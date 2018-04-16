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
    <style>
        .text2{
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: black;
            z-index:11001;
            -moz-opacity: 0.7;
            opacity:.70;
            filter: alpha(opacity=70);
            text-align:center;
            color:white;
            line-height:150px;
        }
    </style>
</head>

<body class="no-skin">
    <div id="uploadDiv" style="width:100%;min-height:200px;margin:150px 0px;border:1px solid #ccc;text-align: center;">
        <#if rcid??>
            <div id="selectDiv">
            <input type="hidden" id="roomCoursewareId" value="${rcid}"/>
            <input type="hidden" id="coursewareId" />
            <input type="file" id="file" onchange="uploadFile()" style="display:none;"/>
            <button onclick="showUpload()" class="btn btn-white btn-info">
                <span class="ace-icon fa fa-folder-o icon-on-right bigger-110"></span>
                点击上传课件
            </button>
            </div>
            <div  style="width:100%;height:auto;overflow-x: auto;position:relative;">
                <ul id="preDiv" class="ace-thumbnails clearfix" >

                </ul>
            </div>
            <div id="sureFinish" style="display: none;">
                <button onclick="finish()" class="btn btn-white btn-info">
                    <span class="glyphicon glyphicon-ok"></span>
                    确认完成
                </button>
            </div>
        <#else>
            <h1>抱歉，该课件上传地址有误，请确认后输入</h1>
        </#if>
    </div>

    <div id="finishDiv" style="width:100%;height:200px;margin:150px 0px;border:1px solid #ccc;text-align: center;display:none;">
        <h1>恭喜你，上传完成</h1>
    </div>


    <script src="${request.contextPath}/static/assets/js/bootstrap.min.js"></script>
    <!-- ace scripts -->
    <script src="${request.contextPath}/static/assets/js/ace-elements.min.js"></script>
    <script src="${request.contextPath}/static/assets/js/ace.min.js"></script>
    <script>
        var basePath  = '${request.getContextPath()}';

        var heraldPath = '';
        //选中图片
        function okselli(index,heraldPath){
            $(".selok_image").hide();
            $("#selok_image_"+index).show();
            this.heraldPath = heraldPath;
        }
        /*
         * 选择文件
         */
        function showUpload(){
            $("#file").trigger("click");
        }
        /**
         * 上传文件
         */
        function uploadFile(){
            var formData = new FormData();
            formData.append("file",$("#file")[0].files[0]);
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
                        $("#sureFinish").show();
                        var html = "";
                        $.each(data[0].splitFileList,function(index,obj){
                            var display = 'display:none;';
                            if(index == 0){
                                display = "";
                                heraldPath = obj.splitFilePath;
                            }
                            html += '<li  style="height:200px;width:200px;border:solid 1px #000000;cursor:pointer;">'+
                                    '<a  data-rel="colorbox">'+
                                    '<img id="img_tag_" width="200"  height="200" alt="200x200" src="'+obj.splitFilePath+'" >'+
                                    '<div class="text" onclick="okselli('+index+',\''+obj.splitFilePath+'\')">'+
                                    '<div class="inner">设置为预告封面</div>'+
                                    '</div>'+
                                    '<div class="text2 selok_image" id="selok_image_'+index+'" style="font-size:36px;'+display+'">'+
                                    '<div class="inner2 icon-ok">预告</div>'+
                                    '</div>'+
                                    '</a>'+
                                    '</li>';
                        });
                        $("#preDiv").html(html);
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

            $.ajax({
                url: basePath + "/minigram/updateRoomCourseware",
                type: "post",
                dataType:'json',
                data: {
                    id:roomCoursewareId,
                    coursewareId: coursewareId,
                    heraldPath: heraldPath
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

</body>
</html>
