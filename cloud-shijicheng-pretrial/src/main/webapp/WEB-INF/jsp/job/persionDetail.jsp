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
	  				<li><a onclick='loadPageForNav("main/job_jyzp_grqzlist.api")' href="javascript:void(0);">个人求职信息列表</a></li>
	  				<li class="active">查看</li>
				</ol>
			<div class="col-xs-6 col-md-6 col-lg-6 text-right">
				<ol class="list-inline" style="padding:10px 15px;">
		  			<li><button type="button" onclick="window.open('exportPdf/personalJobHunting?jobId=${jobId}')" class="btn btn-link"><i class="glyphicon glyphicon-file"></i>导出pdf</button></li>
				</ol>	
			</div>
		</div>
		<table class="table table-bordered col-xs-8" style="margin-bottom:0px;">
			<caption>
				<div class="row">
					<div class="col-xs-12 col-md-12 col-lg-12 text-center">
						<h4><b>个人求职登记表</b></h4>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-8 col-md-8 col-lg-8 text-left">
						登记日期: <span id="createDate" style="margin-right:15px;"><em style="color:#787778;">数据加载中...</em></span> 有效期: <span id="expireDate"><em style="color:#787778;">数据加载中...</em></span>
					</div>
					<div class="col-xs-4 col-md-4 col-lg-4 text-right">
						登记表编号: <span id="jobNo"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
			</caption>		  
			   <tbody>
			      <tr>
			         <td>姓名：<span id="jName"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>性别：<span id="sex"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>民族：<span id="nation"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>文化程度：<span id="education"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>年龄：<span id="age"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>身高：<span id="height"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="3">身份证号码：<span id="idCard"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>健康情况：<span id="healthCond"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">户口性质：<span id="regNature"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td>婚姻状况：<span id="marryStatus"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">是否持有职业资格证书：<span id="isCertificate"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="3">联系电话：<span id="tel"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="6">人员类型：<span id="category"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="2">毕业院校：<span id="university"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">所学专业：<span id="major"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">毕业时间：<span id="graduateDate"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="2">专业工种：<span id="profType"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">专业技术等级：<span id="profLvl"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">从业年限：<span id="profTime"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="4">应聘岗位：<span id="position"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">月薪要求：<span id="salary"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="6">户口所在地：<span id="regAddr"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="6">现居住地址：<span id="domAddr"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="6">
				         <div>
					         <p>学习工作培训经历：</p>
					         <p><span id="workExper"><em style="color:#787778;">数据加载中...</em></span></p>
				         </div>
			         </td>
			      </tr>
			      <tr>
			         <td colspan="6">
				         <div>
					         <p>本人承诺网上报名填写信息及申报材料完全真实有效，如有虚假，本人承诺一切法律责任，在招聘中的任何环节，一旦发现本人上报信息虚假不实，随时取消求职推荐资格。</p>
				         </div>
				         <div style="text-align:right;">
					         <p style="margin-right:100px;">信息确认（请签字）：</p>
				         </div>
			         </td>
			      </tr>
			   </tbody>
	 </table>
     <ul class="list-unstyled" id="attachmentList">
		<!-- <li>附件1：<a href="javascript:void(0);" onclick="openAdditionView()"><span id="addr"><em style="color:#A4AEDA;">数据加载中...</em></span></a></li> -->
	 </ul>
	
	 <!-- 附件预览弹窗 -->
	<div class="modal fade" id="imgViewDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:800px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<a id="aNext" href="javascript:void(0);" style="float:right;margin-right:5px;" onclick="changeNext()"><span>下一页</span><i class="glyphicon glyphicon-chevron-right"></i></a>
					<a id="aPre" href="javascript:void(0);" style="float:right;margin-right:5px;" onclick="changePre()"><i class="glyphicon glyphicon-chevron-left"></i><span>上一页</span></a>
					<h4 class="modal-title" id="myModalLabel">
