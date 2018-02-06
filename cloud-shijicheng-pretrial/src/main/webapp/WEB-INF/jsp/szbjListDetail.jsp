<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	</head>
	<body>
		<div class="row">
				<ol class="breadcrumb col-xs-6 col-md-6 col-lg-6">
	  				<li><a href="main">首页</a></li>
	  				<li><a id="backToList" onclick='loadPageForNav("main/getAllApply.api")' href="javascript:void(0);">待审申请列表</a></li>
	  				<li><a id="backToSubList" onclick='loadPageForNav("main/apply_jsfw_szbjfwz.api")' href="javascript:void(0);">生殖保健服务证</a></li>
	  				<li id="bcName"class="active"><em style="color:#787778;">数据加载中...</em></li>
				</ol>
			<div id="buttonDiv" class="col-xs-6 col-md-6 col-lg-6 text-right" style="display:none;">
			<ol class="list-inline" style="padding:10px 15px;">
	  				<li><button type="button" id="buttonPass" onclick="pass();" class="btn btn-primary btn-default" style="width:80px;color:white;border-radius:15px;">通过</button></li>
	  				<li><button type="button" id="buttonUnPass" onclick="unPass();" class="btn btn-default" style="width:80px;background-color:white;border-radius:15px;">不通过</button></li>
	  				<li><button type="button" onclick="window.open('exportPdf/reproductiveCivics?formNo=${formNo}')" class="btn btn-link"><i class="glyphicon glyphicon-file"></i>导出pdf</button></li>
				</ol>	
			</div>
		</div>
		<div id="resultDiv" style="display:none;">
			<div class="col-xs-11 col-md-11 col-lg-11" style="background-color:white;margin:10px; color:#787778">
				<div class="row" style="margin:10px;">
				<!-- 	<div class="col-xs-1 col-md-1 col-lg-1 text-right" style="padding-right:5px;">
						审核结果：
					</div> -->
					<div class="col-xs-10 col-md-10 col-lg-10 text-left" style="padding-left:5px;">
						审核结果：<span id="resultReason"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				<div class="row" style="margin:10px;">
		<!-- 			<div class="col-xs-1 col-md-1 col-lg-1 text-right" style="padding-right:5px;">
						审核人：
					</div> -->
					<div class="col-xs-10 col-md-10 col-lg-10 text-left" style="padding-left:5px;">
						审  核  人 ：<span id="resultPerson"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				<div class="row" style="margin:10px;">
