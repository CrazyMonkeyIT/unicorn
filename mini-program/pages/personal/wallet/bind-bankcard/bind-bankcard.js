// pages/personal/wallet/bind-bankcard/bind-bankcard.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bankArray: [],
    bankIndex: 0,
    lecturerAccountId:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.loadBankList();
    this.loadLecturerAccount();
  },
  loadBankList:function () {
    var that = this;
    wx.request({
      url: app.globalData.serverPath + '/lecturer/withdraw/noauth/getBankCodeMap',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (!!res.data) {
          that.setData({
            bankArray: res.data
          });
        } else {
          app.alert("加载银行列表失败");
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
          that.setData({
            lecturerAccountId: res.data.id
          })
        }
      }
    })
  },
  //提交
  submit: function (e) {
    var id = this.data.lecturerAccountId;
    var realName = e.detail.value.realName;
    var bankCardNo = e.detail.value.cardNo;
    var bankCode = this.data.bankArray[this.data.bankIndex].code;

    wx.request({
      url: app.globalData.serverPath + '/lecturer/noauth/updateAccount',
      data: {
        id:id,
        realName: realName,
        bankCardNo: bankCardNo,
        bankCode: bankCode
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.result) {
          wx.navigateBack();
        }else{
          app.alert(res.data.message);
        }
      }
    })
  },
  bindBankChange: function (e) {
    this.setData({
      bankIndex: e.detail.value
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
  
  },
  /** 取消 */
  cancel: function () {
    wx.navigateBack();
  }
})