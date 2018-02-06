1.fronJson文件夹名字不能修改，否则要修改FrontServiceImpl代码中获取文件夹目录的方法
2.每个前端页面对应一个{name}.json文件，在前端访问时通过访问/项目目录/front_{name}，如果name没有对应的json配置文件，则会返回404 not found,然后通过js中的error:function(){}跳转到404页面
3.json文件配置说明：
{    
	"pageTitle": "户口转移办理", //html title 会作为返回值的type返回给后台，作为表单标识，所以要求不同名
	"navTitle": "世纪城社区",//导航条的标题
	"fnId":"12"//对应的功能id，用来反馈给后台做数据存储
    "navSubTitle": "&nbsp;&nbsp;便民服务平台",//导航条的副标题 
    "mainTitle": "民政服务",//页面内容中的标题
    "mainSubTitle": "户口转移办理", //页面内容中的副标题
    "mainInfo": "收费标准：免费", //页面内容中的信息，可以写办理须知
    "panelHeader": "在线初审：在线提交资料，若通过审核将短信通知您前往柜台办理", //表单块的块头
    "formInitParams": //表单初始化参数，目前实现的组件有文本输入框，图片上传框和验证码框(验证码框包含了手机号输入框，不能一起使用)
    [
        {//文本输入框，申请人姓名
            "params": {
                "divID": "realName", //会作为前端div的id，也会作为返回参数键值对中的键返回给后台，所以同一个表单中不能有相同的
                "label": "申请人姓名"//可以加入&nbps;、html标签等
            }, 
            "type": "text"
        }, 
        {//图片上传框
            "params": {
                "backendUrl": "upload.api", //后端上传url
                "divID": "accountPic", //会作为前端div的id，也会作为返回键值对中的键返回后端，所以同一表单中不能有相同值，对应后端的键值对的值是图片后端的url地址
                "quality": "0.6", //图片压缩质量，越低压缩出来的图片越小
                "hvw": "1", //缩略图展示框最大高和宽的比
                "label": "户口本", //显示的label，可以加入&nbps;、html标签等
                "picClassify": "account_pic"//后端根据这个标签对图片进行分类存放
            }, 
            "type": "img"
        }, 
        {//验证码输入框，会自带一个手机号输入框，手机号返回后端的键值对的键是telNum，同手机号输入框
            "params": {
                "backendUrl": "sendVerify.api", //后端用来验证的api，在前端点击获取验证码的时候对这个地址传递一个手机号码，获取到后调用短信网关对这个手机发送信息
                "divID": "verifyInput",//前端作为div的id，后端对应手机号telNum字段，验证码字段 verifyInputCode
                "label": "手机号码"
            }, 
            "type": "verify"
        }
    ]
}