// pages/livelist/livelist.js

//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    navbar: ['路演', 'VIP', '专题'],
    currentTab: 0,
    roomList: [{ roomId: 1, roomPosterPath:'../../images/home/test/1.jpg'}],
    subjectList: []
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;

    /** 
     * 获取系统信息 
     */
    wx.getSystemInfo({
      success: function (res) {
        console.log(res);
        that.setData({
          //winWidth: res.windowWidth,
          //winHeight: res.windowHeight
        });
      }

    });  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },
  
  /**
   * 页面顶部tab切换事件
   */
  navbarTap: function (e) {
    this.setData({
      currentTab: e.currentTarget.dataset.idx
    });
    this.roomList()
  },

  /**
   * 获取直播列表数据事件
   */
  roomList: function(){
    var that = this;
    let roomType;
    if (this.data.currentTab == 0) {
      roomType = 1;
    } else if (this.data.currentTab == 1) {
      roomType = 0;
    } else {
      //未知类型,当作主题tab来处理
      roomType = -1;
      wx.request({
        url: app.globalData.serverPath +'/mini/home/subject/list',
        method: 'GET',
        dataType: 'json',
        success: function (result) {
          that.setData({
            'subjectList': result.data.obj
          })
        }
      })
    }
    wx.request({
      url: app.globalData.serverPath + '/mini/home/room/' + roomType + '/list',
      method: 'GET',
      dataType: 'json',
      success: function (result) {
        that.setData({
          'roomList': result.data.obj
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.setData({
      'currentTab': app.currentTab
    });
    this.roomList();
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
  
  }

})