// pages/personal/speaker/speaker.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    serverPath:'',
    tempFilePath: '',
    applyDivDisplay:true,
    inviteCodeDiv:true,
    inviteInfoDiv:true,
    lecturerInfoDiv:true,
    inviteInfo:{},
    lecturerInfo:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(!!app.globalData.lecturerInfo){
      //已经是讲师，直接显示讲师信息
      this.setData({
        lecturerInfo:app.globalData.lecturerInfo,
        lecturerInfoDiv:false
      })
    }else{
      //注册讲师
      this.setData({
        applyDivDisplay:false
      })
    }
    
    if (!!app.globalData.serverPath){
      this.setData({
        serverPath: app.globalData.serverPath
      })
    }
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
  //提交讲师申请
  submitForm:function(e){
    
    wx.uploadFile({
      url: app.globalData.serverPath + '/import/up/lecturerHeadPhoto',
      filePath: this.data.tempFilePath,
      name: 'files',
      success: function (res) {
        if(!!res.data){
          wx.request({
            url: app.globalData.serverPath + '/lecturer/register/submit',
            data: {
              headPhotoFile: res.data[0].filePath,
              lecturerName: e.detail.value.lecturerName,
              openId: getApp().globalData.user.openId,
              phone: e.detail.value.phone,
              company: e.detail.value.company,
              position: e.detail.value.position
            },
            header: {
              'content-type': 'application/json'
            },
            success: function (res) {
              if(res.result){
                wx.showToast({
                  title: '申请成功',
                  icon: 'success',
                  duration: 2000
                })
              }else{
                wx.showToast({
                  title: res.message,
                  icon: 'success',
                  duration: 2000
                })
              }
            }
          })
        }else{
          wx.showToast({
            title: res.message,
            icon: 'success',
            duration: 2000
          })
        }
      }
    })
    
  },
  //显示输入邀请码
  inputInviteCode: function () {
    this.setData({
      applyDivDisplay: true,
      inviteCodeDiv: false
    })
  },
  //提交邀请码
  submitInvite:function(e){
    var that = this;
    var invite = e.detail.value.inviteCode;
    if (!invite){
      app.alert("请输入邀请码");
    }
    wx.request({
      url: app.globalData.serverPath + '/lecturer/invite/getLecturerByInviteCode',
      data: {
        inviteCode: invite
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.result) {
          that.setData({
            inviteInfo:res.data.obj,
            inviteCodeDiv: true,
            inviteInfoDiv: false
          })
        } else {
          app.alert(res.data.message);
        }
      }
    })
  },
  //返回注册
  returnRegister:function(){
    this.setData({
      applyDivDisplay: false,
      inviteCodeDiv: true,
      inviteInfoDiv:true
    })
  },
  //确认邀请信息
  sureInfo:function(){
    var that = this;
    wx.request({
      url: app.globalData.serverPath + '/lecturer/invite/accept',
      data: {
        inviteCode: that.data.inviteInfo.inviteCode,
        openId: getApp().globalData.user.openId
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.result) {
          that.setData({
            inviteInfoDiv: true,
            lecturerInfoDiv:false
          })
        } else {
          app.alert(res.data.message);
        }
      }
    })

  },
  //获取讲师信息
  getLecturerInfo:function(){
    wx.request({
      url: app.globalData.serverPath + '/lecturer/getByOpenId',
      data: {
        openId: getApp().globalData.user.openId
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.result) {
          this.setData({
            lecturerInfo: res.obj
          })
          app.globalData.lecturerInfo = res.obj;
        } else {
          wx.showToast({
            title: res.message,
            icon: 'success',
            duration: 2000
          })
        }
      }
    })
  }
})