<!-- 					<div class="col-xs-1 col-md-1 col-lg-1 text-right" style="padding-right:5px;">
						审核时间：
					</div> -->
					<div class="col-xs-10 col-md-10 col-lg-10 text-left" style="padding-left:5px;">
						审核时间：<span id="resultTime"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				<span style="position: absolute;z-index:10;right:62px; bottom:-45px;">
					<img id="passUnpassImg" alt="水印" class="img-circle" src="" style="height:120px;">
				</span>
			</div>
		</div>
		<div class="row" style="margin: 10px; color:#101010">
			<div class="col-xs-6 col-md-6 col-lg-6" style="margin-right:7px;border:1px solid #BBBBBB;min-height:600px;background-color:white;">
				<div class="row text-center" style="margin:15px;"><strong>生殖保健服务证预审</strong></div>
				<div class="row" style="margin-top:5px;margin-bottom:5px;">
					<div class="col-xs-6 col-md-6 col-lg-6 text-left">
						预审单编号：<span id="formId"><em style="color:#787778;">数据加载中...</em></span>
					</div>
					<div class="col-xs-6 col-md-6 col-lg-6 text-right">
						<span id="formTime"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				<div class="col-xs-12 col-md-12 col-lg-12" style="background-color:#1198D5; min-height:30px;">
					<span style="position:absolute;top:5px;color:white;">基本信息</span>
				</div>
				<!-- 女方的信息 -->
				<div class="col-xs-12 col-md-12 col-lg-12" style="background-color:#88CCEA;margin-top:15px;border-radius:15px;">
					<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;padding-left:0px;padding-right:0px;">
						<div class="col-xs-6 col-md-6 col-lg-6">
							女方姓名:<span id="formWomanName"><em style="color:#787778;">数据加载中...</em></span>
						</div>
						<div class="col-xs-6 col-md-6 col-lg-6 text-right">
							婚姻状况:<span id="formWomanMarried"><em style="color:#787778;">数据加载中...</em></span>
						</div>
					</div>
				</div>
					
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
					<div class="col-xs-7 col-md-7 col-lg-7">
						身份证号:<span id="formWomanCard"><em style="color:#787778;">数据加载中...</em></span>
					</div>
					<div class="col-xs-5 col-md-5 col-lg-5 text-right">
						出生日期:<span id="formWomanBirthDay"><em style="color:#787778;">数据加载中...</em></span>
					</div>						
				</div>
					
				<div class="col-xs-12 col-md-12 col-lg-12" style="border:1px dashed #BBBBBB"></div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
					<!-- <div class="col-xs-2 col-md-2 col-lg-2" style="padding-right:0px;">
						户籍地址:
					</div> -->
					<div class="col-xs-11 col-md-11 col-lg-11 text-left">
						户籍地址:<span id="formWomanRegAddr"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
				<!-- 	<div class="col-xs-2 col-md-2 col-lg-2" style="padding-right:0px;">
						居住地址:
					</div> -->
					<div class="col-xs-11 col-md-11 col-lg-11 text-left">
						居住地址:<span id="formWomanLifeAddr"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				
				<!-- 男方的信息 -->
				<div class="col-xs-12 col-md-12 col-lg-12" style="background-color:#88CCEA;border-radius:15px;padding-left:0px;padding-right:0px;">
					<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
						<div class="col-xs-6 col-md-6 col-lg-6">
							男方姓名:<span id="formManName"><em style="color:#787778;">数据加载中...</em></span>
						</div>
						<div class="col-xs-6 col-md-6 col-lg-6 text-right">
							婚姻状况:<span id="formManMarried"><em style="color:#787778;">数据加载中...</em></span>
						</div>
					</div>
				</div>		
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
						<div class="col-xs-7 col-md-7 col-lg-7">
							身份证号:<span id="formManCard"><em style="color:#787778;">数据加载中...</em></span>
						</div>
						<div class="col-xs-5 col-md-5 col-lg-5 text-right">
							出生日期:<span id="formManBirthDay"><em style="color:#787778;">数据加载中...</em></span>
						</div>					
				</div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="border:1px dashed #BBBBBB"></div>
			
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
				<!-- 	<div class="col-xs-2 col-md-2 col-lg-2" style="padding-right:0px;">
						户籍地址:
					</div> -->
					<div class="col-xs-11 col-md-11 col-lg-11 text-left">
						户籍地址:<span id="formManRegAddr"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
					<!-- <div class="col-xs-2 col-md-2 col-lg-2" style="padding-right:0px;">
						居住地址:
					</div> -->
					<div class="col-xs-11 col-md-11 col-lg-11 text-left">
						居住地址:<span id="formManLifeAddr"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="border:1px dashed #BBBBBB"></div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
					<div class="col-xs-6 col-md-6 col-lg-6">
						婚姻登记证号:<span id="formMarriageCardId"><em style="color:#787778;">数据加载中...</em></span>
					</div>
					<div class="col-xs-6 col-md-6 col-lg-6 text-right">
						婚姻登记日期:<span id="formMarriageCardDate"><em style="color:#787778;">数据加载中...</em></span>
					</div>						
				</div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="border:1px dashed #BBBBBB"></div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
					<div class="col-xs-12 col-md-12 col-lg-12">
						婚姻登记机关:<span id="formMarriageCardOrg" style="margin-left:5px;margin-right:5px;"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="border:1px dashed #BBBBBB"></div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
					<div class="col-xs-12 col-md-12 col-lg-12">
						现有子女数:<span id="formChildFirst" style="margin-left:5px;margin-right:5px;"><em style="color:#787778;">数据加载中...</em></span>
						男孩:<span id="formChildSecond" style="margin-left:5px;margin-right:5px;"><em style="color:#787778;">数据加载中...</em></span>
						女孩:<span id="formChildThird" style="margin-left:5px;margin-right:5px;"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="border:1px dashed #BBBBBB"></div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="margin-top:7px;margin-bottom:7px;">
					<div class="col-xs-12 col-md-12 col-lg-12">
						当前婚育情况:<span id="formFetationed"><em style="color:#787778;">数据加载中...</em></span>
						<span id="formFetationedDaysDiv" style="margin-left:7px;">周数:<span id="formFetationedDays"><em style="color:#787778;">数据加载中...</em></span></span>
					</div>
				</div>
				
				<div class="col-xs-12 col-md-12 col-lg-12" style="background-color:#1198D5; min-height:30px;">
					<span style="position:absolute;top:5px;color:white;">提交材料</span>
				</div>
				
				<div id="allFileList" class="col-xs-12 col-md-12 col-lg-12">
					<em class="text-center" style="color:#787778;">数据加载中...</em>
					<!-- <div class="col-xs-9 col-md-9 col-lg-9" style="margin-top:3px; margin-bottom:3px;">
						<span>身份证正反面</span>
					</div>
					<div class="col-xs-9 col-md-9 col-lg-9" style="margin-top:3px; margin-bottom:3px;">
						<span>身份证正反面</span>
					</div>
					<div class="col-xs-9 col-md-9 col-lg-9" style="margin-top:3px; margin-bottom:3px;">
						<span>身份证正反面</span>
					</div>
					<div class="col-xs-9 col-md-9 col-lg-9" style="margin-top:3px; margin-bottom:3px;">
						<span>身份证正反面</span>
					</div>
					<div class="col-xs-9 col-md-9 col-lg-9" style="margin-top:3px; margin-bottom:3px;">
						<span>身份证正反面</span>
					</div>
					<div class="col-xs-9 col-md-9 col-lg-9" style="margin-top:3px; margin-bottom:3px;">
						<span>身份证正反面</span>
					</div>
					<div class="col-xs-9 col-md-9 col-lg-9" style="margin-top:3px; margin-bottom:3px;">
						<span>身份证正反面</span>
					</div>
					<div class="col-xs-9 col-md-9 col-lg-9" style="margin-top:3px; margin-bottom:3px;">
						<span>身份证正反面</span>
					</div> -->
				</div>
				
			</div>	
			<div class="col-xs-5 col-md-5 col-lg-5" style="margin-left:7px;border:1px solid #BBBBBB;min-height:600px;background-color:white;">
				<div class="row">
					<div class="col-xs-12 col-md-12 col-lg-12 text-left" style="margin-top:10px; margin-bottom:10px;">
						<span id="attFileName"></span>
					</div>
				</div>
				
				<div class="row">
					<div id="clickPreDiv" class="col-xs-1 col-md-1 col-lg-1" style="visibility:hidden">
						<span onclick="clickPrePic()" style="position:absolute;top:250px;padding:5px;"><i class="glyphicon glyphicon-menu-left"></i></span>
					</div>
					<div id="attachFileDiv" class="col-xs-10 col-md-10 col-lg-10 text-center">
						<!-- <img src=""  alt="" style="display: inline-block; max-width: 100%;padding:5px;"/> -->
						<em class="text-center" style="color:#787778;">数据加载中...</em>
					</div>
					<div id="clickNextDiv" class="col-xs-1 col-md-1 col-lg-1">
						<span onclick="clickNextPic()" style="position:absolute;top:250px;padding:5px;"><i class="glyphicon glyphicon-menu-right"></i></span>
					</div>
				</div>
			</div>
		</div>
 <script type="text/javascript">
	var picFiles;
	var picIndex = 0;
	function initAttachFiles(){
		if(picFiles!=null && picFiles.length>0 && picIndex>=0 && picIndex<picFiles.length){
			$("#attFileName").html('附件'+(picIndex+1)+'：'+picFiles[picIndex].fileName);
			$("#attachFileDiv").html('');
			var fileArr = picFiles[picIndex].fileAddr;
			var imgFiles = '';
			for(var m=0; m<fileArr.length; m++){
				imgFiles+='<img src="'+fileArr[m]+'"  alt="'+picFiles[picIndex].fileName+'" style="display: inline-block; max-width: 100%;padding:5px;"/>';
			}
			$("#attachFileDiv").html(imgFiles);
			if(picFiles.length == 1 || picFiles.length<=0){
				$("#clickPreDiv").css("visibility","hidden");
				$("#clickNextDiv").css("visibility","hidden");
			}else{
				if(picIndex<=0){
					$("#clickPreDiv").css("visibility","hidden");
					$("#clickNextDiv").css("visibility","visible");
				}else if(picIndex>=picFiles.length-1){
					$("#clickPreDiv").css("visibility","visible");
					$("#clickNextDiv").css("visibility","hidden");
				}else{
					$("#clickPreDiv").css("visibility","visible");
					$("#clickNextDiv").css("visibility","visible");
				}
			}
		}
	}
	$(document).ready(function(){
		var formNo = ${formNo};
		$.ajax({
			method:'GET',
			url:'approval/detail',
			data:{
				formNo : formNo
			},
			success:function(data){
				if(data!=undefined && data!='undefined' && data.baseInfo!=null && Object.getOwnPropertyNames(data.baseInfo).length>0){
			 		picFiles = data.files;
			 		if(picFiles!=null && picFiles.length>0 && picIndex>=0 && picIndex<picFiles.length){
			 			$("#attachFileDiv").html('<em class="text-center" style="color:#787778;">没有查询到数据！</em>');
			 		}
			 		dataValue = data.baseInfo;
			 		showAndNoShowDiv(dataValue);
					showOrNoshowButton(dataValue);
					//填充数据到页面
					$("#formId").html(dataValue.formNo);
					$("#formTime").html(dataValue.proposerTime);
					//女方信息
					$("#formWomanName").html(dataValue.femaleName);
					$("#formWomanMarried").html(dataValue.feMaritalStatus);
					$("#formWomanCard").html(dataValue.femaleIdCard);
					$("#formWomanBirthDay").html(dataValue.femaleBirthday);	
					$("#formWomanRegAddr").html(dataValue.femaleRegAddress);
					$("#formWomanLifeAddr").html(dataValue.femaleDomAddress);
					//男方信息
					$("#formManName").html(dataValue.maleName);
					$("#formManMarried").html(dataValue.maleMaritalStatus);
					$("#formManCard").html(dataValue.maleIdCard);
					$("#formManBirthDay").html(dataValue.maleBirthday);	
					$("#formManRegAddr").html(dataValue.maleRegAddress);
					$("#formManLifeAddr").html(dataValue.maleDomAddress);
					//婚姻登记
					$("#formMarriageCardId").html(dataValue.marrCertiNumber);
					$("#formMarriageCardDate").html(dataValue.marrCertiDate);
					//婚姻登记机关
					$("#formMarriageCardOrg").html(dataValue.marrCertiOrgan);
					//子女个数
					$("#formChildFirst").html(dataValue.childerNum);
					$("#formChildSecond").html(dataValue.sonNum);
					$("#formChildThird").html(dataValue.daughterNum);
					$("#bcName").html(dataValue.proposerName);
					//展示列表
					showFileList();
					initAttachFiles();
					console.log(dataValue);
					console.log(picFiles);
				}else{
					layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>获取数据失败!", {
						time: 2000
					});
					/* loadPageForNav("main/apply_jsfw_szbjfwz.api"); */
				}
			}
		});
		
		//检测审核原因个数的问题
		 $("#dialogpassRsn,#dialogUnpassRsn").on("input propertychange", function() {
		        var $this = $(this);  
		            _val = $this.val(); 
		        if (_val.length > 70) {  
		            $this.val(_val.substring(0, 70));
		            layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>最多只能输入70个文字！", {
						time: 2000
					});
		        }
		 });
	});
	
	//点击预览下一个附件
	function clickNextPic(){
		picIndex += 1;
		initAttachFiles();
	}
	//点击预览上一个附件
	function clickPrePic(){
		picIndex -= 1;
		initAttachFiles();
	}
	//显示和隐藏common按钮和面包屑
	function showOrNoshowButton(data){
		audited = data.approvalStatus;
		if(audited == '0'){
			//未审核
			$("#resultDiv").css("display","none");
			$("#buttonDiv").css("display","");
		}else{
			$("#buttonDiv").css("display","none");
			$("#resultDiv").css("display","");
			$("#resultReason").html(data.approvalResult);
			$("#resultPerson").html(data.approvalPerson);
			$("#resultTime").html(data.proposerTime);
			//设置url地址
			$("#backToList").attr("onclick", 'loadPageForNav("main/getReviewLog.api")');
			$("#backToList").html('审核记录列表');
			$("#backToSubList").attr("onclick", 'loadPageForNav("main/apply_jsfw_szbjfwzAproved.api")');
			//已经审核
			if(audited == '1'){
				//审核通过了的
				$("#passUnpassImg").attr("src","resource/images/nopass.png");
			}else{
				//审核未通过
				$("#passUnpassImg").attr("src","resource/images/pass.png");
			}
		}
	}
	//显示下面的附件列表
	function showFileList(){
		if(picFiles!=null && picFiles.length>0){
			var attFile = '';
			for(var m=0; m<picFiles.length; m++){
				attFile+='<div class="col-xs-9 col-md-9 col-lg-9" style="margin-top:3px; margin-bottom:3px;"><span>附件'+(m+1)+'：'+picFiles[m].fileName+'</span></div>';
			}
			$("#allFileList").html(attFile);
		}else{
			$("#allFileList").html('<em class="text-center" style="color:#787778;">没有查询到数据！</em>');
			$("#attachFileDiv").html('<em class="text-center" style="color:#787778;">没有查询到数据！</em>');
		}
	}
	
	//显示和隐藏div
	function showAndNoShowDiv(data){
		if(data.isPregancy=='0'){
			//没怀孕
			$("#formFetationedDaysDiv").css("display","none");
			$("#formFetationed").html('未怀孕');
		}else{
			//已经怀孕
			$("#formFetationed").html('已怀孕');
			$("#formFetationedDays").html(data.pregnancyWeeks);
		}
	}
