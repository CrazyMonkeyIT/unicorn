<@ui.layout >
<div class="col-xs-12">

    <div>
        <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
            <div class="row" style="display: none;">
                <form class="form-inline" id="form1" role="form" action="${request.getContextPath()}/lecturer/register/list" method="post">
                    <input id="pageIndex" name="pageIndex" value="${page.pageNum}" type="hidden" />
                </form>
            </div>

            <table class="table table-striped table-bordered table-hover dataTable no-footer" >
                <thead class="thin-border-bottom">
                <tr >
                    <th >序号</th>
                    <th>头像</th>
                    <th >讲师姓名</th>
                    <th >手机号</th>
                    <th >公司</th>
                    <th >职位</th>
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
                            <td>
                                <#if data.status == 'WAIT'>
                                    <span class="label label-yellow label-white middle">等待审核</span>
                                </#if>
                                <#if data.status == 'FAILURE'>
                                    <span class="label label-white middle">未通过</span>
                                </#if>
                                <#if data.status == 'SUCCESS'>
                                    <span class="label label-success label-white middle">已通过</span>
                                </#if>
                            </td>
                            <td><#if data.createTime??>${(data.createTime?string('yyyy-MM-dd HH:mm:ss'))}</#if></td>
                            <td>
                                <a onclick="getInfo(${data.id})" class="btn btn-white btn-info btn-sm">
                                    <i class="ace-icon fa fa-search nav-search-icon"></i>
                                    查看
                                </a>
                                <#if data.status != 'WAIT' >
                                <#else >

                                        <a onclick="showSuccess(${data.id})" class="btn btn-sm btn-white btn-primary" >
                                            <i class="ace-icon glyphicon glyphicon-ok blue"></i>
                                            通过
                                        </a>
                                        <a onclick="failure(${data.id})" class="btn btn-sm btn-white btn-primary" >
                                            <i class="ace-icon glyphicon glyphicon-remove blue"></i>
                                            拒绝
                                        </a>
                                </#if>
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
<!-- 审核通过begin -->
<div id="success_modal" class="modal fade" style="display: none; " data-backdrop="static" role="dialog" tabindex="-1" class="modal fade in exam_newbox">
    <div class="modal-dialog">
        <div class="modal-content"  >
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4 class="blue"><i class="fa fa-pencil"></i>&nbsp;创建邀请</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" action="${request.getContextPath()}/lecturer/register/examine" method="post">
                    <div class="form-horizontal">
                        <input id="registerId" type="hidden"/>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">讲师等级</label>
                            <div class="col-sm-8">
                                <select id="gradeId">
                                    <#list gradeList as grade>
                                        <option value="${grade.lecturerGradeId}">${grade.gradeName}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
            <div class="modal-footer">

                <a onclick="success()" class="btn btn-white btn-info btn-bold">
                    <i class="ace-icon glyphicon glyphicon-ok blue"></i>
                    确定
                </a>
                <a class="btn btn-white btn-info btn-bold" data-dismiss="modal">
                    <i class="ace-icon glyphicon glyphicon-remove blue"></i>
                    取消
                </a>
            </div>
        </div>
    </div>
</div>
<!-- 审核通过end -->
<!-- 预览信息begin -->
<div id="preview_modal" class="modal fade" style="display: none; " data-backdrop="static" role="dialog" tabindex="-1" class="modal fade in exam_newbox">
    <div class="modal-dialog">
        <div class="modal-content"  >
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4 class="blue"><i class="fa fa-pencil"></i>&nbsp;讲师信息</h4>
            </div>
            <div class="modal-body">
                    <div class="form-horizontal">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-8" style="padding-left:50px;">
                                <div id="editImgDiv" style="width:130px;text-align: center;">
                                    <img src="" id="headImg" style="width:130px;height:126px;"/>
                                    <br/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">讲师姓名：</label>
                            <div class="col-sm-8" style="padding-top: 8px;">
                                <span id="lecturerName"></span>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">联系方式：</label>
                            <div class="col-sm-8" style="padding-top: 8px;">
                                <span id="phone"></span>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">在职公司：</label>
                            <div class="col-sm-8" style="padding-top: 8px;">
                                <span id="company"></span>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">所在职位：</label>
                            <div class="col-sm-8" style="padding-top: 8px;">
                                <span id="position"></span>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">简介：</label>
                            <div class="col-sm-8" style="padding-top: 8px;">
                                <span id="introduction"></span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a class="btn btn-white btn-info btn-bold" data-dismiss="modal">
                    <i class="ace-icon glyphicon glyphicon-remove blue"></i>
                    关闭
                </a>
            </div>
        </div>
    </div>
</div>
<!-- 预览信息end -->
<script>
    jQuery(function($) {

    });
    // 分页查询
    function submitForm(index){
        $("#pageIndex").val(index);
        $("#form1").submit();
    }
    function showSuccess(id){
        $("#registerId").val(id);
        $("#success_modal").modal("show");
    }
    /**
     * 通过
     */
    function success(){
        $.ajax({
            url : $("#editForm").attr("action"),
            type : 'post',
            dataType:'json',
            data : {
                id:$("#registerId").val(),
                examineResult:"SUCCESS",
                gradeId:$("#gradeId").val()
            },
            success : function(data) {
                console.log(data);
                if(data.result){
                    $("#form1").submit();
                }else{
                    alert(data.msg);
                }
            }
        });
    }
   function failure(id){
       $.ajax({
           url : $("#editForm").attr("action"),
           type : 'post',
           dataType:'json',
           data : {
               id:id,
               examineResult:"FAILURE"
           },
           success : function(data) {
               if(data.result){
                   $("#form1").submit();
               }else{
                   alert(data.msg);
               }
           }
       });
   }

   function getInfo(id){
       $.ajax({
           url : basePath + "/lecturer/register/get",
           type : 'post',
           dataType:'json',
           data : {
               id:id
           },
           success : function(data) {
               if(!!data){
                    $("#headImg").attr("src",data.headPhotoFile);
                    $("#lecturerName").html(data.lecturerName);
                    $("#phone").html(data.phone);
                    $("#company").html(data.company);
                    $("#position").html(data.position);
                    $("#introduction").html(data.introduction);
                    $("#preview_modal").modal("show");
               }else{

               }
           }
       });
   }
</script>
</@ui.layout>