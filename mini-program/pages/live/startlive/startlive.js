const config = require('../../../config.js');
const userUtil = require('../../../global-js/userUtil.js');
//live.js
//获取应用实例
var app = getApp();
var roomId,currentUser,that;
Page({
  data: {
    motto: 'Hello World',
    need_buy :true,
    hiddenmodalput :true,
    inviteCode : null,
    isBack :false,
    roomPostPic :'../../../images/live/tmp_post.jpg',
    userInfo: {},
    userViplist: [], //VIP充值可选项列表
    needBuy :false,
    needBuyVip :false,
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function (e) {
    that = this;
    roomId = e.roomId;
    currentUser = userUtil.login();

  },
  onShow : function(e) {
    //此页为中转验证页
    //如果由上级返回至此页，则直接再做一次返回操作
    if (this.data.isBack == true){
      wx.navigateBack({
        delta: 1,
      })
      return;
    }
    wx.request({
      url: config.service.host + '/minigram/checkUserPermission',
      data: { userId: currentUser.id,roomId:roomId},
      header: {},
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: function(res) {
        let result = res.data;
        if(result.result == true){
          if (result.roomOwner == true){
            that.interRoom();
          }
          if (result.payInviteCode == true || result.hasPayRoom == true){
            that.interRoom();
          }else{
            that.setData({
              needBuy: true
            })
          }

          if (result.isVipRoom == true){//如果是VIP房间
            if (result.isVip == true){//如果用户是VIP则直接进入房间
              that.interRoom();
            }else{//如果用户不是VIP，提示用户进行VIP购买
              that.viplist();//查询可购买VIP列表
              that.setData({
                needBuyVip : true
              })
            }
          }
        }
      },
      fail: function(res) {},
      complete: function(res) {},
    })
  },
  interRoom : function(){
    /*wx.navigateTo({
      url: '/pages/live/chat/chat?roomId=' + roomId,
    })*/
  },
  viplist : function(){
    wx.request({
      url: config.service.host + '/minigram/allVip',
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: function(res) {
        let result = res.data;
        that.setData({
          userViplist : result
        })
      },
      fail: function(res) {},
      complete: function(res) {},
    })
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
