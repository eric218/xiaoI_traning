<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 		<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
		<link rel="stylesheet" href="kindeditor/plugins/code/prettify.css" />
		<script charset="utf-8" src="kindeditor/kindeditor-all.js"></script>
		<script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>
		<script charset="utf-8" src="kindeditor/plugins/code/prettify.js"></script>
	</head>
	<body>
		<div class="row">
				<ol class="breadcrumb col-xs-6 col-md-6 col-lg-6">
	  				<li><a href="main">首页</a></li>
	  				<li><a onclick='loadPageForNav("main/job_jyzp_zpxxfb.api")' href="javascript:void(0);">招聘信息发布列表</a></li>
	  				<li class="active">添加招聘信息</li>
				</ol>
			<div class="col-xs-6 col-md-6 col-lg-6 text-right">
				<ol class="list-inline" style="padding:10px 15px;">
		  			<li><button id="publishBtn" type="button" onclick="addRecruit()" class="btn btn-primary btn-default" style="width:80px;border-radius:15px;">发布</button></li>
				</ol>	
			</div>
		</div>
		<form name="formArea" id="formArea">  
			<textarea class="text-center" name="jobTitle" id="jobTitle" cols="100" rows="8" style="width:100%;height:40px;" placeholder="请输入标题"></textarea>
			<div id="fileSpan" class="row" style="margin-left:0px;margin-right:0px;margin-top:5px; margin-bottom:10px;">
				<i class="glyphicon glyphicon-open"></i><a href="javascript:void(0);" onclick="addFile()">上传附件</a>
			</div>
			<textarea name="content" id="content" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
<!-- 			<input type="text" name="jobId" value="0" style="display:none;"/> -->
		</form>
	<script type="text/javascript">
	var editor;
	var filesCount = 1;
	function kedit(kedit){
		editor = KindEditor.create(kedit, {
				width:'100%',
				height:'550px',
			 	allowImageUpload:false, //上传图片框本地上传的功能，false为隐藏，默认为true
			 	allowImageRemote:false, //上传图片框网络图片的功能，false为隐藏，默认为true
			    allowFileManager:false, //浏览图片空间
			    afterCreate:function() {
                    this.sync();
                },
				afterBlur:function(){
					this.sync();
				},
				items: ["source", "|", "undo", "redo", "|", "preview", "print", "template", "code", "cut", "copy", "paste", "plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/", "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "flash", "media", "insertfile", "table", "hr", "emoticons", "baidumap", "pagebreak", "anchor", "link", "unlink", "|", "about"]
			});
			prettyPrint();
	}
	 
	$(function(){
	    kedit('textarea[name="content"]');
	});
	function addRecruit(){
		var cTitle = $("#jobTitle").val();
		var cContent = $("#content").val();
		if(cTitle=='' || cContent==''){
			alert("标题和内容不能为空！");
			return;
		}
		//生成formdata表单
		var form=document.getElementById("formArea");
        var fd=new FormData(form);
        fd.append("jobId", '0');
        fd.append("deleteFileIds", '');
        $("#publishBtn").attr("disabled", true);
        var tips = layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>保存中，保存成功后会自动跳转到列表界面！", {
			time: 200000
		});
		$.ajax({
			method:'POST',
		    processData: false,
		    contentType: false,
			url:'recruit/updateOrAdd',
			data:fd,
			success:function(data){
				layer.close(tips);
				if(data.code==1){
					loadPageForNav("main/job_jyzp_zpxxfb.api");
				}else{
					$("#publishBtn").attr("disabled", false);
					layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>保存失败，请重试!", {
						time: 2000
					});
				}
			}
		});
	}
	//打开文件上传对话框
	function addFile(){
		if(filesCount>3){
			layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>最多只能添加3个附件!", {
				time: 2000
			});
			return;
		}
	    $("#fileSpan").append('<span style="margin-right:15px;"><input type="file" name="files" onchange="onFileChanged(this)" style="display:inline-block;width:190px;"/><i class="glyphicon glyphicon-remove" style="color:red;" onclick="delFile(this)"></i></span>');
		filesCount++;
	}
	
	//文件改变情况
	function onFileChanged(target){
	    //检测上传文件的大小        
	    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
	    var fileSize = 0;           
	    if (isIE && !target.files){
	        var filePath = target.value;       
	        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
	        var file = fileSystem.GetFile(filePath);       
	        fileSize = file.Size;      
	    } else {
	        fileSize = target.files[0].size;       
	    } 
		if(fileSize>50*1024*1024){
			alert("文件的大小必须小于等于50M");
			target.value="";
			return false;
		}
	}
	
	function delFile(obj){
		$(obj).parent().remove();
		filesCount--;
	}
</script>
	</body>
</html>