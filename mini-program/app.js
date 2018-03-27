//app.js
const userUtil = require("/global-js/userUtil.js");
const config = require("config.js");
App({
  onLaunch: function () {

    if (!this.globalData.user) {
      this.globalData.user = userUtil.login();
    }
    if(!this.globalData.serverPath){
      this.globalData.serverPath = config.service.host;
    }

  },
  //重新加载用户信息
  reloadUser:function(){
    this.globalData.user = userUtil.login();
  },
  globalData: {
    //用户信息
    user:null,
    //服务器地址
    serverPath:null,
    //讲师信息
    lecturerInfo:null
  }
})