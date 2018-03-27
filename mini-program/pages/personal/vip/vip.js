// pages/personal/vip/vip.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    vipList:null,
    isVipDiv:true,
    isNotVipDiv:true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (!!app.globalData.user) {
      this.setData({
        userInfo: app.globalData.user,
      })
      if(this.data.userInfo.isVip == 0){
        this.setData({ isNotVipDiv:false}) 
      }else{
        this.setData({ isVipDiv: false })
      }
    }
    this.loadVipList();
  }, 
  loadVipList:function(){
    var that = this;
    wx.request({
      url: app.globalData.serverPath + '/vip/all',
      success: function (res) {
        if (res.data.result) {
          that.setData({
            vipList: res.data.obj
          })
        }else{
          console.log(res.data.message);
        }
      }
    })
  },
  openMember:function(e){
    console.log(e.target.dataset.vipid);
    console.log(e.target.dataset.money);
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
  
  }
})