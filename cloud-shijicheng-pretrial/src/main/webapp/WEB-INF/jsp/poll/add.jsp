<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Content Header (Page header) -->
<div class="row">
    <ol class="breadcrumb col-xs-12 col-md-12 col-lg-12">
        <li><a href="main">主页</a></li>
        <li><a onclick='loadPageForNav("main/pollManage.api")' href="javascript:void(0);">民调管理</a></li>
        <li class="active">添加问卷调查</li>
    </ol>
</div>

<div class="row" style="margin-top: 20px;">
    <div class="col-sm-12">
        <div class="login-panel panel panel-info">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-12 col-md-12 col-lg-12 text-left"><strong>添加问卷调查</strong></div>
                </div>
            </div>
            <div class="panel-body">
                <div class="box box-default" style="width: 10%;position: fixed;bottom: 40px;z-index: 1">
                    <div class="box-body">
                        <div class="row pb20 pt10 single-topic-condition">
                            <div class="col-xs-12 col-lg-12">
                                请添加题型:
                            </div>
                            <div class="col-xs-12 col-lg-12" style="text-align: center;margin-top: 5px;">
                                <a href="javascript:add_topic_div(0);" id="add_radio"
                                   class="mybtn btn-add-user dropdown-toggle" style="padding: 2px 15px;"><i
                                        class="fa fa-dot-circle-o"></i>单选题</a>
                            </div>
                            <div class="col-xs-12 col-lg-12" style="text-align: center;margin-top: 5px;">
                                <a href="javascript:add_topic_div(1);" id="add_checkbox"
                                   class="mybtn btn-add-user dropdown-toggle" style="padding: 2px 15px;"><i
                                        class="fa fa-check-square-o"></i>多选题</a>
                            </div>
                            <div class="col-xs-12 col-lg-12" style="text-align: center;margin-top: 5px;">
                                <a href="javascript:add_topic_div(2);" id="add_textarea"
                                   class="mybtn btn-add-user dropdown-toggle" style="padding: 2px 15px;"><i
                                        class="fa fa-pencil-square-o"></i>填空题</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="box box-default">
                    <form id="survey_save" class="form-horizontal" method="post" action="poll/save"
                          enctype="multipart/form-data" target="nm_iframe">
                        <div class="box-body" id="page_content">
                            <div class="row pb20 pt10 single-topic-condition">
                                <div class="col-xs-12 col-lg-12">
                                    <div class="clearfix" style="float:left">
                                        本次调查的时间<span style="color: #ff5704;">*</span>:
                                    </div>
                                    <div class="input-group date ml10" style="width:175px;float: left;">
                                        <input type="text"
                                               style="display: block;width: 100%;line-height: 20px;padding: 6px 12px;border: 1px solid #ccc;"
                                               name="datetimeStart" id="datetimeStart" readonly UNSELECTABLE="on">
                                        <i class="fa fa-calendar" style="position: absolute;top: 11px;right: 5px;"></i>
                                    </div>
                                    <div style="float: left;padding-top: 6px;margin-left: 5px;margin-right: 5px;">至
                                    </div>
                                    <div class="input-group date" style="width:175px;">
                                        <input type="text"
                                               style="display: block;width: 100%;line-height: 20px;padding: 6px 12px;border: 1px solid #ccc;"
                                               name="datetimeEnd" id="datetimeEnd" readonly UNSELECTABLE="on">
                                        <i class="fa fa-calendar" style="position: absolute;top: 11px;right: 5px;"></i>
                                    </div>
                                </div>
                            </div>

                            <div class="row pb20 pt20 mlr-10 border-top-1">
                                <div class="col-xs-2 col-lg-2 single-topic-title">
                                    <i class="fa fa-file-text-o"></i> 标题<span style="color: #ff5704;">*</span>:
                                </div>
                                <div class="col-xs-8 col-lg-7 ml-40">
                                    <div class="col-sm-12 single-topic-title-discribe">
                                        <textarea id="survey_title" name="title" class="form-control" rows="1"
                                                  placeholder="请输入本次问卷的标题"></textarea>
                                    </div>
                                </div>
                            </div>

                            <div class="row pb20 pt20 mlr-10 border-top-1">
                                <div class="col-xs-2 col-lg-2 single-topic-title">
                                    <i class="fa fa-share-square-o"></i> 描述:
                                </div>
                                <div class="col-xs-8 col-lg-7 ml-40">
                                    <div class="col-sm-12 single-topic-title-discribe">
                                        <textarea id="survey_des" name="des" class="form-control" rows="3"
                                                  placeholder="请输入本次问卷的目的描述"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="box-footer" style="padding-bottom: 30px;padding-top: 30px;">
                            <div class="row">
                                <div class="col-xs-4 col-lg-4"></div>
                                <div class="col-xs-8 col-lg-8 col-xs-offset-2 col-lg-offset-2 mt20 ml-40">
                                    <button type="button" class="btn btn-primary" id="sub_topic"
                                            onclick="save_survey();">确认
                                    </button>
                                    <button type="button" class="btn btn-default" id="d_topic">取消</button>
                                </div>
                            </div>
                        </div>
                    </form>

                    <iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
                </div>
            </div><!-- ./end of panelbody -->
        </div><!-- ./end of panel -->
    </div><!-- ./end of col -->
