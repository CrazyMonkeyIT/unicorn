<@ui.layout >
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${request.contextPath}/static/assets/css/colorbox.min.css" />
<script src="${request.contextPath}/static/assets/js/jquery.colorbox.min.js"></script>

<div class="col-xs-12">

    <div>
        <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
                <div class="row" style="display: none;">
                    <form class="form-inline" id="form1" role="form" action="${request.getContextPath()}/lecturer/invite/list" method="post">
                        <input id="pageIndex" name="pageIndex" value="${page.pageNum}" type="hidden" />
                    </form>
                </div>
                <div class="row">
                        <button onclick="showModal()" class="btn btn-white btn-info">
                            <i class="glyphicon glyphicon-plus"></i>
                            创建邀请
                        </button>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer" >
                    <thead class="thin-border-bottom">
                    <tr >
                        <th >序号</th>
                        <th >头像</th>
                        <th >讲师姓名</th>
                        <th >手机号</th>
                        <th >公司</th>
                        <th >职位</th>
                        <th >邀请码</th>
                        <th >状态</th>
                        <th ><i class="normal-icon ace-icon fa fa-clock-o"></i>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#if page.list?? && (page.list?size > 0)>
                            <#list page.list as data>
                            <tr>
                                <td>${((page.pageNum-1) * 10) + (data_index+1)}</td>
                                <td>
                                <#if data.headPhotoFile??><img src="${data.headPhotoFile!''}" style="width:60px;height:60px;border-radius:12px;"/>
                                <#else>
                                    <i class="ace-icon ace-icon fa fa-picture-o" style="width:60px;height:60px;border-radius:12px;font-size:56px;color:#ccc;"></i>
                                </#if>
                                </td>
                                <td>${data.lecturerName!''}</td>
                                <td>${data.phone!''}</td>
                                <td>${data.company!''}</td>
                                <td>${data.position!''}</td>
                                <td>${data.inviteCode!''}</td>
                                <td>
                                    <#if data.status == 'NOTALLOW'>
                                        <span class="label label-yellow label-white middle">待接受</span>
                                    </#if>
                                    <#if data.status == 'ALLOW'>
                                        <span class="label label-success label-white middle">已接受</span>
                                    </#if>
                                </td>
                                <td><#if data.createTime??>${(data.createTime?string('yyyy-MM-dd HH:mm:ss'))}</#if></td>
                                <td>
                                    <a onclick="toEdit(${data.id})" class="btn btn-sm btn-white btn-success" >
                                        <i class="fa fa-pencil bigger-110 green"></i>
                                        编辑
                                    </a>
                                    <a onclick="deleteInvite(${data.id})" class="btn btn-sm btn-white btn-danger" >
                                        <i class="fa fa-trash-o bigger-110 red"></i>
                                        删除
                                    </a>
                                </td>
                            </tr>
                            </#list>
                        <#else>
                        <tr style="text-align:center;">
                           <td colspan="9"><h4 class="green">暂无记录</h4></td>
                        </tr>
                        </#if>
                    </tbody>
                </table>
            <div class="row">
                <!-- 分页begin -->
                <#if (page.pages>0)>
                    <#import "../macro/pagination.ftl" as cast/>
                    <@cast.pagination callFunName="submitForm" />
                </#if>
                <!-- 分页end -->
            </div>
        </div>
    </div>
</div>
<!-- 编辑邀请信息begin -->
<div id="invite_modal" class="modal fade" style="display: none; " data-backdrop="static" role="dialog" tabindex="-1" class="modal fade in exam_newbox">
    <div class="modal-dialog">
        <div class="modal-content"  >
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4 class="blue"><i class="fa fa-pencil"></i>&nbsp;创建邀请</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" action="${request.getContextPath()}/lecturer/invite/create" method="post">
                    <input type="hidden" name="id"/>
                    <div class="form-horizontal">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-8" style="padding-left:50px;">
                                <input type="hidden" name="headPhotoFile"/>
                                <!-- 上传文件域begin -->
                                <label id="addImg" class="ace-file-input ace-file-multiple" style="width:143px;">
                                    <input multiple="" id="file" type="file" >
                                </label>
                                <!-- 上传文件域end -->
                                <div id="editImgDiv" style="display: none;width:130px;text-align: center;">
                                    <img onclick="showAddImg()" src="" id="editImg" style="width:130px;height:126px;"/>
                                    <br/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">讲师姓名</label>
                            <div class="col-sm-8">
                                <input name="lecturerName" type="text"  style="width:243px;"/>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">联系方式</label>
                            <div class="col-sm-8">
                                <input name="phone" type="text"  style="width:243px;"/>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">讲师等级</label>
                            <div class="col-sm-8">
                                <select name="gradeId" style="width:243px;">
                                    <#list gradeList as grade>
                                        <option value="${grade.lecturerGradeId}">${grade.gradeName}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">在职公司</label>
                            <div class="col-sm-8">
                                <input name="company" type="text"  style="width:243px;"/>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">所在职位</label>
                            <div class="col-sm-8">
                                <input name="position" type="text"  style="width:243px;"/>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">简介</label>
                            <div class="col-sm-8">
                                <textarea name="introduction" rows="5" cols="32" style="resize: none;"></textarea>
                            </div>
                        </div>
                        <div class="form-group " id="inviteCodeGroup">
                            <label class="col-sm-4 control-label">邀请码</label>
                            <div class="col-sm-8">
                                 <input type="hidden" name="inviteCode"/>
                                 <label id="inviteCode" style="line-height: 32px;font-weight: bold;" class="green"></label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a onclick="create()" class="btn btn-white btn-info btn-bold">
                    <i class="ace-icon glyphicon glyphicon-ok blue"></i>
                    保存
                </a>
                <a class="btn btn-white btn-info btn-bold" data-dismiss="modal">
                    <i class="ace-icon glyphicon glyphicon-remove blue"></i>
                    取消
                </a>
            </div>
        </div>
    </div>
