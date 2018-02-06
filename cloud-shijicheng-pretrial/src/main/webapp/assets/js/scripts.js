jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("resource/assets/img/backgrounds/1.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
    //提交绑定，click和回车事件触发
    $('#loginBtn').click(function(){
    	logincheck();
    });
    document.onkeydown = function(e){
    	if(!e){e = window.event;}
    	if((e.keyCode || e.which) == 13){logincheck();}
    }
    
});

//登录信息检查
function logincheck(){
	var username = $('.login-form input[type="text"]').val();
	var password = $('.login-form input[type="password"]').val();
	//alert(username.length + '||' + password.length);
	if(username.length <= 4 || password.length <= 4){
		if(username.length <= 4){
			$(function () {
				$('.login-form input[type="text"]').popover('show')
			})
		}
		if(password.length <= 4){
			$(function () {
				$('.login-form input[type="password"]').popover('show')
			})    			
		}
	}else{
		$.ajax({
			method:'POST',
			url:'login.api',
			//dataType: "json",
			data:{
				username:username,
				password:password
			},
			success:function(response){
				var responseObj = JSON.parse(response)
				if(responseObj.type == "success"){
					window.location.href = responseObj.url;
				}else{
					alert(responseObj.reason);
				}
			}
		});
	}
		
}