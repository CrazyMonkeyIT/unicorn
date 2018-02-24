// pages/livechat/livechat.js
var app = getApp();
var that;
var chatListData = [{ chatid: 1, orientation: 'l', text: '这是一个小测试', type: 'text' },
{ chatid: 2, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png' }, { chatId: 3, orientation: 'l', text: '这是一个小测试', type: 'text' },
  { chatid: 4, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png' }, { chatid: 5, orientation: 'l', text: '这是一个小测试', type: 'text' },
  { chatid: 6, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png' }, { chatId: 7, orientation: 'l', text: '这是一个小测试', type: 'text' },
  { chatid: 8, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png' }, { chatid: 9, orientation: 'l', text: '这是一个小测试', type: 'text' },
  { chatid: 10, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png' }, { chatId: 11, orientation: 'l', text: '这是一个小测试', type: 'text' },
  { chatid: 12, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png' }, { chatid: 13, orientation: 'l', text: '这是一个小测试', type: 'text' },
{ chatid: 14, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/audio_icon_3.png' }, { chatId: 15, orientation: 'l', text: '这是一个小测试', type: 'text' },
  { chatid: 16, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png' }, { chatid: 17, orientation: 'l', text: '这是一个小测试', type: 'text' },
  { chatid: 18, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png' }, { chatId: 19, orientation: 'l', text: '这是一个小测试', type: 'text' },
  { chatid: 20, orientation: 'l', url: 'http://sc1.111ttt.cn/2017/1/11/11/304112004168.mp3', type: 'voice', duration: 5, voiceImg: '/images/live/audio_icon_3.png' }];
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
    speakerUrl: '/images/speaker0.png',
    speakerUrlPrefix: '/images/speaker',
    speakerUrlSuffix: '.png',
    filePath: null,
    contactFlag: true,
    imgUrls: [{
      id: 'i10282395', url: 'http://image.tupian114.com/20151104/10282395.jpg'
    },{
        id: 'i19234926', url: 'http://image.tupian114.com/20120410/19234926.jpg'
    }
    ]
  },
  onLoad: function () {
    console.log("[Console log]:Loading...");
    that = this;
    this.setData({ chatList: chatListData});
    app.getUserInfo(function (userInfo) {
      var aUrl = userInfo.avatarUrl;
      if (aUrl != null) {
        that.setData({
          userLogoUrl: aUrl
        });
      }
    });
    this.sendRequest(this.data.defaultCorpus);
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
  addChat: function (word, orientation) {
    that.addChatWithFlag(word, orientation, true);
  },
  // 增加对话到显示界面（scrolltopFlag为是否滚动标志）
  addChatWithFlag: function (word, orientation, scrolltopFlag) {
    let ch = { 'text': word, 'time': new Date().getTime(), 'orientation': orientation };
    chatListData.push(ch);
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
        this.voiceAnimate(chater);
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
  //下载语音并返回本地语音的临时地址
  downloadVoice : function (voiceUrl){
    wx.downloadFile({
      url: voiceUrl,
      header: {},
      success: function(res) {
        // 只要服务器有响应数据，就会把响应内容写入文件并进入 success 回调，业务需要自行判断是否下载到了想要的内容
        if (res.statusCode === 200) {
          return res.tempFilePath;
        }
      },
      fail: function(res) {
        return null;
      }
    })
  },
  //语音的动画效果 - 播放
  //判断语音对象中是否已经存了，如果已经存在则直接播放，不存在则下载
  voiceAnimatePlay: function (voiceObject) {
    voicePlaying = true;
    chartDetail = voiceObject;
    var voiceTempFilepath = voiceObject.voiceTempFilepath ? voiceObject.voiceTempFilepath : this.downloadVoice(voiceObject.url);
    if(voiceTempFilepath == null){
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
    //语音播放动画
    var i = 0;
    that.speakerInterval = setInterval(function () {
      i++;
      i = i % 3;
      chatTo.voiceImg = "/images/live/audio_icon_" + (i + 1) + ".png";
      that.setData({
        chatList: chatListData
      });
    }, 300);
    //播放语音，语音完成后执行停止播放
    wx.playVoice({
      filePath: voiceTempFilepath,
      duration: 0,
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {
        this.voiceAnimateStop(voiceObject);
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
      this.voiceAnimateStop(chatTo);
    } else if (chartDetail != null && chatTo != chartDetail && voicePlaying == true) {
      this.voiceAnimateStop(chartDetail);
      this.voiceAnimatePlay(chatTo);
    } else {
      this.voiceAnimatePlay(chatTo);
    }
  }
  
})
