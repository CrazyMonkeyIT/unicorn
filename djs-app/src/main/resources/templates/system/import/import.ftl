<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<script src="${request.contextPath}/static/js/jquery-1.11.0.js"></script>
<script type="text/javascript">
    var remoteAjax = function(url,option){
        return $.ajax({
            url:url,
            data:option && option.data || '',
            type:option && option.type || 'POST',
        });
    }

    $(function(){
        $('#up').click(excuteup);
        getFileList();
    });

    var excuteup = function(){
        $('#progressMsg').text('正在上传文件...');
        $('#upForm').submit();
    }

    var getFileList = function(){
        var $table = $('#fileTable');
        var url = '${request.contextPath}/getFileList';
        remoteAjax(url).done(function(data){
            if(data && data.length>0){
                for(var i= 0;i<data.length;i++){
                    $table.append("<tr><td>"+data[i]+"</td><td><a href='${request.contextPath}/delete?name="+data[i]+"'>delete</a></td>></tr>");
                }
            }
        });

    }
</script>
<body>
<form id="upForm" action="${request.contextPath}/up/12" enctype="multipart/form-data" method='post'>
    <div id="upDiv">
        File to upload: <input type="file" name="file" multiple="multiple"><br />
    </div>
    <br />
    <input type="button" value="up" id="up" /><br /><br />
</form>

<div>
    <table id="fileTable">
        <tr>
            <td width="200px">文件</td>
            <td>操作</td>
        </tr>
    </table>
</div>
</body>
</html>
