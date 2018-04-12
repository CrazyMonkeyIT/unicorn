const config = require('../../../config.js');
const userUtil = require('../../../global-js/userUtil.js');
//live.js
//获取应用实例
var app = getApp();
var roomId,currentUser;
Page({
  data: {
    motto: 'Hello World',
    need_buy :true,
    hiddenmodalput :true,
    inviteCode : null,
    isBack :false,
    userInfo: {}
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function (e) {
    var that = this;
    roomId = e.roomId;
    currentUser = userUtil.login();

  },
  onShow : function(e) {
   
    console.log(this.data.isBack);
    if (this.data.isBack == true){
      wx.navigateBack({
        delta: 1,
      })
    }
  },
  inviteModel :function(){
    this.setData({
      inviteCode :null,
      hiddenmodalput: false
    });
  },
  inviteCodeTyping : function(e){
    let typingValue = e.detail.value;
    this.setData({
      inviteCode: typingValue
    });
  },
  inviteConfirm : function(e){
    console.log(this.data.inviteCode);
    console.log(currentUser);
    wx.request({
      url: config.service.host + '/minigram/checkInviteCode',
      data: { userId: currentUser.id, roomId: roomId, inviteCode: this.data.inviteCode},
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: function(res) {
        let result = res.data;
        if (result.result == false){
          wx.showToast({
            title: result.message,
            icon :'none',
            mask: true
          })
        }else{
          wx.navigateTo({
            url: '/pages/live/chat/chat?roomId=' + roomId,
          })
        }
      },
      fail: function(res) {
        wx.showToast({
          title: '服务器异常',
          mask: true
        })
      },
      complete: function(res) {},
    })
    
  }

})
