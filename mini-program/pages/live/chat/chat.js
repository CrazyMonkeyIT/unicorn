// pages/livechat/livechat.js
const util = require('../../../global-js/util.js')
var app = getApp();
var that,currentUser;
var chatListData = [{ chatid: 1, orientation: 'l', text: '这是一个小测试', type: 'text', nickName: '陈道明' },
  { chatid: 2, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png', nickName:'陈道明' }, 
  { chatid: 20, orientation: 'r', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png', avatarImg: 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLgZK43CgcILte4AfCBOicPTgYb7oIXq9CUPoYSDOgZyZZt000sR5eVib1UW70kW2OWNLeUF1vNu9xg/0', nickName: '楼得罚'}];
var speakerInterval; 
var chartDetail,voicePlaying = false;
Page({
  data: {
    isPlay : false,
    sendButtDisable: true,
    userInfo: {},
    chatList: [],
    scrolltop: '',
    userLogoUrl: '/images/user_default.png',
    keyboard: true,
    isSpeaking: false,
    speakerUrl: '/images/live/speaker0.png',
    speakerUrlPrefix: '/images/live/speaker',
    speakerUrlSuffix: '.png',
    filePath: null,
    contactFlag: true,
    imgUrls: [{
      id: 'tp10282395', url: 'http://image.tupian114.com/20151104/10282395.jpg'
    },{
        id: 'tp19234926', url: 'http://image.tupian114.com/20120410/19234926.jpg'
    }
    ] 
  },
  onLoad: function () {
    console.log("[Console log]:Loading...");
    that = this;
    //获取用户登录信息
    wx.getSetting({
      success: (response) => {
        console.log(response)
        if (!response.authSetting['scope.userInfo']) {
          wx.authorize({
            scope: 'scope.userInfo',
            success: () => {
              console.log('yes');
              wx.getUserInfo({
                success: function (res) {
                  console.log(userInfo);
                  currentUser = res.userInfo;
                  //更新数据
                  that.setData({
                    userInfo: currentUser
                  })
                }
              })
              
            }
          })
        } else {
          //调用应用实例的方法获取全局数据
          wx.getUserInfo({
            success: function (res) {
              console.log("get user info next");
              console.log(res.userInfo);
              currentUser = res.userInfo;
              //更新数据
              that.setData({
                userInfo: currentUser
              })
            }
          })
        }
/*
        if (!response.authSetting['scope.record']) {
          wx.authorize({
            scope: 'scope.record',
            success: () => {
              console.log('scope.record -- > auth');
            }
          })
        }

        if (!response.authSetting['scope.camera']) {
          wx.authorize({
            scope: 'scope.camera',
            success: () => {
              console.log('scope.camera -- > auth');
            }
          })
        }*/
      }
    })

    //远程取一次本聊天室所有的聊天记录做一次初始化
    //todo
    this.setData({ chatList: chatListData});
  },

  onReady: function () {

  }, 
  // 切换语音输入和文字输入
  switchInputType: function () {
    this.setData({
      keyboard: !(this.data.keyboard),
    })
  },
  // 监控输入框输入
  Typing: function (e) {
    var inputVal = e.detail.value;
    var buttDis = true;
    if (inputVal.length != 0) {
      var buttDis = false;
    }
    that.setData({
      sendButtDisable: buttDis,
    })
  },
  // 按钮按下
  touchdown: function () {
    this.setData({
      isSpeaking: true,
    })
    that.speaking.call();
    console.log("[Console log]:Touch down!Start recording!");
    wx.startRecord({
      'success': function (res) {
        var tempFilePath = res.tempFilePath;
        that.data.filePath = tempFilePath;
        console.log("[Console log]:Record success!File path:" + tempFilePath);
        var myVoiceChat = { chatid: util.uuid(1234), orientation: 'r', url: '', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png', voiceTempFilepath: tempFilePath, avatarImg: currentUser.avatarUrl, nickName: currentUser.nickName };
        console.log("[录音结束]")
        console.log(myVoiceChat)
        that.addChat(myVoiceChat);
      },
      'fail': function () {
        console.log("[Console log]:Record failed!");
        wx.showModal({
          title: '录音失败',
          content: '换根手指再试一次！',
          showCancel: false,
          confirmText: '确定',
          confirmColor: '#09BB07',
        })
      },
    });
  },
  // 按钮松开
  touchup: function () {
    wx.stopRecord();
    console.log("[Console log]:Touch up!Stop recording!");
    this.setData({
      isSpeaking: false,
      speakerUrl: '/images/speaker0.png',
    })
    clearInterval(that.speakerInterval);
  },
  // 增加对话到显示界面（scrolltopFlag为True）
  addChat: function (chatOjbect) {
    that.addChatWithFlag(chatOjbect, true);
  },
  // 增加对话到显示界面（scrolltopFlag为是否滚动标志）
  addChatWithFlag: function (chatOjbect, scrolltopFlag) {
    chatListData.push(chatOjbect);
    var charlenght = chatListData.length;
    console.log("[Console log]:Add message to chat list...");
    if (scrolltopFlag) {
      console.log("[Console log]:Rolling to the top...");
      that.setData({
        chatList: chatListData,
        scrolltop: "roll" + charlenght,
      });
    } else {
      console.log("[Console log]:Not rolling...");
      that.setData({
        chatList: chatListData,
      });
    }
  },
  // 分享功能
  onShareAppMessage: function (res) {
    console.log("[Console log]:Sharing the app...");
    return {
      desc: '智能聊',
      desc: '智能聊，比你还能聊~',
      path: 'pages/index/index',
      imageUrl: '/images/chat_logo.png',
      success: function (res) {
        console.log("[Console log]:Share app success...");
        console.log("[Console log]:" + res.errMsg);
      },
      fail: function (res) {
        console.log("[Console log]:Share app fail...");
        console.log("[Console log]:" + res.errMsg);
      }
    }
  },
  // 麦克风帧动画 
  speaking: function () {
    //话筒帧动画 
    var i = 0;
    that.speakerInterval = setInterval(function () {
      i++;
      i = i % 7;
      that.setData({
        speakerUrl: that.data.speakerUrlPrefix + i + that.data.speakerUrlSuffix,
      });
      console.log("[Console log]:Speaker image changing...");
    }, 300);
  },
  //播放语音，查找到指定的语音节点并播放，展示播放动画
  chatVoicePlay :function(e){
    var chatId = e.target.dataset.chatid,voiceObject = null;
    chatListData.forEach(function (chater) {//从对话列表中找出当前点击的语音记录，并执行播放操作
      if (chater.chatid == chatId) {
        voiceObject = chater;
        that.voiceAnimate(chater);
      }
    }); 
    //找不到指定文件时的提示框
    if (voiceObject = null){
      wx.showToast({
        title: '没有找到指定语音',
        icon: 'none',
        duration: 200,
        mask: true,
        success: function(res) {},
        fail: function(res) {},
        complete: function(res) {},
      })
    }
  },
  //语音的动画效果 - 播放
  //判断语音对象中是否已经存了，如果已经存在则直接播放，不存在则下载
  voiceAnimatePlay: function (voiceObject) {
    voicePlaying = true;
    chartDetail = voiceObject;
    if (voiceObject.voiceTempFilepath == undefined || voiceObject.voiceTempFilepath == null){
      console.log('111111');
      wx.downloadFile({
        url: voiceObject.url,
        header: {},
        success: function (res) {
          // 只要服务器有响应数据，就会把响应内容写入文件并进入 success 回调，业务需要自行判断是否下载到了想要的内容 
          that.voiceOper(voiceObject, res.tempFilePath);         
        },
        fail: function (res) {
          wx.showToast({
            title: '没有找到指定语音',
            icon: 'none',
            duration: 200,
            mask: true,
            success: function (res) { },
            fail: function (res) { },
            complete: function (res) { },
          })
        }
      })
    }else {
      console.log('22222222');
      that.voiceOper(voiceObject, voiceObject.voiceTempFilepath);
    }
  },

  voiceOper: function (voiceObject, voiceTempFilepath){
    //语音播放动画
    var i = 0;
    that.speakerInterval = setInterval(function () {
      i++;
      i = i % 3;
      voiceObject.voiceImg = "/images/live/audio_icon_" + (i + 1) + ".png";
      that.setData({
        chatList: chatListData
      }); 
    }, 300);
    //播放语音，语音完成后执行停止播放
    wx.playVoice({
      filePath: voiceTempFilepath,
      duration: 0,
      success: function (res) { },
      fail: function (res) { },
      complete: function (res) {
        that.voiceAnimateStop(voiceObject);
      },
    })
  },
  //语音动画停止效果
  voiceAnimateStop : function (chatTo) {
    clearInterval(that.speakerInterval);
    chatTo.voiceImg = "/images/live/audio_icon_3.png";
    voicePlaying = false;
    that.setData({
      chatList: chatListData
    });
    chartDetail = null;
  },
  //语音的动画效果
  voiceAnimate : function (chatTo) {
    if (chatTo == chartDetail && voicePlaying == true) {
      that.voiceAnimateStop(chatTo);
    } else if (chartDetail != null && chatTo != chartDetail && voicePlaying == true) {
      that.voiceAnimateStop(chartDetail);
      that.voiceAnimatePlay(chatTo);
    } else {
      that.voiceAnimatePlay(chatTo);
    }
  }
  
})
