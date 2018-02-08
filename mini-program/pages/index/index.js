//index.js


//获取应用实例
const app = getApp()
 
Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    imgUrls :[
      'http://image.tupian114.com/20151104/10282395.jpg',
      'http://image.tupian114.com/20120410/19234926.jpg'
    ],
    autoplay :true,
    interval :3000,
    duration :500,
    indicatorDots:false
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    
  }
});
