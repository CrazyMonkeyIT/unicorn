var app = getApp();
Page({
  onLoad: function () {
    this.loadSubjectList();
  },
  onShow:function(){
    var n = this.randomNum();
    this.setData({
      randomNum:n
    })
  },
  data: {
    roomPosterTemp: '',
    subjectArray: [],
    subjectIndex:0,
    roomTypeArray:[{"id":0,"name":'VIP'},{"id":0,"name":'路演'}],
    roomTypeIndex:0,
    beginDate:'2018-01-01',
    endDate:'2018-12-31',
    beginTime:'00:00',
    endTime:'23:59',
    randomNum:123456,
    submitText:'提交',
    submitLoading:false,
    roomCoursewareId:null,
    interval:null
  },
  chooseImage: function () {
    var that = this
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
    var that = this;
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
    var that = this;

    var name =  e.detail.value.name;
    var subjectId = this.data.subjectArray[this.data.subjectIndex].subjectId;
    var lecturerId = app.globalData.lecturerInfo.id;
    var roomDesc = e.detail.value.roomDesc;
    var roomPosterPath = '';
    console.log(this.data.roomTypeArray[this.data.roomTypeIndex]);
    var type1 = this.data.roomTypeArray[this.data.roomTypeIndex].id;
    var roomPrice = e.detail.value.roomPrice;
    var coursewareId = '';
    var prepareLiveBeginTime = this.data.beginDate + ' ' + this.data.beginTime;
    var prepareLiveEndTime = this.data.endDate + ' ' + this.data.endTime;
    var heraldPath = '';
    var inviteCode = this.data.randomNum;
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
          wx.showModal({
            content: "请用电脑端打开如下地址上传课件：" + app.globalData.serverPath + "/minigram/intoRoomCourseware/" + roomCoursewareId,
            showCancel: false,
            confirmText: "确定"
          })
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
    var that = this;
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
          room['coursewareId'] = res.data.obj.coursewareId;
          room['heraldPath'] = res.data.obj.heraldPath;
          this.setData({
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
                wx.redirectTo({
                  url: '../create-success/create-success'
                })
              } else {
                app.alert(res.data.message);
              }
            }
          })
        } else {
          app.alert(res1.data.message);
        }
      }
    })
  },
  /** 取消 */
  clean:function(){
    wx.navigateBack();
  }
})
