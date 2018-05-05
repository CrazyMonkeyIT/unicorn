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
    focus:false,
    channelArray: [{ "id": 0, "name": '微信零钱' }, { "id": 1, "name": '银行卡' }],
    channelIndex: 0,
    lecturerAccount:null,
    isHasBankCard:false,
    icon0Hidden:false,
    icon1Hidden:true
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
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    //加载讲师账户
    this.loadLecturerAccount();
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

    //如果用户还未绑定银行卡，则需先绑卡
    if (this.data.channelIndex == 1 && !this.data.isHasBankCard){
      wx.navigateTo({
        url: '../bind-bankcard/bind-bankcard'
      })
      return false;
    }
    
    var amount = parseFloat(e.detail.value.amount);
    var withdrawCash = parseFloat(this.data.withdrawCash);
    var withdrawChannel = this.data.channelIndex;
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
        withdrawMoney: amount,
        withdrawChannel: withdrawChannel
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
  //加载讲师账户信息
  loadLecturerAccount: function () {
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
          var isHasBankCard = false;
          if(!!res.data.bankCardNo){
            isHasBankCard = true;
          }
          that.setData({
            lecturerAccount: res.data,
            isHasBankCard: isHasBankCard
          })
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
  bindChannelChange: function (e) {
    this.setData({
      channelIndex: e.detail.value
    })
    if (e.detail.value == 1){
      this.setData({
        icon0Hidden:true,
        icon1Hidden:false
      })
      //选择了银行卡，如果用户还未绑定银行卡，则先进行绑卡
      if (!this.data.isHasBankCard){
        wx.navigateTo({
          url: '../bind-bankcard/bind-bankcard'
        })
      }
    }else{
      this.setData({
        icon0Hidden: false,
        icon1Hidden: true
      })
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
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