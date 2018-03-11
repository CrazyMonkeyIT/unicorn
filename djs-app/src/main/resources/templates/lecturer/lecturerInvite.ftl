<@ui.layout >
<!-- zTree -->
<link rel="stylesheet" href="${request.contextPath}/static/ztree/zTreeStyle.css" type="text/css">
<script src="${request.contextPath}/static/ztree/jquery.ztree.all-3.5.js"></script>
<!-- My97 -->
<script src="${request.contextPath}/static/My97DatePicker/WdatePicker.js"></script>
<div class="col-xs-12">
    <!-- div.table-responsive -->

    <!-- div.dataTables_borderWrap -->

    <div>
        <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                        <button type="submit" class="btn btn-white btn-info">
                            <i class="glyphicon glyphicon-plus"></i>
                            创建邀请
                        </button>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer" >
                    <thead class="thin-border-bottom">
                    <tr >
                        <th >序号</th>
                        <th >讲师姓名</th>
                        <th >手机号</th>
                        <th >公司</th>
                        <th >职位</th>
                        <th >邀请码</th>
                        <th >状态</th>
                        <th ><i class="normal-icon ace-icon fa fa-clock-o"></i>创建时间</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#if page.list?? && (page.list?size > 0)>
                            <#list page.list as data>
                            <tr>
                                <td>${((page.pageNum-1) * 10) + (data_index+1)}</td>
                                <td>${data.lecturerName!''}</td>
                                <td>${data.phone!''}</td>
                                <td>${data.company!''}</td>
                                <td>${data.position!''}</td>
                                <td>${data.inviteCode!''}</td>
                                <td><#if data.createTime??>${(data.createTime?string('yyyy-MM-dd HH:mm:ss'))}</#if></td>
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
                <form id="editForm" action="${request.getContextPath()}/lecturerInvite/createInvite" method="post">
                    <div class="form-horizontal">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">讲师姓名</label>
                            <div class="col-sm-8">
                                <input name="userName" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">手机号</label>
                            <div class="col-sm-8">
                                <input name="loginName" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">讲师等级</label>
                            <div class="col-sm-8">

                            </div>
                        </div>

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a onclick="updateUser()" class="btn btn-white btn-info btn-bold">
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
// 分页查询
function submitForm(index){
    $("#pageIndex").val(index);
    $("#form1").submit();
}

</script>
</@ui.layout>