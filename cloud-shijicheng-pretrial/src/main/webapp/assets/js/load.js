$(document).ready(function(){
	//main页面初始化时加载navbar
	$('#navbar').load("getNavBar.api");
	//main页面初始化时加载siderbar
	$('#sidebar-collapse').load("getSiderBar.api");
	//要先清空
	$("#mainPan").empty();
	//main页面初始化时加载主页面
	$("#mainPan").load("main/getMainPage.api");
	
});

jQuery.initSiderBar = function(parent, initUrl){
	$.ajax({
		url:initUrl,
		type:'POST',
		success:function(response){
			var params = JSON.parse(response);
			//动态生成菜单
			renderSiderBar(parent,params);
			//手动调整第二级的菜单添加style="padding-left: 45px;"
			$(parent+' li.parent ul li.parent ul li a').css('padding-left','45px');
			//添加分割线
			$(parent).append('<li role="presentation" class="divider"></li>')
			.append('<li><a href="logout.api"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg>退出系统</a></li>');//添加退出按钮
		}
	});
	
	var renderSiderBar = function(parent,params){
		//根据json生成菜单
		for(var i = 0 ; i < params.length ; i ++ ){
			if(params[i].child.length == 0){
				//没有子节点的情况
				//添加li标签和id=fnTree_xx
				$(parent).append('<li id="fnTree_'+params[i].id+'"></li>');
				//添加a标签
				$('#fnTree_'+params[i].id).append('<a href="javascript:void(0);"></a>');
				//添加图片和文字和时间
				$('#fnTree_'+params[i].id+' a').append(params[i].icon)
				.append(params[i].name)
				.data('onclick_url',params[i].url)
				.on('click',function(){
					$('#mainPan').empty().load($(this).data('onclick_url'));
				});				
			}else{
				//有子节点的情况
				//添加一个li标签，样式为parent
				$(parent).append('<li class="parent" id="fnTree_'+params[i].id+'"></li>');
				//添加一个parent的a标签
				$('#fnTree_'+params[i].id).append('<a data-toggle="collapse"></a>');
				//在a标签中添加span
				$('#fnTree_'+params[i].id+' a').append('<span href="javascript:void(0);"></span>');
				//在span中添加图片
				$('#fnTree_'+params[i].id+' a span').append(params[i].icon);
				//在a标签中添加文字和事件
				$('#fnTree_'+params[i].id+' a').append(params[i].name)
				.data('onclick_url',params[i].url)
				.on('click',function(){
					$('#mainPan').empty().load($(this).data('onclick_url'));
				});
				//在li中创建ul子菜单
				$('#fnTree_'+params[i].id).append('<ul class="children collapse"></ul>');
				//为ul创建一个随机的8位id
				var ul_id = Math.floor(Math.random()*90000000)+10000000;		
				$('#fnTree_'+params[i].id+' ul').attr('id', ul_id);
				//将parent的a标签和ul的标签关联
				$('#fnTree_'+params[i].id+' a').attr('href','#'+ul_id);
				//递归选择parent的时候注意ul前面的>表示当前层级下，不会递归向下查找
				renderSiderBar('#fnTree_'+params[i].id+' > ul',params[i].child)
			}
		}
	}
}

function loadPageForNav(obj){
	$('#mainPan').empty().load(obj);
}
