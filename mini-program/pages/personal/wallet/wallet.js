// pages/personal/wallet/wallet.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    serverPath:'',
    incomeAmount:"0.00",
    shopingAmount:"0.00",
    withdrawCash:'0.00'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (!!app.globalData.lecturerInfo){
      this.loadLecturerAccount();
    }
    if(!!app.globalData.user){
      this.setData({
        shopingAmount: app.globalData.user.totalPayAmount
      })
    }

    if (!!app.globalData.serverPath) {
      this.setData({
        serverPath: app.globalData.serverPath
      })
    }
  },
  //加载讲师账户信息
  loadLecturerAccount: function(){
    var that = this;
    wx.request({
      url: app.globalData.serverPath + '/wallet/getLecturerAccount',
      data: {
        lecturerId: app.globalData.lecturerInfo.id
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (!!res.data) {
          that.setData({
            incomeAmount: res.data.totalIncome + ".00",
            withdrawCash: res.data.withdrawCash + ".00"
          })
        }
      }
    })
  },
  //查看收益
  toIncome:function(){
    console.log(app.globalData.lecturerInfo);
    if (!app.globalData.lecturerInfo){
      return;
    }
    wx.navigateTo({
      url: 'detail/detail?openId=' + app.globalData.user.openId + '&lecturerId=' + app.globalData.lecturerInfo.id + '&type=1'
    })
  },
  //查看支出
  toShopping:function(){
    wx.navigateTo({
      url: 'detail/detail?openId=' + app.globalData.user.openId + '&type=2'
    })
  },
  //查看收支流水
  toAll:function(){
    wx.navigateTo({
      url: 'detail/detail?openId=' + app.globalData.user.openId + '&type=3'
    })
  },
  //进入提现页面
  toWithdraw:function(){
    wx.navigateTo({
      url: 'withdraw/withdraw?withdrawCash=' + this.data.withdrawCash
    })
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