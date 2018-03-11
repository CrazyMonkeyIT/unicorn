
const config = require("../config.js")
const login = function(){
  var userData  = wx.getStorageSync('miniUser');//从本地缓存中读取用户信息
  if(!userData){//如果没有读取到用户信息，则进行授权
    loginOper();
    userData = wx.getStorageSync('miniUser');
  }
  return userData;
}
const loginOper = function(){
  wx.login({
    success: function (res) {
      if (res.code) {
        wx.getUserInfo({
          success: function (userRes) {
            wx.request({
              url: config.service.getauth,
              data: {
                code: res.code,
                iv: userRes.iv,
                encryptedData: userRes.encryptedData
              },
              header: {},
              method: 'GET',
              dataType: 'json',
              responseType: 'text',
              success: function (result) {
                wx.request({
                  url: config.service.saveUser,
                  data: {
                    openId: result.data.openId,
                    gender: result.data.gender,
                    country: result.data.country,
                    avatarUrl: result.data.avatarUrl,
                    city: result.data.city,
                    province: result.data.province,
                    nickName: result.data.nickName
                  },
                  method: 'POST',
                  dataType: 'json',
                  responseType: 'text',
                  success: function(saveRes) {
                    if (saveRes.data.success == true){
                      wx.setStorageSync('miniUser', {
                        openId: result.data.openId,
                        gender: result.data.gender,
                        country: result.data.country,
                        avatarUrl: result.data.avatarUrl,
                        city: result.data.city,
                        province: result.data.province,
                        nickName: result.data.nickName
                      });
                    }
                  }
                })
              }
            })
          }
        })
      }
    },
    fail: function (res) { },
    complete: function (res) { },
  });
}

module.exports = {
  login: login
}