// pages/personal/wallet/withdraw/withdraw.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    withdrawCash:'0.00', //可提现金额
    amount:null,
    buttonDisabled:false,
    display1:false,
    display2:true,
    focus:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (!!options.withdrawCash){
      this.setData({
        withdrawCash: options.withdrawCash
      })
    }
  },
  //全部提现
  allWithdraw:function(){
    var amount = parseFloat(this.data.withdrawCash);
    this.setData({
      amount: amount
    })
  },
  inputKeyDown:function(e){
      var amount = parseFloat(e.detail.value);
      var withdrawCash = parseFloat(this.data.withdrawCash);
      if (amount > withdrawCash) {
        this.setData({
          buttonDisabled:true,
          display1:true,
          display2:false
        })
      }else{
        this.setData({
          buttonDisabled: false,
          display1: false,
          display2: true
        })
      }
  },
  //提交申请
  submitForm:function(e){
    var amount = parseFloat(e.detail.value.amount);
    var withdrawCash = parseFloat(this.data.withdrawCash);
    if (!amount){
      app.alert("请先输入提现金额");
      this.setData({
        focus:true
      })
      return false;
    }
    if (amount > withdrawCash){
      app.alert("输入金额不可大于可提现金额");
      return false;
    }
    wx.request({
      url: app.globalData.serverPath + '/lecturer/withdraw/noauth/submitWithdrawRequest',
      data: {
        lecturerId: app.globalData.lecturerInfo.id,
        lecturerName: app.globalData.lecturerInfo.lecturerName,
        withdrawMoney: amount
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.result) {
          wx.redirectTo({
            url: '../withdraw-ok/withdraw-ok'
          })
        } else {
          app.alert(res.data.message);
        }
      }
    })
  },
  //查看提现说明
  lookWithdrawNotice:function(){
    wx.navigateTo({
      url: '../withdraw-notice/withdraw-notice'
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