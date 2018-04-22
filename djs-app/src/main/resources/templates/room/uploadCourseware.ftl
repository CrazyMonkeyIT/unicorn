<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>上传直播课件</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${request.contextPath}/static/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/jquery-ui.custom.min.css">
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/jquery.gritter.min.css">
    <!-- text fonts -->
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/fonts.googleapis.com.css" />
    <!-- ace styles -->
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <![endif]-->
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/ace-rtl.min.css" />
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/ace-ie.min.css" />
    <![endif]-->
    <style type="text/css">@keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-moz-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-webkit-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-ms-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-o-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}.ace-save-state{animation-duration:10ms;-o-animation-duration:10ms;-ms-animation-duration:10ms;-moz-animation-duration:10ms;-webkit-animation-duration:10ms;animation-delay:0s;-o-animation-delay:0s;-ms-animation-delay:0s;-moz-animation-delay:0s;-webkit-animation-delay:0s;animation-name:nodeInserted;-o-animation-name:nodeInserted;-ms-animation-name:nodeInserted;-moz-animation-name:nodeInserted;-webkit-animation-name:nodeInserted}</style>
    <!-- ace settings handler -->
    <script src="${request.contextPath}/static/assets/js/ace-extra.min.js"></script>
    <!--[if lte IE 8]>
    <script src="${request.contextPath}/static/assets/js/html5shiv.min.js"></script>
    <script src="${request.contextPath}/static/assets/js/respond.min.js"></script>
    <![endif]-->


    <script src="${request.contextPath}/static/assets/js/jquery-2.1.4.min.js"></script>

    <!-- layui提示插件 -->
    <link href="${request.contextPath}/static/layui/css/layui.css" rel="stylesheet" type="text/css" />
    <script src="${request.contextPath}/static/layui/layui.js" type="text/javascript"></script>
    <!-- 自定义confirm -->
    <script src="${request.contextPath}/static/assets/Ewin.js" type="text/javascript"></script>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/colorbox.min.css" />
    <script src="${request.contextPath}/static/assets/js/jquery.colorbox.min.js"></script>

    <style>
        .text2{
            position: absolute;
            top: -5px;
            right: 20px;
            width: 50px;
            height: 20px;
            line-height:20px;
        }
    </style>
</head>