</div>

<script type="text/javascript">
    var topicCount = 0;
    $(function () {
        var start = {
            hmsSetVal: {hh: 00, mm: 00, ss: 00},
            isinitVal: false,
            festival: false,
            format: 'YYYY-MM-DD hh:mm:ss',
            maxDate: '2199-06-16 23:59:59', //最大日期
            choosefun: function (elem, val, date) {
                end.minDate = date; //开始日选好后，重置结束日的最小日期
                //end.trigger = false;//弹出结束时间
                $("#datetimeEnd").jeDate(end);
            },
            clearfun: function (elem, val, date) {
                end.minDate = "1900-01-01 00:00:00";//设定最小日期为当前日期
                $("#datetimeEnd").jeDate(end);
            }
        };

        var end = {
            hmsSetVal: {hh: 00, mm: 00, ss: 00},
            isinitVal: false,
            festival: false,
            format: 'YYYY-MM-DD hh:mm:ss',
            maxDate: '2199-06-16 23:59:59', //最大日期
            choosefun: function (elem, val, date) {
                start.maxDate = date; //将结束日的初始值设定为开始日的最大日期
            },
            clearfun: function (elem, val, date) {
                start.maxDate = '2199-06-16 23:59:59';
                $("#datetimeEnd").jeDate(end);
                $("#datetimeStart").jeDate(start);
            }
        };

        $.jeDate('#datetimeStart', start);
        $.jeDate('#datetimeEnd', end);


        $("#d_topic").click(function () {
            loadPageForNav("main/pollManage.api");
        });


    });

    //第一步,添加题目div
    function add_topic_div(sign) {

        //200题，根据id=page_content 下有多少个子div判断
        if (($("#page_content").children().length - 3) < 200) {
            var opt_title = '<div class="list-wrap mt15"><span name="num_s">Q' + ($("#page_content").children().length - 2) + ':</span><input name="surveyTopicVos[' + ($("#page_content").children().length - 3) + '].opts_title" type="text">&nbsp;(填空)</div>';

            var div = '<div class="row pb20 pt20 mlr-10 border-top-1">' +
                '<button type="button" onclick="removeTopicDiv(this)" class="btn btn-box-tool"><i class="fa fa-times"></i></button>' +
                '<div class="col-xs-4 col-lg-4 single-topic-title"><input type="hidden" name="surveyTopicVos[' + ($("#page_content").children().length - 3) + '].topicTypeNum" value="' + sign + '"></div>' +
                '<div class="col-xs-8 col-lg-8 ml-40 single-topic-answer-list">' +
                '<div class="">' +
                '<div class="answer-list-default" style="padding-left: 20px;">' + opt_title +
                '</div>' +
                '<div class="answer-list-default"><textarea readonly="readonly" class="form-control" rows="3" style="margin-top: 10px;width: 400px;margin-left: 66px;"></textarea>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
            if (sign != 2) {
                var opt = load_option(sign, ($("#page_content").children().length - 3));
                opt_title = '<div class="list-wrap mt15" style="margin-left: 18px;"><span name="num_s">Q' + ($("#page_content").children().length - 2) + ':</span><input name="surveyTopicVos[' + ($("#page_content").children().length - 3) + '].opts_title" type="text">&nbsp;(单选)</div>';
                if (sign == 1) {
                    opt_title = '<div class="list-wrap mt15" style="margin-left: 18px;"><span name="num_s">Q' + ($("#page_content").children().length - 2) + ':</span><input name="surveyTopicVos[' + ($("#page_content").children().length - 3) + '].opts_title" type="text">&nbsp;(多选)</div>';
                }
                div = '<div class="row pb20 pt20 mlr-10 border-top-1">' +
                    '<button type="button" onclick="removeTopicDiv(this)" class="btn btn-box-tool"><i class="fa fa-times"></i></button>' +
                    '<div class="col-xs-4 col-lg-4 single-topic-title"><input type="hidden" name="surveyTopicVos[' + ($("#page_content").children().length - 3) + '].topicTypeNum" value="' + sign + '"></div>' +
                    '<div class="col-xs-8 col-lg-8 ml-40 single-topic-answer-list">' +
                    '<div class="">' +
                    '<div class="answer-list-default" style="padding-left: 20px;">' + opt_title +
                    '</div>' +
                    '<div class="answer-list-default">' + opt +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="col-xs-7 col-lg-7 col-xs-offset-4 col-lg-offset-4 mt20">' +
                    '<a href="javascript:void(0);" onclick="add_opt_line(this)" class="mybtn btn-add-user2"><i class="fa fa-plus"></i>添加选项</a>' +
                    '</div>' +
                    '</div>';
            }

            $("#page_content").append(div);
            topicCount++;
        }

    }

    //删除题目div
    function removeTopicDiv(obj) {
        //动态添加的根div
        var topic_div_obj = $(obj).parent().parent().children();

        var c = $(obj).parent().children();
        var toptitle = $(c[2]).children().children().children().children();
        //题目编号
        var topicNum = $(toptitle[0]).html().replace("Q", "").replace(":", "");
        //判断当前删除的题目 是不是 最后一题，如不是，则更改此编号后面题目的所有编号
        if (topicNum == topicCount) {

        } else {
            //循环次数，改变问题编号
            var fori = topicCount - parseInt(topicNum, 10)
            for (var i = 0; i < fori; i++) {
                var nextdiv = topic_div_obj[parseInt(topicNum, 10) + 3 + i];
                var newtopicTitle = $(nextdiv).children().children().children().children().children();
                var newNum = parseInt($(newtopicTitle[0]).html().replace("Q", ""), 10) - 1;
                $(newtopicTitle[0]).html("Q" + newNum + ":");
                //更改题目名称topic_title下标
                var name_sign = $(newtopicTitle[1]).attr("name");
                var a = name_sign.substring(0, name_sign.indexOf("[") + 1);
                var b = name_sign.substring(name_sign.indexOf("]"), name_sign.length);
                $(newtopicTitle[1]).attr("name", a + (newNum - 1) + b);
                //更改题目类型topic_type下标
                var topicType_name = $($(nextdiv).children()[1]).children().attr("name");
                var ta = topicType_name.substring(0, topicType_name.indexOf("[") + 1);
                var tb = topicType_name.substring(topicType_name.indexOf("]"), topicType_name.length);
                $($(nextdiv).children()[1]).children().attr("name", ta + (newNum - 1) + tb);

                var opt_div = $(nextdiv).find("div[class='answer-list-default']")[1];
                $(opt_div).find("input").each(function () {
                    var old_name = $(this).attr("name");
                    var old_name_a = old_name.substring(0, old_name.indexOf('[') + 1);
                    var old_name_b = old_name.substring(old_name.indexOf(']'), old_name.length);
                    $(this).attr("name", old_name_a + (newNum - 1) + old_name_b);
                });
            }
        }

        $(obj).parent().remove();
        topicCount--;
    }

    //点击题型后，默认载入选项
    //topic_sign 题目下标
    function load_option(sign, topic_sign) {
        var sfor = 3;//循环次数
        if (sign == 0) {//单选
            sfor = 3;
        } else if (sign == 1) {//多选
            sfor = 4;
        }
        var str = "";
        for (var i = 0; i < sfor; i++) {
            var file_input = '<a href="javascript:void(0);" style="margin-left: 5px;"><i class="glyphicon glyphicon-picture"><input type="file" onclick="opt_change_file(this)" name="surveyTopicVos[' + topic_sign + '].surveyOptVos[' + i + '].myfile" style="position: absolute;opacity: 0;z-index: 1;top: -15px;left: -20px;width:1px;"  /></i></a>';
            if (i + 1 == sfor) {
                str = str + '<div class="list-wrap mt15"><i name="opt_c" style="display:none;" onclick="check_focus(this)" class="fa fa-circle-o"><input style="display: none;" name="surveyTopicVos[' + topic_sign + '].surveyOptVos[' + i + '].ans" value="0"></i><span name="num_s" style="display: block;float: left;width: 65px;line-height: 40px;text-align: right;">选项' + (i + 1) + ':</span><input name="surveyTopicVos[' + topic_sign + '].surveyOptVos[' + i + '].optValue" type="text">' + file_input + '<b style="position: relative;z-index: 2;" onclick="remove_opt(this)">-</b><div style="margin-left: 80px;margin-top: 5px;"><img style="display: none;" width="120" height="212" src="resource/assets/img/cp_05.jpg" /><i onclick="remove_img(this)" class="glyphicon glyphicon-remove" style="display: none;"></i></div></div>';
            } else {
                str = str + '<div class="list-wrap mt15"><i name="opt_c" style="display:none;" onclick="check_focus(this)" class="fa fa-circle-o"><input style="display: none;" name="surveyTopicVos[' + topic_sign + '].surveyOptVos[' + i + '].ans" value="0"></i><span name="num_s" style="display: block;float: left;width: 65px;line-height: 40px;text-align: right;">选项' + (i + 1) + ':</span><input name="surveyTopicVos[' + topic_sign + '].surveyOptVos[' + i + '].optValue" type="text">' + file_input + '<div style="margin-left: 80px;margin-top: 5px;"><img style="display: none;" width="120" height="212" src="resource/assets/img/cp_05.jpg" /><i onclick="remove_img(this)" class="glyphicon glyphicon-remove" style="display: none;"></i></div></div>';
            }
        }
        return str;
    }

    //点击单个添加选项按钮
    function add_opt_line(obj) {
        //动态添加的根div
        var div_obj = $(obj).parent().parent();
        //当前选项div
        var div_opt = $($(div_obj.children()[2]).children().children()[1]);

        //获得当前题目序号
        var span_name = div_obj.find("div[class = 'answer-list-default']")[0];
        //当前题目序列号
        var topic_num = parseInt($(span_name).find("span").html().replace("Q", "").replace(":", ""), 10);

        var str = "";
        var file_input = '<a href="javascript:void(0);" style="margin-left: 5px;"><i class="glyphicon glyphicon-picture"><input type="file" onclick="opt_change_file(this)" name="surveyTopicVos[' + (topic_num - 1) + '].surveyOptVos[' + div_opt.children().length + '].myfile" style="position: absolute;opacity: 0;z-index: 1;top: -15px;left: -20px;width:1px;"  /></i></a>';
        str = '<div class="list-wrap mt15"><i name="opt_c" style="display:none;" onclick="check_focus(this)" class="fa fa-circle-o"><input style="display: none;" name="surveyTopicVos[' + (topic_num - 1) + '].surveyOptVos[' + div_opt.children().length + '].ans" value="0"></i><span name="num_s" style="display: block;float: left;width: 65px;line-height: 40px;text-align: right;">选项' + (div_opt.children().length + 1) + ':</span><input name="surveyTopicVos[' + (topic_num - 1) + '].surveyOptVos[' + div_opt.children().length + '].optValue" type="text">' + file_input + '<b style="position: relative;z-index: 2;" onclick="remove_opt(this)">-</b><div style="margin-left: 80px;margin-top: 5px;"><img style="display: none;" width="120" height="212" src="resource/assets/img/cp_05.jpg" /><i onclick="remove_img(this)" class="glyphicon glyphicon-remove" style="display: none;"></i></div></div>';
        $(div_opt).append(str);
        
		//获得选项列表div
		var optNumDiv = div_obj.find("div[class = 'answer-list-default']")[1];
		//选项总数
		var optNum = $(optNumDiv).children().length;
		if(optNum >= 200){
			$(obj).parent().hide();
		}
    }

    //删除选项
    function remove_opt(obj) {
        //动态添加的根div
        var div_obj = $(obj).parent().parent().parent().parent().parent();
        //获得当前题目序号
        var span_name = div_obj.find("div[class = 'answer-list-default']")[0];
        //当前题目序列号
        var topic_num = parseInt($(span_name).find("span").html().replace("Q", "").replace(":", ""), 10);

        //当前选择的选项div
        var current_opt_div = $(obj).parent();
        //当前选项编号
        var span_num_s = $(current_opt_div).find("span[name='num_s']").html();
        var span_num = parseInt(span_num_s.replace("选项","").replace(":",""),10);
        
        //当前题目有多少选项
        var opt_count = $(obj).parent().parent().children().length

        //判断当前删除的选项 是不是 最后一项，如不是，则更改此编号后面选项的所有编号
        if (span_num == opt_count) {

        } else {
            //循环次数，改变选项编号
            var fori = opt_count - span_num;
            for (var i = 0; i < fori; i++) {
                var nextdiv = $(obj).parent().parent().children()[span_num + i];
                var old_num = $(nextdiv).find("span[name='num_s']").html();
                old_num = parseInt(old_num.replace(/[^0-9]/ig, ""), 10);
                $(nextdiv).find("span[name='num_s']").html('选项' + (old_num - 1) + ':');

                //是否正确答案
                var an = $(nextdiv).find("input")[0];
                var an_name = $(an).attr("name");
                var an_name_a = an_name.substring(0, an_name.lastIndexOf('[') + 1);
                var an_name_b = an_name.substring(an_name.lastIndexOf(']'), an_name.length);

                //选项值
                var v = $(nextdiv).find("input")[1];
                var v_name = $(v).attr("name");
                var v_name_a = v_name.substring(0, v_name.lastIndexOf('[') + 1);
                var v_name_b = v_name.substring(v_name.lastIndexOf(']'), v_name.length);

                //选项文件
                var f = $(nextdiv).find("input")[2];
                var f_name = $(f).attr("name");
                var f_name_a = f_name.substring(0, f_name.lastIndexOf('[') + 1);
                var f_name_b = f_name.substring(f_name.lastIndexOf(']'), f_name.length);
                //重置下标
                $(f).attr("name", f_name_a + (old_num - 2) + f_name_b);
                $(v).attr("name", v_name_a + (old_num - 2) + v_name_b);
                $(an).attr("name", an_name_a + (old_num - 2) + an_name_b);
            }
        }

        $(current_opt_div).remove();
        var opt_count = $(obj).parent().parent().children().length;
        if(opt_count < 200){
        	$($(div_obj).children()[3]).show();
        }
    }

    //文件上传按钮
    function opt_change_file(obj) {
    	$(obj).change(function(){
    		var file = this.files[0];
    		var fileSize = file.size / 1024;
    		var fileName = file.name;
    		var ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    		if(fileSize > 200){
    			layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>图片大小应小于200KB!", {
    				time: 2000
    			});
    			$(obj).attr("value", "");
    			return false;
    		}
    		
    		if(["jpg", "jpeg", "png"].indexOf(ext) < 0){
    			layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>图片类型错误!", {
    				time: 2000
    			});
    			$(obj).attr("value", "");
    			return false;
    		}
    		
    	});
    	$(obj).uploadPreviewNoImg({ obj: $(obj).parent().parent().parent().find('img')[0], Width: 120, Height: 212 });
    }

    //选项选中
    function check_focus(obj) {
        //题目类型
        var topicType = $($(obj).parent().parent().parent().parent().parent().children()[1]).find("input").val();

        var this_obj = $(obj);
        var this_class = this_obj.attr("class");
        if (topicType == 0) {//单选
            if (this_class == "fa fa-circle-o") {
                this_obj.parent().parent().children().find("input[name = 'ans']").each(function () {
                    $(this).attr("value", "0");
                });

                this_obj.parent().parent().children().find("i[name = 'opt_c']").each(function () {
                    $(this).attr("class", "fa fa-circle-o");
                    $(this).parent().removeClass("active");
                    $(this).children("input").attr("value", "0")
                });

                this_obj.parent().addClass("active");
                this_obj.attr("class", "fa fa-check-circle");
                this_obj.children("input").attr("value", "1");

            }
        } else if (topicType == 1) {//多选
            if (this_class == "fa fa-circle-o") {
                this_obj.parent().addClass("active");
                this_obj.attr("class", "fa fa-check-circle");
                this_obj.children("input").attr("value", "1");
            } else if (this_class == "fa fa-check-circle") {
                this_obj.parent().removeClass("active");
                this_obj.attr("class", "fa fa-circle-o");
                this_obj.children("input").attr("value", "0");
            }
        }
    }

    //删除图片
    function remove_img(obj) {
        var ob = $(obj).parent().parent().find("a")[0];
        //清空file文件
        $(ob).children().children().val("");
        //隐藏图片显示
        var img = $(obj).parent().find("img");
        var i = $(obj).parent().find("i");
        $(img).attr("src", "");
        $(img).attr("style", "display: none;");
        $(i).attr("style", "display: none;");
    }

    function save_survey() {
        var sign = 0;

        var survey_title = $("#survey_title").val();
        var survey_des = $("#survey_des").val();
        var survey_datetimeStart = $("#datetimeStart").val();
        var survey_datetimeEnd = $("#datetimeEnd").val();
        var currentDate = new Date();
        var currentDatetime = currentDate.setHours(0, 0, 0, 0);
        var start = new Date(survey_datetimeStart.replace("-", "/").replace("-", "/"));
        var end = new Date(survey_datetimeEnd.replace("-", "/").replace("-", "/"));

        //调查时间，检查
        if (!validate(survey_datetimeStart, 1)) {
            layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>调查开始时间不可为空，请检查!", {
                time: 2000
            });
            sign = 1;
            return false;
        }

        //调查时间，检查
        if (!validate(survey_datetimeEnd, 1)) {
            layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>调查结束时间不可为空，请检查!", {
                time: 2000
            });
            sign = 1;
            return false;
        }
        
      	//调查时间不能为过去时间
        if(start.getTime() < currentDatetime || end.getTime() < currentDatetime){
        	layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>输入的时间不正确，请重新输入!", {
                time: 2000
            });
            sign = 1;
            return false;
        }

        //调查标题，检查
        if (!validate(survey_title, 1) || survey_title.length < 1 || survey_title.length > 200) {
            layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>调查标题需填写1至200字符，请检查!", {
                time: 2000
            });
            sign = 1;
            return false;
        }

        //调查描述，检查
        /* if (!validate(survey_des, 1) || survey_des.length < 1 || survey_des.length > 200) {
            layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>调查描述需填写1至200字符，请检查!", {
                time: 2000
            });
            sign = 1;
            return false;
        } */

        if ($("#page_content").children().length == 3) {
            layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>请添加选题!", {
                time: 2000
            });
            sign = 1;
            return false;
        }

        $("#page_content").children().each(function () {
            //判断是否在  题目 div 层
            if ($(this).children().length == 4) {
                //题目div
                var topic_div = $(this).children().find("div [class='answer-list-default']")[0];
                //题目类型 0:单选  1:多选
                var topic_type = $($(this).children()[1]).find("input").val();
                //选项div
                var opt_div = $(this).children().find("div [class='answer-list-default']")[1];

                //题目名称
                var topic_title = $($(topic_div).find("input")[0]).val();
                //题目标题
                var topic_sign = $($(topic_div).find("span")[0]).html();

                //检查题目是否为空
                if (!validate(topic_title, 1) || topic_title.length < 1 || topic_title.length > 200) {
                    layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>" + topic_sign + "题目名称需填写1至200字符，请检查!", {
                        time: 2000
                    });
                    sign = 1;
                    return false;
                }

                //检查是否勾选正确答案
                var ans = 0;
                var opt_value_sign = 0;
                $(opt_div).children().each(function () {
                    ans = ans + parseInt($($(this).find("i")[0]).children("input").val(), 10);

                    var opt_value = $($(this).find("input")[1]).val();
                    if (!validate(opt_value, 1) || opt_value.length < 1 || opt_value.length > 200) {
                        opt_value_sign = 1;
                        return false;
                    }
                })

                if (opt_value_sign == 1) {
                    layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>" + topic_sign + "选项须填写1至200字符，请检查!", {
                        time: 2000
                    });
                    sign = 1;
                    return false;
                }

                /* if (topic_type == 0) {
                    if (ans == 0) {
                        layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>" + topic_sign + "未选择正确答案，请检查!", {
                            time: 2000
                        });
                        sign = 1;
                        return false;
                    }
                } else if (topic_type == 1) {
                    if (ans < 2) {
                        layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>" + topic_sign + "请选择2个及以上正确答案，请检查!", {
                            time: 2000
                        });
                        sign = 1;
                        return false;
                    }
                } */

            } else if ($(this).children().length == 3) {
                //题目div
                var topic_div = $(this).children().find("div [class='answer-list-default']")[0];
                //题目名称
                var topic_title = $($(topic_div).find("input")[0]).val();
                //题目标题
                var topic_sign = $($(topic_div).find("span")[0]).html();

                //检查题目是否为空
                if (!validate(topic_title, 1) || topic_title.length < 1 || topic_title.length > 200) {
                    layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>" + topic_sign + "题目名称需填写1至200字符，请检查!", {
                        time: 2000
                    });
                    sign = 1;
                    return false;
                }
            }
        });

        if (sign == 0) {
            $("#survey_save").submit();
            $("#sub_topic").attr("disabled", true);
            $("#id_iframe").load(function () {
                loadPageForNav("main/pollManage.api");
            });
        }
    }

    function validate(data, type) {
        if (!data || data.length == 0 || $.trim(data) == "") {
            return false;
        } else if (type == 0) {//正整数
            var reg = /^\+?[1-9][0-9]*$/;
            if (reg.test(data)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

</script>
	