<!-- 						附件1：求职人员身份证 -->
					</h4>
				</div>
				<div class="modal-body text-center" id="dialogImg">
					<!-- <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508242033251&di=dafbb709ab614f988da48a279e0aad92&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D3778844716%2C2345470252%26fm%3D214%26gp%3D0.jpg"  alt="" style="display: inline-block; max-width: 100%;padding:5px;"/>
					<img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508242033251&di=dafbb709ab614f988da48a279e0aad92&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D3778844716%2C2345470252%26fm%3D214%26gp%3D0.jpg"  alt="" style="display: inline-block; max-width: 100%;padding:5px;"/> -->
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>

	<script type="text/javascript">
	var srcFiles;
	var fileIndexGloble = 0;
	$(document).ready(function(){
		var jobId = ${jobId};
		var type = ${type};
		$.ajax({
			method:'GET',
			url:'recruit/getDetail',
			data:{
				jobId : jobId,
				type : type
			},
			success:function(data){
				console.info(data);
				if(data!=null && data!=undefined && data!='undefined'){
				 	$("#createDate").html(data.createDate);
				 	$("#expireDate").html(data.expireDate);
				 	$("#jobNo").html(data.jobNo);
				 	$("#jName").html(data.jName);
				 	$("#sex").html(data.sex);
				 	$("#nation").html(data.nation);
				 	$("#education").html(data.education);
				 	$("#age").html(data.age);
				 	$("#height").html(data.height);
				 	$("#idCard").html(data.idCard);
				 	$("#healthCond").html(data.healthCond);
				 	$("#regNature").html(data.regNature);
				 	$("#marryStatus").html(data.marryStatus);
				 	$("#isCertificate").html(data.isCertificate);
				 	$("#tel").html(data.tel);
				 	$("#category").html(data.category);
				 	$("#university").html(data.university);
				 	$("#major").html(data.major);
				 	$("#graduateDate").html(data.graduateDate);
				 	$("#profType").html(data.profType);
				 	$("#profLvl").html(data.profLvl);
				 	$("#profTime").html(data.profTime);
				 	$("#position").html(data.position);
				 	$("#salary").html(data.salary);
				 	$("#regAddr").html(data.regAddr);
				 	$("#domAddr").html(data.domAddr);
				 	$("#workExper").html(data.workExper);
				 	
				 	//把附件赋值给srcFiles
					srcFiles = data.files;
				 	//初始化下面的附件列表
				 	if(srcFiles!=null && srcFiles!=undefined && srcFiles!='undefined' && srcFiles.length>0){
				 		var srcList = '';
				 		for(var i=0; i<srcFiles.length; i++){
				 			srcList += '<li>附件'+(i+1)+'：<a href="javascript:void(0);" onclick="openAdditionView('+i+')">'+srcFiles[i].fileName+'</a></li>';
				 		}
				 		$("#attachmentList").html(srcList);
				 	}
				}else{
					alert("获取数据失败!");
				}
			}
		});
	});
	
	//图片预览弹窗
	function openAdditionView(index){
		fileIndexGloble = index;
		fillDialogValue(index);
		$("#imgViewDialog").modal("show");
	}
	
	function fillDialogValue(index){
		//附件的label
		$("#myModalLabel").html('附件'+(index+1)+'：'+srcFiles[index].fileName);
		//附件图片
		var srcImg = '';
 		for(var i=0; i<srcFiles[index].fileAddr.length; i++){
 			srcImg = '<img src="'+srcFiles[index].fileAddr[i]+'"  alt="" style="display: inline-block; max-width: 100%;padding:5px;"/>';
 		}
 		$("#dialogImg").html(srcImg);
 	    //上一页，下一页图标控制
 		if(index==0){
 			$("#aNext").css("display","");
 			$("#aPre").css("display","none");
 		}else{
 			$("#aNext").css("display","none");
 			$("#aPre").css("display","");
 		}
	}
	
	//点击上一页和下一页的时候
	function changePre(){
		fileIndexGloble --;
		fillDialogValue(fileIndexGloble);
	}
	
	//点击上一页和下一页的时候
	function changeNext(){
		fileIndexGloble ++;
		fillDialogValue(fileIndexGloble);
	}
</script>
	</body>
</html>