<body class="no-skin" style="background: #FFFFFF;">
<div class="panel panel-default" style="width:50%;min-height:200px;margin:150px auto;border:1px solid #d5e3ef;text-align: center;">
    <div id="uploadDiv" class="panel-body" >
        <#if rcid??>
            <div id="selectDiv">
                <input type="hidden" id="roomCoursewareId" value="${rcid}"/>
                <input type="hidden" id="coursewareId" />
                <input type="hidden" id="splitFiles"/>

                <div class="alert alert-info">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="ace-icon fa fa-times"></i>
                    </button>
                    <table style="margin:0px auto;width:600px;">
                        <tr>
                            <td style="text-align: right;"><strong>课件目前支持格式：</strong></td>
                            <td style="text-align: left;">.doc  .docx  .docm  .xlsx  .pptx  .htm  .html  .pdf  .txt  .wps  .xml  .csv</td>
                        </tr>
                        <tr>
                            <td style="text-align: right;"><strong>课件大小：</strong></td>
                            <td style="text-align: left;">50M 以内</td>
                        </tr>
                    </table>
                </div>
                <!-- 上传文件域begin -->
                <label class="ace-file-input ace-file-multiple">
                    <input multiple="" id="file" type="file" >
                </label>
                <!-- 上传文件域end -->
            </div>
            <div id="sureFinish" style="display: none;margin-bottom:10px;margin-top:10px;">
                <button onclick="finish()" class="btn btn-white btn-warning btn-bold">
                    <span class="glyphicon glyphicon-ok"></span>
                    我已完成课件上传
                </button>
            </div>
            <div  style="width:100%;height:auto;overflow-x: auto;position:relative;padding:0px 50px;">
                <ul id="preDiv" class="ace-thumbnails clearfix" >

                </ul>
            </div>
        <#else>
            <div style="width:100%;height:200px;margin:150px 0px;text-align: center;">
                <div style="font-size:40px;margin:0px auto;"><i class="ace-icon fa fa-globe red"></i></div>
                <h3 class="red">抱歉，该课件上传地址有误，请确认后输入</h3>
            </div>
        </#if>
    </div>

    <div id="finishDiv" style="width:100%;height:200px;margin:150px 0px;text-align: center;display:none;">
        <div style="font-size:40px;margin:0px auto 20px auto;"><span class="glyphicon glyphicon-ok green"></span></div>
        <h3 class="green">恭喜你，上传完成</h3>
        <p>&nbsp;</p>
        <p class="green">您的直播已创建</p>
    </div>


    <script src="${request.contextPath}/static/assets/js/bootstrap.min.js"></script>
    <!-- ace scripts -->
    <script src="${request.contextPath}/static/assets/js/ace-elements.min.js"></script>
    <script src="${request.contextPath}/static/assets/js/ace.min.js"></script>
    <script>
        var basePath  = '${request.getContextPath()}';

        var heraldPath = '';

        var $overflow = '';

        var colorbox_params = {};

        $(function () {



            $('#file').ace_file_input({
                style: 'well',
                btn_choose: '点击上传课件',
                btn_change: null,
                showRemove: false,
                no_icon: 'ace-icon ace-icon fa fa-cloud-upload',
                droppable: true,
                thumbnail: 'small',//large | fit
                allowExt: ['.doc','.docx','.docm','.xlsx','.pptx','.htm','.html','.pdf','.txt','.wps','.xml','.csv'],
                preview_error: function (filename, error_code) {

                }
            }).on('change', function () {
                uploadFile();
            });
        });
        //选中图片
        function okselli(index,heraldPath){
            $(".selok_image").hide();
            $("#selok_image_"+index).show();
            this.heraldPath = heraldPath;
        }

        /**
         * 上传文件
         */
        function uploadFile(){
            var formData = new FormData();
            var file = $("#file")[0].files[0];
            if(file.size > (50 * 1024 * 1024)){
                alert("上传的文件大小不可超过50M");
                return false;
            }

            formData.append("file",file);
            formData.append("toConvertPic",true);
            loading_begin();
            $.ajax({
                url:basePath+"/import/up/courseware",
                type:"post",
                data:formData,
                processData: false,  //必须
                contentType: false,  //必须
                success:function(data){
                    if(!!data){
                        console.log(data);
                        $("#coursewareId").val(data[0].insertId);
                        $("#splitFiles").val(JSON.stringify(data[0].splitFileList));
                        $("#sureFinish").show();
                        var html = "";
                        $.each(data[0].splitFileList,function(index,obj){
                            var display = 'display:none;';
                            if(index == 0){
                                display = "";
                                heraldPath = obj.splitFilePath;
                            }
                            html += '<li  style="height:200px;width:200px;border:solid 1px #000000;cursor:pointer;">'+
                                    '<a  href="'+obj.splitFilePath+'" data-rel="colorbox">'+
                                    '<img width="200"  height="200" alt="200x200" src="'+obj.splitFilePath+'" >'+
                                    '<div class="text2 selok_image" id="selok_image_'+index+'" style="font-size:32px;'+display+'">'+
                                    '<span class="label label-success arrowed-in">' +
                                    '  预告封面  ' +
                                    '</span>'+
                                    '</div>'+
                                    '<div class="tools tools-bottom" style="padding-top:5px;line-height:16px;">'+
                                    '<a href="#" onclick="okselli('+index+',\''+obj.splitFilePath+'\')">'+
                                    '<font style="font-size:14px;">&nbsp;设置为预告封面</font>'+
                                    '</a>'+
                                    '</div>'+
                                    '</a>'+
                                    '</li>';
                        });
                        $("#preDiv").html(html);
                        $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
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
         * 确认完成
         */
        function finish(){
            var roomCoursewareId = $("#roomCoursewareId").val();
            var coursewareId = $("#coursewareId").val();
            var splitFiles = $("#splitFiles").val();

            $.ajax({
                url: basePath + "/minigram/updateRoomCourseware",
                type: "post",
                dataType:'json',
                data: {
                    id:roomCoursewareId,
                    coursewareId: coursewareId,
                    heraldPath: heraldPath,
                    splitFiles: splitFiles
                },
                success: function (data) {
                    if(data.result){
                        $("#uploadDiv").hide();
                        $("#finishDiv").show();
                    }else{
                        alert(data.message);
                    }
                }
            });
        }
        /**
         * 系统提示
         * @param txt
         */
        window.alert = function(txt)
        {
            layui.use('layer', function(){
                layer.msg(txt);
            });
        }
        /**
         * 加载中
         */
        function loading_begin(){
            layui.use('layer', function(){
                var index = layer.load(1, {
                    shade: [0.3,'#fff'] //0.1透明度的白色背景
                });
            });
        }
        /**
         * 隐藏加载中
         */
        function loading_end(){
            layui.use('layer', function(){
                layer.closeAll('loading');
            });
        }

        /**
         * 日期格式化（标准时间格式化为yyyy-MM-dd）
         * @param obj
         * @returns {string}
         */
        function fmtDate(obj){
            var date =  new Date(obj);
            var y = 1900+date.getYear();
            var m = "0"+(date.getMonth()+1);
            var d = "0"+date.getDate();
            return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
        }

    </script>
    <script type="text/javascript">
        jQuery(function($) {
            colorbox_params = {
                rel: 'colorbox',
                reposition:true,
                scalePhotos:true,
                scrolling:false,
                previous:'<i class="ace-icon fa fa-arrow-left"></i>',
                next:'<i class="ace-icon fa fa-arrow-right"></i>',
                close:'&times;',
                current:'{current} of {total}',
                maxWidth:'100%',
                maxHeight:'100%',
                onOpen:function(){
                    $overflow = document.body.style.overflow;
                    document.body.style.overflow = 'hidden';
                },
                onClosed:function(){
                    document.body.style.overflow = $overflow;
                },
                onComplete:function(){
                    $.colorbox.resize();
                }
            };

            $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon
            $(document).one('ajaxloadstart.page', function(e) {
                $('#colorbox, #cboxOverlay').remove();
            });
        })
    </script>
</div>
</body>
</html>
