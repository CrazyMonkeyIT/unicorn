
$(function () {
    //监听下拉框，显示/隐藏对应的输入框
   $("#advertisementType").click(function () {
       var type = $('#advertisementType option:selected').val();
       if(type == 1){
           $("#urlDiv").show();
           $("#roomDiv").hide();
           $("#lecturerDiv").hide();
       }else if(type == 2){
           $("#urlDiv").hide();
           $("#roomDiv").hide();
           $("#lecturerDiv").hide();
       }else if(type == 3){
           $("#urlDiv").hide();
           $("#roomDiv").hide();
           $("#lecturerDiv").hide();
       }else if(type == 4){
           $("#urlDiv").hide();
           $("#roomDiv").hide();
           $("#lecturerDiv").show();
       }else if(type == 5){
           $("#urlDiv").hide();
           $("#roomDiv").show();
           $("#lecturerDiv").hide();
       }
   });
});

/**
 * 保存广告信息
 */
function saveAdvertisementInfo(){
    var advertisementTitle = $("#editForm").find("input[name='advertisementTitle']").val();
    if(!advertisementTitle){
        alert("讲师等级名称不能为空");
        return;
    }
    var advertisementDesc = $("#editForm").find("input[name='advertisementDesc']").val();
    if(!advertisementDesc){
        alert("讲师等级名称不能为空");
        return;
    }
    var advertisementType = $("#editForm").find("input[name='advertisementType']").val();
    if(!advertisementType){
        alert("讲师等级名称不能为空");
        return;
    }
    var advertisementUrl = $("#editForm").find("input[name='advertisementUrl']").val();
    if(!advertisementUrl){
        alert("讲师等级名称不能为空");
        return;
    }
    var invalidDate = $("#editForm").find("input[name='invalidDate']").val();
    if(!invalidDate){
        alert("讲师等级名称不能为空");
        return;
    }
    $.ajax({
        url : $("#editForm").attr("action"),
        type : 'post',
        data : $("#editForm").serialize(),
        success : function(data) {
            if(data){
                location.href = basePath + "/advertisement/list";
            }else{
                alert("error");
            }
        }
    });
}

/**
 * 异步上传广告图片
 */
$(function(){
    $("#upFileBtn").click(function(){
        $("#imgForm").ajaxSubmit({
            dataType: 'json',
            success: function (data) {
                alert(data[0].filePath);
                $("#advertisementImgPath").val(data[0].filePath);
            },
            error: function (msg) {
               alert(msg);
            }
        });
    });
})

/**
 * 清空表单
 */
function clearForm(){
    $("#editForm")[0].reset();
    $("#editForm").find("input[name='userId']").val("");
}

/**
 * 显示新增等级窗口
 */
function showAddModal(){
    clearForm();
    $("#edit_grade_modal").modal("show");
}