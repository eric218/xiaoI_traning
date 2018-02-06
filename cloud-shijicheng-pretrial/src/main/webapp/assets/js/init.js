$(document).ready(function(){
	var thisUrl = document.location.href;
	var thisUri = thisUrl.substring(thisUrl.indexOf('front_')+6);
	//字符串渲染
	$.ajax({
		url:'initial.api',
		type:'POST',
		data:{
			uri : thisUri
		},
		success:function(data){
			var initParams = JSON.parse(data);
			//设置页面标题
			document.title = initParams.pageTitle;
			//设置navbar
			$('#page_nav a span').html(initParams.navTitle);
			$('#page_nav a').append(initParams.navSubTitle);
			//设置main表单
			$('#main .page-header').append('<h1>'+initParams.mainTitle+'<small>'+initParams.mainSubTitle+'</small></h1>')
			$('#main_info').append(initParams.mainInfo);
			//设置面板
			$('#main .panel-heading p').html(initParams.panelHeader);
			//动态生成表单
			initform('#main .panel-body form',initParams.formInitParams);
			//动态生成提交按钮并绑定提交事件
			$('#main .panel-body form').append('<div class="form-group"></div>');
			$('#main .panel-body div:last')
			.append('<button class="btn btn-info btn-block" type="button" id="submitBtn">提交申请</button>');
			$('#submitBtn').on('click',function(){
				$(this).prop({disabled:true});
				//键值对
				function NameValuePair(name,value,label){
					this.name = name;
					this.value = value;
					this.label = label;
				}
				//上传数据数组
				var uploadData = new Array();
				//获取上传数据
				for(var i = 0 ; i < initParams.formInitParams.length ; i++){
					var inputDivName = "#"+initParams.formInitParams[i].params.divID;
					//获取值
					if(initParams.formInitParams[i].type == 'img'){
						if(initParams.formInitParams[i].params.label.indexOf('可选') != -1){
							//对可选证件的处理
							//不必先做非空判断
							if( $(inputDivName+' img').length > 0 && $(inputDivName+' img').attr('src').indexOf('base64') == 16 ){
								//如果是base64，先检查到第16位就能确定，不会继续监测，如果是http链接也很快就能检测完
								alert('您有图片资料未提交，请上传后再提交申请!');
								return;
							}else if( $(inputDivName+' img').length > 0 && $(inputDivName+' img').attr('src').indexOf('http://') == 0 ){
								var nameValuePair = new NameValuePair(initParams.formInitParams[i].params.divID,$(inputDivName+' img').attr('src'),initParams.formInitParams[i].params.label);
								uploadData[i] = nameValuePair;
							}
						}else{
							//对必传证件的处理
							//获取img src并判断内容
							if($(inputDivName+' img').length <= 0){
								alert(initParams.formInitParams[i].params.label+'不能为空');
								return;
							}
							//不为空再进行下面操作
							if( $(inputDivName+' img').attr('src').indexOf('base64') == 16 ){
								//如果是base64，先检查到第16位就能确定，不会继续监测，如果是http链接也很快就能检测完
								alert('您有图片资料未提交，请上传后再提交申请!');
								return;
							}else if( $(inputDivName+' img').attr('src').indexOf('http://') == 0 ){
								var nameValuePair = new NameValuePair(initParams.formInitParams[i].params.divID,$(inputDivName+' img').attr('src'),initParams.formInitParams[i].params.label);
								uploadData[i] = nameValuePair;
							}
						}
						
						
					}else if(initParams.formInitParams[i].type == 'text'){
						var text = $(inputDivName+' input').val();
						if(text == null || text.length == 0){
							alert(initParams.formInitParams[i].params.label+'不能为空');
							return;
						}else{
							var nameValuePair = new NameValuePair(initParams.formInitParams[i].params.divID,text,initParams.formInitParams[i].params.label);
							uploadData[i] = nameValuePair;
						}
					}else if(initParams.formInitParams[i].type == 'verify'){
						var tel = $(inputDivName+' input').val();
						var verifyCode = $(inputDivName+'Code input').val();
						if(tel.length != 11){
							alert('请正确输入手机号并点击获取验证码！');
							return;
						}
						if(verifyCode.length <= 0){
							alert('请输入手机中收到的验证码！');
							return;
						}
						var nameValuePair = new NameValuePair('telNum',tel,'联系电话');
						uploadData[i] = nameValuePair;
						var nameValuePair = new NameValuePair('verifyCode',verifyCode,'验证码');
						uploadData[i+1] = nameValuePair;
					}

				}
				//FormData
				function FormData(type,array){
					this.type = type;
					this.array = array;
				}
				var formData = new FormData(initParams.fnId,uploadData);
				$.ajax({
					url:'recieveFormData.api',
					type:'POST',
					data:{
						formDatas : JSON.stringify(formData)
					},
					success:function(){
						alert('您的申请已提交，后续进展我们将通过手机短信的方式给您回复，请您关注短信提醒！');						
						$('#submitBtn').prop({disabled:false});
						window.location.href = "";
					},
					error:function(){
						alert('上传失败，与服务器失去联系，请检查您的网络！');
						$('#submitBtn').prop({disabled:false});
					}
				});
			});
			$('#submitBtn').prop({disabled:true});
			$('input').on('change',function(){
				var flag = 1;
				for(var i = 0; i<initParams.formInitParams.length; i++){
					if(initParams.formInitParams[i].type == 'text'){
						var text = $("#"+initParams.formInitParams[i].params.divID+' input').val();
						if(text == null || text.length == 0)
							flag = 0;
					}else if(initParams.formInitParams[i].type == 'img'){
						if($("#"+initParams.formInitParams[i].params.divID+' img').length <= 0)
							flag = 0;
						else{
							if($("#"+initParams.formInitParams[i].params.divID+' img').attr('src').indexOf('base64') == 16)
								flag = 0;
						}
					}else if(initParams.formInitParams[i].type == 'verify'){
						var tel = $("#"+initParams.formInitParams[i].params.divID+' input').val();
						var verifyCode = $("#"+initParams.formInitParams[i].params.divID+'Code input').val();
						if(tel.length != 11 || verifyCode.length <= 0)
							flag = 0;
					}
				}
				if(flag)
					$('#submitBtn').prop({disabled:false});
				else
					$('#submitBtn').prop({disabled:true});
			});
			//别问我特么下面的代码是什么鬼意思，过了今年老子也看不懂
			//uploader.js里面上传成功了return一个h1的点击事件
			//这下子懂了吧
			$('h1').on('click',function(){
				var flag = 1;
				for(var i = 0; i<initParams.formInitParams.length; i++){
					if(initParams.formInitParams[i].type == 'text'){
						var text = $("#"+initParams.formInitParams[i].params.divID+' input').val();
						if(text == null || text.length == 0)
							flag = 0;
					}else if(initParams.formInitParams[i].type == 'img'){
						if(initParams.formInitParams[i].params.label.indexOf("可选") != -1){
							//可选的
							//不产生影响，什么都不做
						}else{
							//必选的
							if($("#"+initParams.formInitParams[i].params.divID+' img').length <= 0)
								flag = 0;
							else{
								if($("#"+initParams.formInitParams[i].params.divID+' img').attr('src').indexOf('base64') == 16)
									flag = 0;
							}
						}
						
					}else if(initParams.formInitParams[i].type == 'verify'){
						var tel = $("#"+initParams.formInitParams[i].params.divID+' input').val();
						var verifyCode = $("#"+initParams.formInitParams[i].params.divID+'Code input').val();
						if(tel.length != 11 || verifyCode.length <= 0)
							flag = 0;
					}
				}
				if(flag)
					$('#submitBtn').prop({disabled:false});//可用
				else
					$('#submitBtn').prop({disabled:true});//不可用户
			});
		},
		error:function(){
			window.location.href="404";
		}
	});
	
	var initform = function(parent, params){
		//循环建立表单表单可能的类型有文本框输入，图片上传框，验证码输入框待定
		for(var i = 0 ; i < params.length ; i++ ){
			if(params[i].type == 'img'){
				$.initUploader(parent,params[i].params);
			}else if(params[i].type == 'text'){
				//initTextInput
				$.initTextInput(parent,params[i].params);
			}else if(params[i].type == 'verify'){
				$.initVerifyInput(parent,params[i].params);
			}else if(params[i].type == 'download'){
				//$.initDownload(parent,params[i].params);
			}
		}
	}
});