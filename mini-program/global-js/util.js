const config = require("../config.js")

const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

const uuid = chatId => {
  const mydate = new Date();
  const uuid = "cms" + chatId + mydate.getDay() + mydate.getHours() + mydate.getMinutes() + mydate.getSeconds() + mydate.getMilliseconds() + Math.round(Math.random() * 10000);
  return uuid
}
/** 日期格式化 yyyy-mm-dd hh:mi:ss */
const getNowFormatDate = function (date) {
  var seperator1 = "-";
  var seperator2 = ":";
  var month = date.getMonth() + 1;
  var strDate = date.getDate();
  var hour = date.getHours();
  var min = date.getMinutes();
  var ss = date.getSeconds();
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (strDate >= 0 && strDate <= 9) {
    strDate = "0" + strDate;
  }
  if (hour >= 0 && hour <= 9) {
    hour = "0" + hour;
  }
  if (min >= 0 && min <= 9) {
    min = "0" + min;
  }
  if (ss >= 0 && ss <= 9) {
    ss = "0" + ss;
  }
  var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
    + " " + hour + seperator2 + min
    + seperator2 + ss;
  return currentdate;
}
/**获得短链接 */
const getShortLink = function(longLink, callback){
  wx.request({
    url: config.service.host + '/minigram/getShortLink',
    dataType: 'json',
    data: {
      longLink: longLink
    },
    header: {
      'content-type': 'application/json'
    },
    success: function (res) {
      if (!!res.data) {
        callback(res.data);
      } else {
        showAlter("短链接获取失败");
      }
    }
  })
}
const showAlter = function (content) {
  wx.showToast({
    title: content,
    icon: 'none',
    duration: 2000
  })
}

module.exports = {
  formatTime: formatTime,
  uuid :uuid,
  getNowFormatDate: getNowFormatDate,
  getShortLink: getShortLink
}
