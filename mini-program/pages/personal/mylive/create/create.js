Page({
  onLoad: function () {
    this.setData({
      savedFilePath: wx.getStorageSync('savedFilePath')
    })
  },
  data: {
    roomPosterTemp: '',
    subjectArray: ['中国', '美国', '巴西', '日本'],
    subjectIndex:0,
    roomTypeArray:['VIP','路演'],
    roomTypeIndex:0,
    beginDate:'2018-01-01',
    endDate:'2018-12-31',
    beginTime:'00:00',
    endTime:'23:59'
  },
  chooseImage: function () {
    var that = this
    wx.chooseImage({
      count: 1,
      success: function (res) {
        that.setData({
          roomPosterTemp: res.tempFilePaths[0]
        })
      }
    })
  },
  bindSubjectChange: function (e) {
    this.setData({
      subjectIndex: e.detail.value
    })
  },
  bindRoomTypeChange: function (e) {
    this.setData({
      roomTypeIndex: e.detail.value
    })
  },
  bindBeginDateChange:function (e){
    this.setData({
      beginDate: e.detail.value
    })
  },
  bindEndDateChange: function (e) {
    this.setData({
      endDate: e.detail.value
    })
  },
  bindBeginTimeChange: function (e) {
    this.setData({
      beginTime: e.detail.value
    })
  },
  bindEndTimeChange: function (e) {
    this.setData({
      endTime: e.detail.value
    })
  },
  /** 取消 */
  clean:function(){
    wx.navigateBack();
  }
})
