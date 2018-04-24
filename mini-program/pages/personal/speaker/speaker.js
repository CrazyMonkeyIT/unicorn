// pages/personal/speaker/speaker.js
const config = require("../../../config.js");
const app = getApp();
var that = null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    serverPath:'',
    tempFilePath: '',
    applyDivDisplay:true,
    inviteCodeDiv:true,
    inviteInfoDiv:true,
    lecturerInfoDiv:true,
    inviteInfo:{},
    lecturerInfo:{},
    descView:true,
    descInpt:false,
    textareaFocus: false,
    desc: '',
    phone:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    that = this;
    if(!!app.globalData.lecturerInfo){
      //已经是讲师，直接显示讲师信息
      this.setData({
        lecturerInfo:app.globalData.lecturerInfo,
        lecturerInfoDiv:false
      })
    }else{
      //注册讲师
      this.setData({
        applyDivDisplay:false
      })
    }
    
    if (!!app.globalData.serverPath){
      this.setData({
        serverPath: app.globalData.serverPath
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  /** 取消 */
  clean: function () {
    wx.navigateBack();
  },

  chooseImage: function () {
    var that = this
    wx.chooseImage({
      count: 1,
      success: function (res) {
        console.log(res);
        that.setData({
          tempFilePath: res.tempFilePaths[0]
        })
      }
    })
  },
  //提交讲师申请
  submitForm:function(e){
    var lecturerName = e.detail.value.lecturerName;
    var openId = app.globalData.user.openId;
    var phone = e.detail.value.phone;
    var company = e.detail.value.company;
    var position = e.detail.value.position;
    var introduction = that.data.desc;
    if (!lecturerName){
      app.alert("讲师姓名必须填写");
      return false;
    }
    if (!phone) {
      app.alert("联系方式必须填写");
      return false;
    }
    if (!company) {
      app.alert("所在公司必须填写");
      return false;
    }
    if (!position) {
      app.alert("所在职位必须填写");
      return false;
    }
    //上传讲师头像
    wx.uploadFile({
      url: app.globalData.serverPath + '/import/up/lecturerHeadPhoto',
      filePath: this.data.tempFilePath,
      name: 'file',
      success: function (res1) {
        var headPhotoFile = JSON.parse(res1.data)[0].filePath;
        if (!!res1.data){
          //提交讲师注册信息
          wx.request({
            url: app.globalData.serverPath + '/lecturer/register/noauth/submit',
            data: {
              headPhotoFile: headPhotoFile,
              lecturerName: lecturerName,
              openId: openId,
              phone: phone,
              company: company,
              position: position,
              introduction: introduction
            },
            method: 'POST',
            header: {
              'content-type': 'application/json'
            },
            success: function (res) {
              if(res.data.result){
                //已注册成功
                wx.setStorageSync('already_submit_lecturer_register','true');
                wx.redirectTo({
                  url: 'register-success/register-success'
                })
              }else{
                app.alert(res.data.message);
              }
            }
          })
        }else{
          app.alert(res1.data.message);
        }
      }
    })
    
  },
  //显示输入邀请码
  inputInviteCode: function () {
    this.setData({
      applyDivDisplay: true,
      inviteCodeDiv: false
    })
  },
  //提交邀请码
  submitInvite:function(e){
    var that = this;
    var invite = e.detail.value.inviteCode;
    if (!invite){
      app.alert("请输入邀请码");
    }
    wx.request({
      url: app.globalData.serverPath + '/lecturer/invite/noauth/getLecturerByInviteCode',
      data: {
        inviteCode: invite
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.result) {
          that.setData({
            inviteInfo:res.data.obj,
            inviteCodeDiv: true,
            inviteInfoDiv: false
          })
        } else {
          app.alert(res.data.message);
        }
      }
    })
  },
  //返回注册
  returnRegister:function(){
    this.setData({
      applyDivDisplay: false,
      inviteCodeDiv: true,
      inviteInfoDiv:true
    })
  },
  //确认邀请信息
  sureInfo:function(){
    var that = this;
    wx.request({
      url: app.globalData.serverPath + '/lecturer/invite/noauth/accept',
      data: {
        inviteCode: that.data.inviteInfo.inviteCode,
        openId: getApp().globalData.user.openId
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.result) {
          //重新获取讲师信息
          that.getLecturerInfo(function(){
            that.setData({
              inviteInfoDiv: true,
              lecturerInfoDiv: false,
              lecturerInfo: app.globalData.lecturerInfo
            })
          });
        } else {
          app.alert(res.data.message);
        }
      }
    })

  },
  //获取讲师信息
  getLecturerInfo: function (callback) {
    var that = this;
    wx.request({
      url: app.globalData.serverPath + '/lecturer/noauth/getByOpenId',
      data: {
        openId: app.globalData.user.openId
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.result) {
          app.globalData.lecturerInfo = res.data.obj;
          callback();
        } else {
          app.alert(res.data.message);
        }
      }
    })
  },
  clickTextarea: function () {
    this.setData({
      textareaFocus: true
    });
  },
  inputDesc: function (e) {
    this.setData({
      desc: e.detail.value
    });
  },
  closeDescInput: function () {
    this.setData({
      descView: false,
      descInpt: true
    });
  },
  clickDescView: function () {
    this.setData({
      descView: true,
      descInpt: false,
      textareaFocus: true
    });
    this.setData({
      textareaFocus: true
    })
  },
  getPhoneNumber: function (e) {
    var errMsg = e.detail.errMsg;
    var iv = e.detail.iv;
    var encryptedData = e.detail.encryptedData;
    console.log(e);
    if (e.detail.errMsg == 'getPhoneNumber:fail user deny') {
      consolw.log("用户未授权获取手机号");
    }else{
      console.log("用户已授权获取手机号");
      wx.login({
        success: function (res) {
          console.log("res.code:" + res.code);
          if (res.code) {
            wx.request({
              url: config.service.getauth,
              data: {
                code: res.code,
                iv: iv,
                encryptedData: encryptedData
              },
              header: {},
              method: 'GET',
              dataType: 'json',
              success: function (result) {
                console.log(result);
                that.setData({
                  phone: result.data.purePhoneNumber
                })
              }
            })
          }
        },
        fail: function (res) { },
        complete: function (res) { },
      });
    }
  } 
})