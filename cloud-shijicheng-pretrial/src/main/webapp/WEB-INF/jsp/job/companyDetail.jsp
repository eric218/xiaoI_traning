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
	  				<li><a onclick='loadPageForNav("main/job_jyzp_qydwzp.api")' href="javascript:void(0);">企业/单位招聘信息列表</a></li>
	  				<li class="active">查看</li>
				</ol>
			<div class="col-xs-6 col-md-6 col-lg-6 text-right">
				<ol class="list-inline" style="padding:10px 15px;">
		  			<li><button type="button" onclick="window.open('exportPdf/companyRecruitment?jobId=${jobId}')" class="btn btn-link"><i class="glyphicon glyphicon-file"></i>导出pdf</button></li>
				</ol>	
			</div>
		</div>
		<table class="table table-bordered col-xs-8" style="margin-bottom:0px;">
			<caption>
				<div class="row">
					<div class="col-xs-12 col-md-12 col-lg-12 text-center">
						<h4><b>招聘登记表</b></h4>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-8 col-md-8 col-lg-8 text-left">
						登记日期: <span id="registerDate" style="margin-right:15px;"><em style="color:#787778;">数据加载中...</em></span> 有效期: <span id="expireDate"><em style="color:#787778;">数据加载中...</em></span>
					</div>
					<div class="col-xs-4 col-md-4 col-lg-4 text-right">
						登记表编号: <span id="jobNo"><em style="color:#787778;">数据加载中...</em></span>
					</div>
				</div>
			</caption>		  
			   <tbody>
			      <tr>
			         <td colspan="2">招聘单位全称：<span id="compName"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>招聘单位性质：<span id="compNature"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>经济类型：<span id="econType"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td>所属行业：<span id="industry"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>法人代表：<span id="representative"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">组织机构代码证编号：<span id="organizeCode"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td>招聘方式：<span id="recruType"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>邮政编码：<span id="postCode"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">营业执照号：<span id="busiLicense"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="2">所属地区：<span id="compPartOfAddr"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td colspan="2">详细地址：<span id="compDetailAddr"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td>联系人：<span id="contactName"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>联系电话：<span id="contactTel"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>公司座机：<span id="compTel"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>招聘邮箱：<span id="contactEmail"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="6" style="text-align:center;"><span><b>招聘要求</b></span></td>
			      </tr>
			      <tr>
			         <td>用工形式：<span id="employForm"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>用工方式：<span id="employMode"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>户籍要求：<span id="houseRequire"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>试用期限：<span id="probationPeriod"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="2">是否提供食宿：<span id="accommodation"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>是否缴纳社会保险：<span id="socialSecur"><em style="color:#787778;">数据加载中...</em></span></td>
			         <td>是否参加周五小型招聘会：<span id="jobFair"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="6">工作地点：<span id="workAddr"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="6">其他福利待遇说明：<span id="benefitDesc"><em style="color:#787778;">数据加载中...</em></span></td>
			      </tr>
			      <tr>
			         <td colspan="6">
				         <div>
					         <table class="table table-bordered col-xs-8" style="margin-bottom:0px;text-align:center;">
								<caption style="text-align:center;">
									<h5><b>招聘岗位</b></h5>
								</caption>
								  <thead>
								    <tr>
								      <th style="text-align:center;">招聘工种</th>
								      <th style="text-align:center;">文化程度</th>
								      <th style="text-align:center;">年龄要求</th>
								      <th style="text-align:center;">招聘人数</th>
								      <th style="text-align:center;">性别</th>
								      <th style="text-align:center;">工资待遇及奖金</th>
								      <th style="text-align:center;">岗位相关要求</th>
								    </tr>
								  </thead>		  
								 <tbody id="workTable">
								    <!--  <tr>
								        <td>招聘单位全称：<span id="jName"><em style="color:#787778;">数据加载中...</em></span></td>
								        <td>招聘单位性质：<span id="nation"><em style="color:#787778;">数据加载中...</em></span></td>
								        <td>经济类型：<span id="education"><em style="color:#787778;">数据加载中...</em></span></td>
								        <td>招聘单位全称：<span id="jName"><em style="color:#787778;">数据加载中...</em></span></td>
								        <td>招聘单位性质：<span id="nation"><em style="color:#787778;">数据加载中...</em></span></td>
								        <td>经济类型：<span id="education"><em style="color:#787778;">数据加载中...</em></span></td>
								        <td>经济类型：<span id="education"><em style="color:#787778;">数据加载中...</em></span></td>
								     </tr> -->
								  </tbody>
							</table>
				         </div>
			         </td>
			      </tr>
			      <tr>
			         <td colspan="6">
				         <div>
					         <p>本公司承诺网上招聘填写信息及申报材料完全真实有效，本公司将严格遵守国家相关法律、法规及规定，守法经营、诚实信用，如有虚假，本公司愿承担一切法律责任。</p>
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
				 	$("#registerDate").html(data.registerDate);
				 	$("#expireDate").html(data.expireDate);
				 	$("#jobNo").html(data.jobNo);
				 	$("#compName").html(data.compName);
				 	$("#compNature").html(data.compNature);
				 	$("#econType").html(data.econType);
				 	$("#recruType").html(data.recruType);
				 	$("#postCode").html(data.postCode);
				 	$("#busiLicense").html(data.busiLicense);
				 	$("#compPartOfAddr").html(data.compPartOfAddr);
				 	$("#compDetailAddr").html(data.compDetailAddr);
				 	$("#contactName").html(data.contactName);
				 	$("#contactTel").html(data.contactTel);
				 	$("#compTel").html(data.compTel);
				 	$("#contactEmail").html(data.contactEmail);
				 	$("#employForm").html(data.employForm);
				 	$("#employMode").html(data.employMode);
				 	$("#houseRequire").html(data.houseRequire);
				 	$("#probationPeriod").html(data.probationPeriod);
				 	$("#accommodation").html(data.accommodation);
				 	$("#socialSecur").html(data.socialSecur);
				 	$("#jobFair").html(data.jobFair);
				 	$("#workAddr").html(data.workAddr);
				 	$("#benefitDesc").html(data.benefitDesc);
				 	$("#industry").html(data.industry);
				 	$("#representative").html(data.representative);
				 	$("#organizeCode").html(data.organizeCode);
				 	
				 	//填充内嵌的table
				 	var jobTable = data.jobCompJobs;
				 	if(jobTable!=null && jobTable!=undefined && jobTable!='undefined' && jobTable.length>0){
				 		var tableStr = '';
				 		for(var j=0; j<jobTable.length; j++){
				 			var tableTr=jobTable[j];
				 			tableStr+= ('<tr><td>'+tableTr.position+'</td><td>'+tableTr.edu+'</td><td>'+tableTr.age+'</td><td>'+tableTr.recruitNum+'</td><td>'+tableTr.sex+'</td><td>'+tableTr.salary+'</td><td>'+tableTr.reqiurement+'</td></tr>');
				 		}
				 		$('#workTable').html(tableStr);
				 	}else{
				 		$('#workTable').html('<tr><td colspan="7" style="text-align:center;">没有查询到数据</td></tr>');
				 	}
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
 		}else if(index==1){
 			$("#aNext").css("display","");
 			$("#aPre").css("display","");
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