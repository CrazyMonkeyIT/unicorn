// pages/personal/mylive/mylive.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    rooms:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
    this.loadLecturerRooms();
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
  //加载讲师直播列表
  loadLecturerRooms: function(){
    var that = this;
    wx.request({
      url: app.globalData.serverPath + '/minigram/getLecturerRooms',
      header: {
        'content-type': 'application/json'
      },
      data:{
        lecturerId:app.globalData.lecturerInfo.id
      },
      success: function (res) {
        if (!!res.data) {
          that.setData({
            rooms : res.data
          });
        }
      }
    })
  },
  //前往直播
  gotoLive:function(e){
    var roomid = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../../live/startlive/startlive?roomId=' + roomid
    })
  }
})