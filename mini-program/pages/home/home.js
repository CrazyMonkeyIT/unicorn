//index.js


//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    imgUrls: [
      'http://image.tupian114.com/20151104/10282395.jpg',
      'http://image.tupian114.com/20120410/19234926.jpg'
    ],
    autoplay: true,
    interval: 3000,
    duration: 500,
    indicatorDots: false,
    roadShowImages: [
      '../../images/home/test/1.jpg',
      '../../images/home/test/2.jpg',
      '../../images/home/test/3.jpg',
      '../../images/home/test/4.jpg'],
    chiefInfoList: [
      {
        name: "Lee",
        desc: "招商证券食品饮料行业首席分析师，研发中心董事。4次新财富最佳分析师第一名，11次上榜",
        photoPath: "../../images/home/test/Lee.jpg"
      },
      {
        name: "Bill",
        desc: "安信证券农林牧渔行业首席分析师，新财富最佳分析师第一名，汤森路透StarMine全球卖方分析师第一名",
        photoPath: "../../images/home/test/bill.jpg"
      },
      {
        name: "xin",
        desc: "华泰证券建筑建材行业首席分析师，3次新财富最佳分析师团队第一名",
        photoPath: "../../images/home/test/xin.jpg"
      },
      {
        name: "myron",
        desc: "天风证券传媒互联网高级分析师，包揽新财富最佳分析师、水晶球、金牛奖第一名",
        photoPath: "../../images/home/test/myron.jpg"
      }
    ]
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {

  }
});
