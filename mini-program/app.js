//app.js
const userUtil = require("/global-js/userUtil.js");
const config = require("config.js");
App({
  onLaunch: function () {
    
    if (!this.globalData.user) {
      var that = this;
      userUtil.login(function () {
        that.globalData.user = wx.getStorageSync('miniUser');
      });
    }
    if(!this.globalData.serverPath){
      this.globalData.serverPath = config.service.host;
    }

  },
  //重新获取用户信息
  reloadUser:function(callback){
    console.log("reloadUser");
    var that = this;
    that.callback = callback;
    userUtil.login_realtime(function (callback) {
      that.globalData.user = wx.getStorageSync('miniUser');
      console.log("已获取到用户信息");
      that.callback();
    });
  },
  //提示信息
  alert: function (content){
    wx.showToast({
      title: content,
      icon: 'none',
      duration: 2000
    })
  },
  alertSuccess:function(centent){
    wx.showToast({
      title: centent,
      icon: 'success',
      duration: 2000
    })
  },
  globalData: {
    //用户信息
    user:null,
    //服务器地址
    serverPath:null,
    //讲师信息
    lecturerInfo:null
  },
  currentTab: 0
})