</div>
<!-- 编辑邀请信息end -->
<script>
jQuery(function($) {
    var inviteCode = RndNum(6);
    $("#inviteCode").html(inviteCode);
    $("input[name='inviteCode']").val(inviteCode);


    $('#file').ace_file_input({
        style: 'well',
        btn_choose: '讲师头像',
        btn_change: null,
        showRemove: false,
        no_icon: ' ace-icon ace-icon fa fa-picture-o',
        droppable: false,
        thumbnail: 'fit',//large | fit
        allowExt: ['.png','.jpg','.gif', '.bmp','.jpeg'],
        preview_error: function (filename, error_code) {

        }
    }).on('change', function () {
        uploadFile();
    });
});
// 分页查询
function submitForm(index){
    $("#pageIndex").val(index);
    $("#form1").submit();
}
function showModal(){
    $("#editImgDiv").hide();
    $("#addImg").show();
    $("#inviteCodeGroup").show();
    $("#editForm")[0].reset();
    $("#invite_modal").modal("show");
}
/**
 * 创建邀请
 */
function create(){
    $.ajax({
        url : $("#editForm").attr("action"),
        type : 'post',
        dataType:'json',
        data : $("#editForm").serialize(),
        success : function(data) {
            if(data.result){
                $("#form1").submit();
            }else{
                alert(data.msg);
            }
        }
    });
}
//产生随机数函数
function RndNum(n){
    var rnd="";
    for(var i=0;i<n;i++)
        rnd+=Math.floor(Math.random()*10);
    return rnd;
}

/**
 * 上传文件
 */
function uploadFile(){
    var formData = new FormData();
    var file = $("#file")[0].files[0];
    formData.append("file",file);
    formData.append("toConvertPic",false);
    loading_begin();
    $.ajax({
        url:basePath+"/import/up/lecturerHeadPhoto",
        type:"post",
        data:formData,
        processData: false,  //必须
        contentType: false,  //必须
        success:function(data){
            if(!!data){
                $("input[name='headPhotoFile']").val(data[0].filePath);
            }else{
                alert("上传异常");
            }
            loading_end();
        },error:function(){
            loading_end();
        }
    });

}

/**
 * 删除
 */
function deleteInvite(id) {
    Ewin.confirm({ message: "确认要删除该邀请吗？" }).on(function () {
        $.ajax({
            url: basePath + "/lecturer/invite/del",
            type: "post",
            dataType: 'json',
            data: {
                id: id
            },
            success: function (data) {
                if (data.result) {
                    $("#form1").submit();
                } else {
                    alert(result.message);
                }
            }, error: function () {
            }
        });
    });
}
//进入编辑
function toEdit(id) {
    $.ajax({
        url: basePath + "/lecturer/invite/get",
        type: "post",
        dataType: 'json',
        data: {
            id: id
        },
        success: function (data) {
            if (data.result) {
                $("input[name='id']").val(data.obj.id);
                $("#editImg").attr("src",data.obj.headPhotoFile);
                $("#editImgDiv").show();
                $("#addImg").hide();
                $("input[name='lecturerName']").val(data.obj.lecturerName);
                $("input[name='company']").val(data.obj.company);
                $("input[name='position']").val(data.obj.position);
                $("select[name='gradeId']").val(data.obj.gradeId);
                $("input[name='phone']").val(data.obj.phone);
                $("input[name='headPhotoFile']").val(data.obj.headPhotoFile);
                $("textarea[name='introduction']").val(data.obj.introduction);
                $("#inviteCodeGroup").hide();
                $("#invite_modal").modal("show");
            } else {
                alert(result.message);
            }
        }, error: function () {
        }
    });
}

function showAddImg(){
    $("#editImgDiv").hide();
    $("#addImg").show();
    $("#addImg").trigger("click");
}
</script>

</@ui.layout>