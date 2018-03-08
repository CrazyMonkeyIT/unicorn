<@ui.layout >

<div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
    <div class="row">
        <form class="form-inline" id="form1" role="form" action="${request.getContextPath()}/system/user/list" method="post">
            <a onclick="showAddModal()" class="btn btn-white btn-info">
                <i class="glyphicon glyphicon-plus"></i>
                新增广告
            </a>
        </form>
    </div>

    <table class="table table-striped table-bordered table-hover dataTable no-footer" >
        <thead class="thin-border-bottom">
            <tr >
                <th >序号</th>
                <th >广告标题</th>
                <th >广告描述</th>
                <th >广告类型</th>
                <th >广告连接</th>
                <th >广告图片</th>
                <th >到期时间</th>
                <th >广告状态</th>
                <th >操作</th>
            </tr>
        </thead>
        <tbody>
            <#list page.list as data>
            <tr>
                <td>${((page.pageNum-1) * 10) + (data_index+1)}</td>
                <td>${data.advertisementTitle!''}</td>
                <td>${data.advertisementDesc!''}</td>
                <td>asd</td>
                <td>${data.advertisementUrl!''}</td>
                <td>图片</td>
                <td>2018-4-1</td>
                <td>${data.status!''}</td>
                <td>
                    <a class="btn btn-white btn-primary btn-bold"  data-rel="tooltip" title="" data-original-title="修改" title="修改">
                        <i class="fa fa-pencil bigger-110 green" ></i>
                    </a>
                    <a class="btn btn-white btn-primary btn-bold" data-rel="tooltip" title="" data-original-title="删除" title="删除">
                        <i class="fa fa-trash-o bigger-110 red"></i>
                    </a>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>

<!--等级新增/修改div开始-->
<div id="edit_grade_modal" class="modal fade" style="display: none; " data-backdrop="static" role="dialog" tabindex="-1" class="modal fade in exam_newbox">
    <div class="modal-dialog">
        <div class="modal-content"  >
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4 class="blue"><i class="fa fa-pencil"></i>编辑广告</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" action="${request.getContextPath()}/system/user/updateUser" method="post">
                    <div class="form-horizontal">
                        <!-- 用户ID -->
                        <input type="hidden" name="advertisementId" />

                        <div class="form-group ">
                            <label class="col-sm-4 control-label">广告标题</label>
                            <div class="col-sm-8">
                                <input name="advertisementTitle" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">广告描述</label>
                            <div class="col-sm-8">
                                <input name="advertisementDesc" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">广告类型</label>
                            <div class="col-sm-8">
                                <input name="advertisementType" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">广告连接</label>
                            <div class="col-sm-8">
                                <input name="advertisementUrl" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">广告图片</label>
                            <div class="col-sm-8">
                                <input name="advertisementImg" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">到期时间</label>
                            <div class="col-sm-8">
                                <input name="invalidDate" type="text"  />
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a class="btn btn-white btn-info btn-bold">
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
<!--等级新增/修改div结束-->

<script >
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
</script>

</@ui.layout>