</script>
<!-- 添加通过提示对话框  -->
<div class="modal fade" id="passDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#0094D4">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title text-center" id="myModalLabel">
					审核通过
				</h4>
			</div>
			<div class="modal-body text-center">
				<textarea id="dialogpassRsn"rows="6" style="width:100%;" placeholder="审核已通过，请携带所有附件材料原件至社区办理业务！"></textarea>
			</div>
			<div class="modal-footer" style="text-align:center;">
				<button onclick='passButtonClick("${formNo}")' type="button" class="btn btn-primary" style="background-color:#0094D4">
					确定
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!-- 添加未通过提示对话框  -->
<div class="modal fade" id="unPassDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#0094D4">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title text-center" id="myModalLabel">
					审核未通过原因
				</h4>
			</div>
			<div class="modal-body text-center">
				<textarea id="dialogUnpassRsn"rows="6" style="width:100%;" placeholder="请编辑不通过审核的原因，将会发送给申请人!"></textarea>
			</div>
			<div class="modal-footer" style="text-align:center;">
				<button onclick='unPassButtonClick("${formNo}")' type="button" class="btn btn-primary" style="background-color:#0094D4">
					确定
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<script>
function pass() {
	$("#passDialog").modal("show");
	$("#dialogpassRsn").val('');
}
function unPass() {
	$("#unPassDialog").modal("show");
	$("#dialogUnpassRsn").val('');
}
function passButtonClick(id){
	var rsn = $("#dialogpassRsn").val();
	if(rsn==null || rsn.trim()==''){
		rsn = '审核已通过，请携带所有附件材料原件至社区办理业务！';
	}
	$("#passDialog").modal("hide");
	$("#buttonPass").attr("disabled", true);
	$("#buttonUnPass").attr("disabled", true);
	$.ajax({
		method:'POST',
		url:'approval/apply',
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		data:{
			formNo : id,
			approvalStatus : 2,
			approvalResult : rsn
		},
		success:function(data){
			layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>"+data.message, {
				time: 2000
			});
			loadPageForNav("main/apply_jsfw_szbjfwz.api");
		}
	});
}
function unPassButtonClick(id){
	var rsn = $("#dialogUnpassRsn").val();
	if(rsn==null || rsn.trim()==''){
		layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>请输入审核不通过的原因!", {
			time: 2000
		});
		return;
	}
	$("#unPassDialog").modal("hide");
	$("#buttonPass").attr("disabled", true);
	$("#buttonUnPass").attr("disabled", true);
	$.ajax({
		method:'POST',
		url:'approval/apply',
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		data:{
			formNo : id,
			approvalStatus : 1,
			approvalResult : rsn
		},
		success:function(data){
			layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>"+data.message, {
				time: 2000
			});
			loadPageForNav("main/apply_jsfw_szbjfwz.api");
		}
	});
}
</script>
</body>
</html>