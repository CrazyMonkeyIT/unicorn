const util = require("../../../global-js/util.js")

const getImgs = (chatListData) => {
  let imgs = [];
  for (let i = 0; i < chatListData.length; i++) {
    let chatContent = chatListData[i];
    if (chatContent.type == 'image') {
      imgs.push(chatContent.url);
    }
  }
  return imgs;
}

const getImg = (id, chatListData) => {
  let img = null;
  for (let i = 0; i < chatListData.length;i++){
    let chatContent = chatListData[i];
    if (chatContent.type == 'image' && chatContent.id == id) {
      img = chatContent.url;
    }
  }
  return img;
}

/**聊天内容 */
const chatContent = (type, currentUser, roomId, url, duration,tempFilepath,content) => {
  let cont = {
    id: util.uuid(roomId),type: type, avatarUrl: currentUser.avatarUrl, 
    nickName: currentUser.nickName, openId: currentUser.openId, 
    roomid: roomId, userId: currentUser.id, url: url, duration: duration,orientation: 'r',
    tempFilepath: tempFilepath, chatType: 'msg', content: content };
  if(type == 'voice'){
    cont.voiceImg = '/images/live/audio_icon_3.png';
  } 
  return cont;
}
/**房间事件内容 */
const chatEvent = (eventType,currentUser,roomId) => {
  let eventContent = {
    id: util.uuid(roomId), chatType: 'event', eventType: eventType, executor: currentUser.id, roomId: roomId
  }
  return eventContent;
}


module.exports = {
  getImgs: getImgs,
  getImg: getImg,
  chatContent: chatContent,
  chatEvent: chatEvent
}