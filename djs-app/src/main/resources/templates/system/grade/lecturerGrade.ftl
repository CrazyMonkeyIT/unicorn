<@ui.layout >
<!-- zTree -->
<link rel="stylesheet" href="${request.contextPath}/static/ztree/zTreeStyle.css" type="text/css">
<script src="${request.contextPath}/static/ztree/jquery.ztree.all-3.5.js"></script>
<!-- My97 -->
<script src="${request.contextPath}/static/My97DatePicker/WdatePicker.js"></script>

<div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
    <div class="row">
        <form class="form-inline" id="form1" role="form" action="${request.getContextPath()}/system/user/list" method="post">
            <a onclick="showAddModal()" class="btn btn-white btn-info">
                <i class="glyphicon glyphicon-plus"></i>
                创建等级
            </a>
        </form>
    </div>

    <table class="table table-striped table-bordered table-hover dataTable no-footer" >
        <thead class="thin-border-bottom">
        <tr >
            <th >序号a</th>
            <th >等级名称</th>
            <th >分成比例</th>
            <th >优先级</th>
            <th >操作</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>等级A</td>
                <td>10</td>
                <td>1</td>
                <td>
                    <a class="btn btn-white btn-primary btn-bold"  data-rel="tooltip" title="" data-original-title="修改" title="修改">
                        <i class="fa fa-pencil bigger-110 green" ></i>
                    </a>
                    <a class="btn btn-white btn-primary btn-bold" data-rel="tooltip" title="" data-original-title="删除" title="删除">
                        <i class="fa fa-trash-o bigger-110 red"></i>
                    </a>
                </td>
            </tr>
            <tr>
                <td>1</td>
                <td>等级B</td>
                <td>20</td>
                <td>2</td>
                <td>
                    <a class="btn btn-white btn-primary btn-bold"  data-rel="tooltip" title="" data-original-title="修改" title="修改">
                        <i class="fa fa-pencil bigger-110 green" ></i>
                    </a>
                    <a class="btn btn-white btn-primary btn-bold" data-rel="tooltip" title="" data-original-title="删除" title="删除">
                        <i class="fa fa-trash-o bigger-110 red"></i>
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
</div>

<!--等级新增/修改div开始-->
<div id="edit_grade_modal" class="modal fade" style="display: none; " data-backdrop="static" role="dialog" tabindex="-1" class="modal fade in exam_newbox">
    <div class="modal-dialog">
        <div class="modal-content"  >
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4 class="blue"><i class="fa fa-pencil"></i>编辑等级</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" action="${request.getContextPath()}/system/user/updateUser" method="post">
                    <div class="form-horizontal">
                        <!-- 用户ID -->
                        <input type="hidden" name="userId" />

                        <div class="form-group ">
                            <label class="col-sm-4 control-label">等级名称</label>
                            <div class="col-sm-8">
                                <input name="userName" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">分成比例</label>
                            <div class="col-sm-8">
                                <input name="loginName" type="text"  />
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