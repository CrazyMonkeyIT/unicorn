// pages/livechat/livechat.js
const util = require('../../../global-js/util.js');
const userUtil = require('../../../global-js/userUtil.js');
const config = require('../../../config.js');
var app = getApp();
var that, currentUser, speakerSec = 0;
var chatListData = [];
var speakerInterval, speakerSecInterval; 
var chartDetail,voicePlaying = false;
Page({
  data: {
    isPlay : false,
    sendButtDisable: true,
    userInfo: {},
    chatList: [],
    scrolltop: '',
    speakerSecond : 60,
    speakerSecondCls : 'speaker-sec',//录音读秒样式，50秒后显示红色字体
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
    currentUser = userUtil.login();
    //获取用户登录信息
    wx.getSetting({
      success(res) {
        if (!res.authSetting['scope.record']) {
          console.log('record is not auth.........');
          wx.authorize({
            scope: 'scope.record',
            success: () => {
              
            }
          })
        }
      }
    })

    //远程取一次本聊天室所有的聊天记录做一次初始化
    //todo
    this.setData({ chatList: chatListData});
  },

  onReady: function () {
    var lastChatId = 0;
    if (chatListData.length != 0){
      lastChatId = chatListData[chatListData.length - 1].id;
    }
    wx.request({
      url: config.service.roomHistory,
      data: { roomid: 123, id: lastChatId},
      header: {},
      method: 'POST',
      dataType:'json',
      responseType: 'text',
      success: function(res) {
        console.log("room history。。。。。。");
        console.log(res.data);
        res.data.forEach(function(data){
          that.roomContentProcess(data,false);
        });
      }
    })
  }, 
  //接收后台传来的聊天内容的处理
  //chatContent 接收到的内容，可能是初次进入房间时拉的，可能是实时聊天时socket通信的
  //isRealTime是否实时聊天产生的标识，如果实时聊天产生，判断是当前用户的聊天直接抛弃，
  //因为本地发送时已经处理到聊天窗口里了
  roomContentProcess : function(chatContent,isRealTime){
    console.log("----------------roomContentProcess-------------------");
    console.log(chatContent);
    if (chatContent.type == 'voice'){
      chatContent.voiceImg = '/images/live/audio_icon_3.png';
    }
    if (chatContent.openId == currentUser.openId){
      if (isRealTime == true) return;
      chatContent.orientation = "r";
    }else{
      chatContent.orientation = "l";
    }
    console.log("----------------roomContentProcess-------------------");
    console.log(chatContent);
    that.addChat(chatContent);
  },
  // 切换语音输入和文字输入
  switchInputType: function () {
    this.setData({
      keyboard: !(this.data.keyboard),
    })
  },
  sendChat : function (e){
    var inputVal = e.detail.value;
    console.log('[input log]:' + inputVal);
    if (!inputVal){
      wx.showToast({
        title: '不能发送空内容!!!',
        icon: 'none',
        duration: 0,
        mask: true
      })
    }
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
        wx.uploadFile({
          url: config.service.upUrl + '123',
          filePath: tempFilePath,
          name: 'file',
          success: function(res) {
            console.log('文件上传成功，返回信息如下：');
            console.log(res.data);
          },
          fail: function(res) {},
          complete: function(res) {},
        })
        console.log("[Console log]:Record success!File path:" + tempFilePath);
        var myVoiceChat = { url: '', type: 'voice', duration: that.speakerSec, voiceImg: '/images/live/audio_icon_3.png', voiceTempFilepath: tempFilePath, avatarImg: currentUser.avatarUrl, nickName: currentUser.nickName, openId: currentUser.openId,roomid:123};
        console.log("[录音结束]")
        console.log(myVoiceChat)
        //that.addChat(myVoiceChat);
        wx.request({
          url: config.service.contentTest,
          data: myVoiceChat,
          header: {},
          method: 'POST',
          dataType: 'json',
          responseType: 'text',
          success: function(res) {
            console.log('-----------save content result');
            console.log(res.data);
          }
        })
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
      speakerUrl: '/images/live/speaker0.png',
      speakerSecond: 60,
      speakerSecondCls: 'speaker-sec'
    })
    clearInterval(that.speakerInterval);
    clearInterval(that.speakerSecInterval);
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
    var i = 0, k = 60, maxSec = 60;
    that.speakerInterval = setInterval(function () {
      i++;
      i = i % 7;
      that.setData({
        speakerUrl: that.data.speakerUrlPrefix + i + that.data.speakerUrlSuffix,
      });
      console.log("[Console log]:Speaker image changing...");
    }, 300);
    that.speakerSecInterval = setInterval(function(){
      k--;
      that.speakerSec = maxSec - k;
      if (k < 20 && k >=10) {
        that.setData({
          speakerSecond: k,
          speakerSecondCls: 'speaker-sec-notice'
        });
      } else if (k < 10 && k >= 0){
        that.setData({
          speakerSecond: k,
          speakerSecondCls: 'speaker-sec-warn'
        });
      }else if(k < 0){
        //处理语音超时
        wx.stopRecord();
        that.touchup.call();
      }else{
        that.setData({
          speakerSecond: k
        });
      }
    },1000) 
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
