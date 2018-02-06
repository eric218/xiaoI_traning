jQuery.initUploader = function(parent,params){
	var id = params.divID;
	var divID = '#'+id;
	var label = params.label;
	var quality = params.quality;
	var hvw = params.hvw;
	var backendUrl = params.backendUrl;
	var picClassify = params.picClassify;
	$(parent).append('<div class="form-group" id="'+id+'"></div>');
	//init
	$(divID).append('<label>'+label+'</label>')
	.append('<div class="input-group"></div>')
	.append('<div style="padding-top: 10px;"></div>');
	$(divID+' div:first').append('<input type="file" accept="image/*" class="form-control">')
	.append('<span class="input-group-btn"></span>');
	$(divID+' div:first span').append('<button class="btn btn-default" type="button">重新上传</button>');
	$(divID+' div:first span button').prop({disabled:true});
	//add event listener
	$(divID+' input').on('change',function(){
		$(divID+' div:first span button').prop({disabled:false});
		$(divID+' input').prop({disabled:true});
		lrz(this.files[0],{
    		fieldName : 'UploadPic',
    		quality : quality
    	}).then(function (rst) {
    		var img = new Image();
        	img.src = rst.base64;
        	var maxWidth = $(divID).width();
        	var maxHeight = maxWidth*hvw;
        	img.onload = function(){
        		if (img.width > maxWidth || img.height > maxHeight) {  
                	if (maxWidth/ maxHeight  <= img.width / img.height){  
                        img.width = maxWidth;   
                        img.height = maxWidth* ( img.height / img.width );  
                    }else {   
                    	img.height = maxHeight  ;   
                        img.width = maxHeight  * (img.width / img.height);  
                        
                    } 
                }
        	}
        	$(divID+' div:last').append('<p>预览</p>').append(img).append('<p><div class="btn btn-info">确认上传</div>');
        	$(divID+' div:last-child div').on('click',function(){
        		rst.formData.append('picClassify',picClassify);
        		rst.formData.append('fileLength', rst.fileLen);
        		$.ajax({
        			url : backendUrl,
        			type : 'POST',
        			processData: false,
        			contentType: false,
        			data : rst.formData,
        			success:function(response){
        				var responseObj = JSON.parse(response);
        				if(responseObj.type == "success"){        					
        					var returnImg = new Image();
        					returnImg.src = responseObj.imgUrl;
        					returnImg.width = img.width;
        					returnImg.height = img.height;
        					$(divID+' div:last-child').empty().append(returnImg)
        					.append('<p><span class="label label-info">上传成功</span>');
        					$(divID+' div:first').hide(function(){return $('h1').click()});
        				}           				
        			},
        			error:function(){
                		$(divID+' input').empty().val("");
                		$(divID+' div:last-child').empty().append('<p>上传出现异常，请查看您的网络并重新尝试上传</p>');
        			}
        		});
        	});
    	}).catch(function (err) {
    		$(divID+' div:first').show();
    		$(divID+' input').empty().val("");
    		$(divID+' div:last-child').empty().append('<p>图片压缩失败，请尝试刷新页面重试</p>');
        }).always(function () {
            // 不管是成功失败，都会执行
            //alert('总是执行')
        });
		$(divID+' div:first span button').click(function(){
			$(divID+' input').prop({disabled:false});
	    	$(divID+' div:first span button').prop({disabled:true});
	    	$(divID+' input').empty().val("");
	    	$(divID+' div:last-child').empty();
	    	return $(divID+' input').click();
	    });
	});
};

jQuery.initTextInput = function(parent,params){
	var divID = params.divID;
	var div = "#"+divID;
	var label = params.label;
	$(parent).append('<div class="form-group" id="'+divID+'"></div>');
	$(parent+' '+ div).append('<label>'+label+'</label>')
	.append('<input type="text" class="form-control" placeholder="">');
};

jQuery.initVerifyInput = function(parent,params){
	var divID = params.divID;
	var div = "#"+divID;
	var label = params.label;
	var backendUrl = params.backendUrl;
	//initial
	$(parent).append('<div class="form-group" id="'+divID+'"></div>');
	$(parent+' '+ div).append('<label>'+label+'</label>')
	.append('<div class="input-group"></div>');
	$(parent+' '+ div+' div').append('<input type="text" class="form-control" placeholder="">')
	.append('<span class="input-group-btn"><button class="btn btn-default" type="button">获取验证码</button></span>')
	$(parent).append('<div class="form-group" id="'+divID+'Code"></div>');
	$(parent+' #'+ divID +'Code').append('<label>验证码</label>')
	.append('<input type="text" class="form-control" placeholder="">');
	//add event listener
	$(parent+' #'+ divID +' button').prop({disabled:true});
	$(parent+' #'+ divID +' input').on('keyup',function(){
		if($(parent+' #'+ divID +' input').val().length == 11)
			$(parent+' #'+ divID +' button').prop({disabled:false});
		else
			$(parent+' #'+ divID +' button').prop({disabled:true});
	});
	$(parent+' #'+ divID +' button').on('click',function(){
		var tel = $(parent+' #'+ divID +' input').val();
		$.ajax({
			url:backendUrl,
			type:'POST',
			data:{
				telNum : tel
			},
			success:function(){
				alert('请在手机上查看验证码！');
				$(parent+' #'+ divID +' button').prop({disabled:true});
			}
		});
	});
}
jQuery.initDownload = function(parent,params){
	var divID = params.divID;
	var div = "#"+divID;
	var label = params.label;
	var downloadUrl = params.downloadUrl;
	var filename = params.filename
	$(parent).append('<div class="form-group" id="'+divID+'"></div>');
	$(parent+' '+ div).append('<a class="btn btn-default btn-block" href="'+downloadUrl+'?filename='+filename+'">'+label+'</a>')
}
