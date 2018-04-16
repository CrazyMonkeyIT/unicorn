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
                <input id="pageIndex" name="pageIndex" value="${page.pageNum}" type="hidden" />
                <span>&nbsp;&nbsp;房间状态</span>
                :<select name="carlist">
                    <option value ="0">正常直播中</option>
                    <option value ="-2">禁言直播中</option>
                    <option value="1">直播结束</option>
                    <option value="-1">直播未开始</option>
                </select>

                <div class="input-group">
                    <input type="text" name="roomName" value="${roomName!}" class="form-control search-query" placeholder="直播间名称/讲师名称">
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-white btn-info">
                            <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                            查询
                        </button>
                    </span>
                </div>
            </div>
            <table class="table table-striped table-bordered table-hover dataTable no-footer" >
                <thead class="thin-border-bottom">
                <tr >
                    <th>序号</th>
                    <th>讲师</th>
                    <th>房间名称</th>
                    <th>房间类型</th>
                    <th><i class="normal-icon ace-icon fa fa-clock-o"></i>开始时间</th>
                    <th><i class="normal-icon ace-icon fa fa-clock-o"></i>结束时间</th>
                    <th>房间状态</th>
                    <th>房间人数</th>
                    <th><i class="ace-icon fa fa-wrench"></i>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if page.list?? && (page.list?size > 0)>
                        <#list page.list as data>
                        <tr>
                            <td>${((page.pageNum-1) * 10) + (data_index+1)}</td>
                            <td><span class="blue">${data.lecturerId!''}</span></td>
                            <td>${data.name!''}</td>
                            <td>
                                <#if (data.type==0)>
                                    <span class="label label-success label-white middle">VIP</span>
                                <#elseif (data.type==1)>
                                    <span class="label label-yellow label-white middle">路演</span>
                                </#if>
                            </td>
                            <td><#if data.prepareLiveBeginTime??>${(data.prepareLiveBeginTime?string('yyyy-MM-dd HH:mm:ss'))}</#if></td>
                            <td><#if data.prepareLiveEndTime??>${(data.prepareLiveEndTime?string('yyyy-MM-dd HH:mm:ss'))}</#if></td>
                            <td>
                                <#if (data.status==0)>
                                    <span class="label label-success label-white middle">正常直播中</span>
                                <#elseif (data.status==1)>
                                    <span class="label label-yellow label-white middle">直播结束</span>
                                <#elseif (data.status==-1)>
                                    <span class="label label-white middle">直播未开始</span>
                                <#elseif (data.status==-2)>
                                    <span class="label label-success label-white middle">禁言直播中</span>
                                </#if>
                            </td>
                            <td>1</td>
                            <td>
                                <div class="btn-overlap btn-group">
                                    <a onclick="showEditModal(1)" class="btn btn-white btn-primary btn-bold"  data-rel="tooltip" title="" data-original-title="修改" title="修改">
                                        <i class="fa fa-pencil bigger-110 green" ></i>
                                    </a>
                                    <a onclick="" class="btn btn-white btn-primary btn-bold" data-rel="tooltip" title="" data-original-title="详情" title="详情">
                                        <i class="fa fa-list bigger-110 grey"></i>
                                    </a>
                            </td>
                        </tr>
                        </#list>
                    <#else>
                    <tr style="text-align:center;">
                        <td colspan="8"><h4 class="green">暂无数据</h4></td>
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
<!-- 编辑用户信息begin -->
<div id="edit_user_modal" class="modal fade" style="display: none; " data-backdrop="static" role="dialog" tabindex="-1" class="modal fade in exam_newbox">
    <div class="modal-dialog">
        <div class="modal-content"  >
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4 class="blue"><i class="fa fa-pencil"></i>&nbsp;编辑直播间信息</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" action="${request.getContextPath()}/system/user/updateUser" method="post">
                 <div class="form-horizontal">
                    <div id="edit_div_l" style="float: left;width: 50%">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">姓名</label>
                            <div class="col-sm-8">
                                <input name="userName" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">登录名</label>
                            <div class="col-sm-8">
                                <input name="loginName" type="text"  />
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal" id="edit_div_r" style="float: left;width: 40%">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">姓名</label>
                            <div class="col-sm-8">
                                <input name="userName" type="text"  />
                            </div>
                        </div>
                    </div>
              </form>
            </div>
            </div>
            <div class="modal-footer" style="margin-top: 90px">
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
<script>
    jQuery(function($) {
        $('[data-rel=tooltip]').tooltip();
    });

    /**
     * 获取直播间信息
     */
    function showEditModal(loginName){
        $("#edit_user_modal").modal("show");
    }
</script>
</@ui.layout>