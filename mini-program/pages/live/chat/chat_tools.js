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
const chatContent = (type, currentUser, roomId, url, duration,tempFilepath) => {
  let content = {
    id: util.uuid(roomId),type: type, avatarUrl: currentUser.avatarUrl, 
    nickName: currentUser.nickName, openId: currentUser.openId, 
    roomid: roomId, userId: currentUser.id, url: url, duration: duration,orientation: 'r',
    tempFilepath :tempFilepath };
  if(type == 'voice'){
    content.voiceImg = '/images/live/audio_icon_3.png';
  }

  return content;
}


module.exports = {
  getImgs: getImgs,
  getImg: getImg,
  chatContent: chatContent
}