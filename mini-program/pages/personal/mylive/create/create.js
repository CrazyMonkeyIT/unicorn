var app = getApp(),that = null;
const util = require("../../../../global-js/util.js");
Page({
  onLoad: function () {
    that = this;
    this.loadSubjectList();
  },
  onShow:function(){
    var n = this.randomNum();
    this.setData({
      randomNum:n
    });
    var currDate = util.getNowFormatDate(new Date());
    var nextHourDate = util.getNowFormatDate(new Date(new Date().getTime() + 1000 * 60 * 60));
    this.setData({
      beginDate: currDate.substring(0, 10),
      beginTime: currDate.substring(11, 16),
      endDate: nextHourDate.substring(0, 10),
      endTime: nextHourDate.substring(11, 16)
    })
  },
  data: {
    roomPosterTemp: '',
    subjectArray: [],
    subjectIndex:0,
    roomTypeArray:[{"id":0,"name":'VIP'},{"id":1,"name":'路演'}],
    roomTypeIndex:0,
    beginDate:'2018-01-01',
    endDate:'2018-01-02',
    beginTime:'00:00',
    endTime:'23:59',
    randomNum:123456,
    submitText:'提交',
    submitLoading:false,
    roomCoursewareId:null,
    interval:null,
    textareaFocus:false,
    roomDesc:'',
    roomDescView:true,
    roomDescInpt:false
  },
  chooseImage: function () {
    wx.chooseImage({
      count: 1,
      success: function (res) {
        that.setData({
          roomPosterTemp: res.tempFilePaths[0]
        })
      }
    })
  },
  bindSubjectChange: function (e) {
    this.setData({
      subjectIndex: e.detail.value
    })
  },
  bindRoomTypeChange: function (e) {
    this.setData({
      roomTypeIndex: e.detail.value
    })
  },
  bindBeginDateChange:function (e){
    this.setData({
      beginDate: e.detail.value
    })
  },
  bindEndDateChange: function (e) {
    this.setData({
      endDate: e.detail.value
    })
  },
  bindBeginTimeChange: function (e) {
    this.setData({
      beginTime: e.detail.value
    })
  },
  bindEndTimeChange: function (e) {
    this.setData({
      endTime: e.detail.value
    })
  },
  loadSubjectList:function(){
    wx.request({
      url: app.globalData.serverPath + '/subject/noauth/getAllSubject',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (!!res.data) {
          that.setData({
            subjectArray: res.data
          });
        } else {
          app.alert("加载主题列表失败");
        }
      }
    })
  },
  //产生6位随机数函数
  randomNum : function (){
    var rNum = "";
    for (var i = 0; i < 6; i++) {
      rNum += Math.floor(Math.random() * 10);
    } 
    return rNum;
  },
  //提交
  submit:function(e){

    var name =  e.detail.value.name;
    var subjectId = this.data.subjectArray[this.data.subjectIndex].subjectId;
    var lecturerId = app.globalData.lecturerInfo.id;
    var roomDesc = e.detail.value.roomDesc;
    var roomPosterPath = '';
    console.log(this.data.roomTypeArray[this.data.roomTypeIndex]);
    var type1 = this.data.roomTypeArray[this.data.roomTypeIndex].id;
    var roomPrice = e.detail.value.roomPrice;
    var coursewareId = '';
    var prepareLiveBeginTime = this.data.beginDate + ' ' + this.data.beginTime + ':00';
    var prepareLiveEndTime = this.data.endDate + ' ' + this.data.endTime + ':00';
    var heraldPath = '';
    var inviteCode = this.data.randomNum;

    if (!this.data.roomPosterTemp){
      app.alert("还未选择直播海报");
      return false;
    }
    if (!name){
      app.alert("请输入直播标题");
      return false;
    }
    if (!roomPrice){
      app.alert("请输入房间金额");
      return false;
    }
    if (!prepareLiveBeginTime){
      app.alert("请选择预计开始时间");
      return false;
    }
    if (!prepareLiveEndTime){
      app.alert("请选择预计结束时间");
      return false;
    }

    var room = {
      name: name,
      subjectId: subjectId,
      lecturerId: lecturerId,
      roomDesc: roomDesc,
      type: type1,
      roomPrice: roomPrice,
      prepareLiveBeginTime: prepareLiveBeginTime,
      prepareLiveEndTime: prepareLiveEndTime,
      inviteCode: inviteCode,
      roomPosterPath: roomPosterPath,
      coursewareId: coursewareId,
      heraldPath: heraldPath
    };


    //先获取上传课件码
    wx.request({
      url: app.globalData.serverPath + '/minigram/updateRoomCourseware',
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if(res.data.result){
          var roomCoursewareId =  res.data.obj;
          var sourceUrl = app.globalData.serverPath + "/minigram/intoRoomCourseware/" + roomCoursewareId;
          if (app.globalData.serverPath.indexOf('localhost') == -1){
            //获取短链接
            util.getShortLink(sourceUrl,function(link){
              //复制到粘贴板
              wx.setClipboardData({data: link});
              wx.showModal({
                content: "请用电脑端打开如下地址上传课件：\n   \n" + link + '（已复制到粘贴板）',
                showCancel: false,
                confirmText: "确定"
              });
            });
          }else{
            //复制到粘贴板
            wx.setClipboardData({ data: sourceUrl });
            wx.showModal({
              content: "请用电脑端打开如下地址上传课件：\n  \n" + sourceUrl + '（已复制到粘贴板）',
              showCancel: false,
              confirmText: "确定"
            })
          }
          //开始轮询
          var interval = setInterval(that.selectCoursewareFinish, 3000);

          that.setData({
            submitText: '等待课件上传完成',
            submitLoading: true,
            roomCoursewareId: roomCoursewareId,
            room: room,
            interval: interval
          })
          

        }
      }
    });
  },
  //轮询，查询课件是否已上传完毕
  selectCoursewareFinish:function(){
    console.log("roomCoursewareId：" + that.data.roomCoursewareId);
    console.log("开始轮询，查询课件是否上传");
    wx.request({
      url: app.globalData.serverPath + '/minigram/selectCoursewareFinish/' + that.data.roomCoursewareId,
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.result) {
          var room = that.data.room;
          room['heraldPath'] = res.data.obj.heraldPath;
          that.setData({
            room:room
          });
          //可以提交信息了
          that.saveRoomInfo();
          //停止轮询
          clearInterval(that.data.interval);
        }
      }
    });
  },
  //保存房间信息
  saveRoomInfo:function(){
    var room = this.data.room;
    //上传房间海报
    wx.uploadFile({
      url: app.globalData.serverPath + '/import/up/roomPoster',
      filePath: this.data.roomPosterTemp,
      name: 'file',
      success: function (res1) {
        var roomPosterPath = JSON.parse(res1.data)[0].filePath;
        if (!!roomPosterPath) {
          room['roomPosterPath'] = roomPosterPath;
          //提交房间信息
          wx.request({
            url: app.globalData.serverPath + '/minigram/saveRoomInfo',
            data: room,
            method: 'POST',
            header: {
              'content-type': 'application/json'
            },
            success: function (res) {
              if (res.data.result) {
                //已提交成功
                that.saveRoomCoursewareRelative(res.data.obj);
                wx.redirectTo({
                  url: '../create-success/create-success'
                })
              } else {
                app.alert(res.data.message);
                that.setData({
                  submitText: '提交',
                  submitLoading: false
                })
              }
            }
          })
        } else {
          app.alert(res1.data.message);
        }
      }
    })
  },
  //保存房间与课件关系
  saveRoomCoursewareRelative:function(roomId){
    console.log("roomId:" + roomId);
    console.log("roomCoursewareId：" + that.data.roomCoursewareId);
    wx.request({
      url: app.globalData.serverPath + '/minigram/updateRoomCourseware',
      data: {
        id: that.data.roomCoursewareId,
        roomId: roomId
      },
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        console.log("保存房间与课件关系完成");
        
      }
    });
  },
  clickTextarea:function(){
    this.setData({
      textareaFocus:true
    });
  },
  inputRoomDesc:function(e){
    this.setData({
      roomDesc:e.detail.value
    });
  },
  closeRoomDescInput:function(){
    this.setData({
      roomDescView: false,
      roomDescInpt: true
    });
  },
  clickRoomDescView:function(){
    this.setData({
      roomDescView: true,
      roomDescInpt: false,
      textareaFocus: true
    });
    this.setData({
      textareaFocus: true
    })
  },
  /** 取消 */
  clean:function(){
    wx.navigateBack();
  }
})
