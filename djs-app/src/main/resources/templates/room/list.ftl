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
                <form class="form-inline" id="form1" role="form" action="${request.getContextPath()}/room/get/info" method="get">
                    <input id="pageIndex" name="pageIndex" value="${page.pageNum}" type="hidden" />
                    <input id="parStatus" value="${parStatus}" type="hidden" />
                    <span>&nbsp;&nbsp;房间状态</span>
                    :<select name="parStatus" id="statusSelect">
                        <option value ="999">全部</option>
                        <option value ="0">正常直播中</option>
                        <option value ="-2">禁言直播中</option>
                        <option value="1">直播结束</option>
                        <option value="-1">直播未开始</option>
                    </select>

                    <div class="input-group">
                        <input type="text" name="parStrName" value="${parStrName!}" class="form-control search-query" placeholder="直播间名称/讲师名称">
                        <span class="input-group-btn">
                            <button type="submit"  class="btn btn-white btn-info">
                                <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                                查询
                            </button>
                        </span>
                    </div>
                </form>
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
                            <td><span class="blue">${data.lecturerName!''}</span></td>
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
                            <td>${data.count!''}</td>
                            <td>
                                <div class="btn-overlap btn-group">
                                    <#if (data.status==0) || (data.status==-2)>
                                        <a onclick="closeRoom(${data.id})" class="btn btn-white btn-primary btn-bold" data-rel="tooltip" title="" data-original-title="关闭" title="关闭">
                                            <i class="fa fa-lock bigger-115 orange"></i>
                                        </a>
                                    </#if>
                                    <a onclick="showEditModal(${data.id})" class="btn btn-white btn-primary btn-bold" data-rel="tooltip" title=""
                                       data-original-title="详情"
                                       title="详情">
                                        <i class="fa fa-list bigger-110 grey"></i>
                                    </a>
                            </td>
                        </tr>
                        </#list>
                    <#else>
                    <tr style="text-align:center;">
                        <td colspan=9><h4 class="green">暂无数据</h4></td>
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
                <form id="editForm" action="" method="post">
                 <div class="form-horizontal">
                    <div style="float: left;width: 50%">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">房间名称</label>
                            <div class="col-sm-8">
                                <input name="name" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">讲师</label>
                            <div class="col-sm-8">
                                <input name="lecturerName" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">专题名</label>
                            <div class="col-sm-8">
                                <input name="subjectName" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">房间海报</label>
                            <div class="col-sm-8">
                                <input name="roomPosterPath" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">价格</label>
                            <div class="col-sm-8">
                                <input name="roomPrice" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">预计开始</label>
                            <div class="col-sm-8">
                                <input name="prepareLiveBeginTime" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">预计结束</label>
                            <div class="col-sm-8">
                                <input name="prepareLiveEndTime" type="text"  />
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal" style="float: left;width: 40%">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">总人数</label>
                            <div class="col-sm-8">
                                <input name="count" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">类型</label>
                            <div class="col-sm-8">
                                <input name="type" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">状态</label>
                            <div class="col-sm-8">
                                <input name="status" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">预告封面</label>
                            <div class="col-sm-8">
                                <input name="heraldPath" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">邀请码</label>
                            <div class="col-sm-8">
                                <input name="inviteCode" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">实际开始</label>
                            <div class="col-sm-8">
                                <input name="actualLiveBeginTime" type="text"  />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label">实际结束</label>
                            <div class="col-sm-8">
                                <input name="actualLiveEndTime" type="text"  />
                            </div>
                        </div>
                    </div>
              </form>
            </div>
            </div>
            <div class="modal-footer" style="margin-top: 50%">
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
        $("#editForm").find('input').attr('disabled',true);
        var parStatus = $('#parStatus').val();
        $('#statusSelect').find("option[value = '"+parStatus+"']").attr('selected','selected');
    });


    /**
     * 关闭直播间
     */
    function closeRoom(roomId){
        if(confirm("确认强制关闭直播间吗？")){
            $.ajax({
                url : basePath+"/room/update/"+roomId,
                type : 'post',
                success : function(data) {
                    window.location.reload();
                    alert(data?'操作成功！':'操作失败！');
                }
            });
        }
    }

    /**
     * 获取直播间信息
     */
    function showEditModal(roomId){
        $.ajax({
            url : basePath+"/room/get/"+roomId,
            type : 'get',
            success : function(data) {
                $("#editForm").find("input[name='name']").val(data.name);
                $("#editForm").find("input[name='type']").val(data.type===0?'VIP':'路演');
                $("#editForm").find("input[name='count']").val(data.count);
                var status = data.status;
                if(status === 0){
                    status = '正常直播中';
                }else if(status === 1){
                    status = '直播结束';
                }else if(status === -1){
                    status = '直播未开始';
                }else if(status === -2){
                    status = '禁言直播中';
                }
                $("#editForm").find("input[name='status']").val(status);
                $("#editForm").find("input[name='roomPrice']").val(data.roomPrice);
                $("#editForm").find("input[name='prepareLiveBeginTime']").val(data.prepareLiveBeginTime);
                $("#editForm").find("input[name='prepareLiveEndTime']").val(data.prepareLiveEndTime);
                $("#editForm").find("input[name='actualLiveBeginTime']").val(data.actualLiveBeginTime);
                $("#editForm").find("input[name='actualLiveEndTime']").val(data.actualLiveEndTime);
                $("#editForm").find("input[name='subjectName']").val(data.subjectName);
                $("#editForm").find("input[name='lecturerName']").val(data.lecturerName);
                $("#editForm").find("input[name='roomPosterPath']").val(data.roomPosterPath);
                $("#editForm").find("input[name='heraldPath']").val(data.heraldPath);
                $("#editForm").find("input[name='inviteCode']").val(data.inviteCode);
                $("#edit_user_modal").modal("show");
            }
        });
    }
</script>
</@ui.layout>