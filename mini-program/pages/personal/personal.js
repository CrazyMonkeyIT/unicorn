// pages/person/person.js

const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    serverPath:'',
    hasUserInfo: false,
    userInfo: {},
    lecturerInfo:null,
    noRegister:true,
    alreadyRegister:true,
    noVip:true,
    alreadyVip:true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;   
    

    if(!!app.globalData.serverPath){
      this.setData({
        serverPath: app.globalData.serverPath
      })
    }
  },
  //获取讲师信息
  getLecturerInfo: function () {
    var that = this;
    wx.request({
      url: app.globalData.serverPath + '/lecturer/noauth/getByOpenId',
      data: {
        openId: app.globalData.user.openId
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.result) {
          console.log("讲师信息");
          console.log(res.data.obj);
          //设置全局讲师信息
          app.globalData.lecturerInfo = res.data.obj;
          that.setData({
            lecturerInfo: getApp().globalData.lecturerInfo,
            noRegister: true,
            alreadyRegister: false
          });
          //已经成为讲师，删除提交注册标识
          wx.removeStorageSync("already_submit_lecturer_register");
        } else {
          that.setData({
            noRegister: false
          })
        }
      }
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
    if (!!app.globalData.user) {
      this.setData({
        userInfo: app.globalData.user,
        hasUserInfo: true
      })
      //判断该用户是否是vip
      if (this.data.userInfo.isVip == 0) {
        console.log("该用户还未是vip");
        this.setData({ noVip: false });
      } else {
        console.log("该用户已是vip");
        this.setData({ alreadyVip: false });
      }
    }

    if (!!app.globalData.lecturerInfo) {
      this.setData({
        lecturerInfo: app.globalData.lecturerInfo,
        noRegister: true,
        alreadyRegister: false
      });
      wx.removeStorageSync("already_submit_lecturer_register");
    } else {
      this.getLecturerInfo();
    }
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
  /** 前往讲师注册页 */
  toRegisterLecturer:function(){
    let already = wx.getStorageSync('already_submit_lecturer_register');
    if (!!already){
      //如果已经提交讲师注册，则进入等待页
      wx.navigateTo({
        url: 'speaker/register-success/register-success'
      })
    }else{
      //如果未提交讲师注册，则仍可以提交注册
      wx.navigateTo({
        url: 'speaker/speaker'
      })
    }
  },
  /** 前往我的直播 */
  toMylive:function(e){
    if (!!this.data.lecturerInfo){
      wx.navigateTo({
        url: 'mylive/mylive',
      })
    }else{
      app.alert("抱歉，您还不是讲师");
    }
  },
  /** 前往我的订阅 */
  toMySubscribe: function (e) {
    wx.navigateTo({
      url: 'subscribe/subscribe',
    })
  },
  /** 前往账户安全 */
  toAccount:function(e){
    wx.navigateTo({
      url: 'account/account',
    })
  }
})