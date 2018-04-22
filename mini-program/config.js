/**
 * 小程序配置文件
 */

// 此处主机域名修改成腾讯云解决方案分配的域名
var host = 'https://health100.cnsoc.org/unicorn';//'https://dujiaoshouzhiku.com/unicorn'//'http://localhost:9090/unicorn'; //
var wsshost = 'wss://health100.cnsoc.org/unicorn/websocket/endpointChat'
//'wss://dujiaoshouzhiku.com/unicorn/websocket/endpointChat';//'ws://localhost:9090/unicorn/websocket/endpointChat'; //

var config = {

  // 下面的地址配合云端 Demo 工作
  service: {
    host:host,
    //保存用户信息
    saveUser:host + '/minigram/saveUser',
    //获取用户加密信息的解密文档
    getauth: host + '/minigram/getauth',
    //上传文件URL
    upUrl : host + '/import/up/',
    //获取房间历史聊天记录
    roomHistory: host + '/minigram/roomHistory',
    //存储聊天内容的临时url
    contentTest: host + '/minigram/contentTest',
    socketUrl: wsshost
  }
};

module.exports = config;