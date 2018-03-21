// pages/personal/speaker/speaker.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tempFilePath: '',
    savedFilePath: '',
    dialog: {
      hidden: true
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      savedFilePath: wx.getStorageSync('savedFilePath')
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
  clean: function () {
    wx.navigateBack();
  },

  chooseImage: function () {
    var that = this
    wx.chooseImage({
      count: 1,
      success: function (res) {
        that.setData({
          tempFilePath: res.tempFilePaths[0]
        })
      }
    })
  },
  saveFile: function () {
    if (this.data.tempFilePath.length > 0) {
      var that = this
      wx.saveFile({
        tempFilePath: this.data.tempFilePath,
        success: function (res) {
          that.setData({
            savedFilePath: res.savedFilePath
          })
          wx.setStorageSync('savedFilePath', res.savedFilePath)
          that.setData({
            dialog: {
              title: '保存成功',
              content: '下次进入应用时，此文件仍可用',
              hidden: false
            }
          })
        },
        fail: function (res) {
          that.setData({
            dialog: {
              title: '保存失败',
              content: '应该是有 bug 吧',
              hidden: false
            }
          })
        }
      })
    }
  },
  clear: function () {
    wx.setStorageSync('savedFilePath', '')
    this.setData({
      tempFilePath: '',
      savedFilePath: ''
    })
  }
})