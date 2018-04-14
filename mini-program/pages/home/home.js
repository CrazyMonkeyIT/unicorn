//index.js

//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    
    autoplay: true,
    interval: 3000,
    duration: 500,
    indicatorDots: false, 
    roadShowList: [],
    chiefList: [],
    advertisementList: [],
    liveList: []
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  //初始化首页数据
  onShow: function() {
    var that = this;
    var serverPath = app.globalData.serverPath;
    //首席列表
    wx.request({
      url: serverPath + '/mini/home/list',
      method: 'GET',
      dataType: 'json',
      success: function (result) {
        let obj = result.data.obj;
        that.setData({
          //广告数据
          'advertisementList': obj.advertisementList,
          //路演数据
          'roadShowList': obj.roadShowList,
          //直播数据
          'liveList': obj.liveList,
          //首席数据
          'chiefList': obj.chiefList
        })
      }
    })
  },
  onLoad: function () {
    
  },

  //更多首席
  moreChiefInfo: function(){
    wx.redirectTo({
      url: 'chief/chief',
    })
  },

  //键盘完成按钮时触发(用来搜索)
  bindconfirm: function(event){
    wx.showToast({
      title: '搜索小哥正在快马加鞭的查找，请稍后!',
      icon: 'none'
    })
  },
  //首页跳转首席讲师直播
  chiefLive: function(e){
    console.log(e.detail);
  },
  //首页广告分析/跳转
  advertisementLink: function(e){
    let info = e.target.dataset.item;
    if (1 == info.advertisementTypeId){
      
    } else if (2 == info.advertisementTypeId){
      
    } else if (3 == info.advertisementTypeId){
      if (info.roomId == undefined){
        wx.showToast({
          title: '房间不存在',
          icon: 'none'
        })
        return;
      }
      //指定路演广告
      wx.navigateTo({
        url: '/pages/live/chat/chat?roomId=' + info.roomId
      })
    } else if (4 == info.advertisementTypeId){
      
    }else{
      console.log("未知类型");
    }
  }
});
