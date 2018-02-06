<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Content Header (Page header) -->
<div class="row">
    <ol class="breadcrumb col-xs-12 col-md-12 col-lg-12">
        <li><a href="main">主页</a></li>
        <li><a onclick='loadPageForNav("main/pollManage.api")' href="javascript:void(0);">民调列表</a></li>
        <li class="active">${title}统计</li>
    </ol>
</div>

<!-- content div -->
<div class="row" style="margin-top: 20px;">
    <div class="col-sm-12">
        <div class="login-panel panel panel-info">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-12 col-md-12 col-lg-12 text-center"><strong>${title}统计</strong></div>
                </div>
            </div>
            <div class="panel-body">
                <div id="pollList"/>
            </div><!-- ./end of panelbody -->
        </div><!-- ./end of panel -->
    </div><!-- ./end of col -->
</div>
<!-- ./end of content div -->

<input class="hidden" id="surveyId" value="${id}">
<script type="text/javascript">
    //每页显示5条记录
    var tabTotal = 5;

    function parseOption(index, data) {
        var result = "<div class=\"row\">";
        var qindex = (index + 1).toString() + ".";
        result += "<div class=\"col-xs-12 mt20\"><span style=\"font-size:16px\">Q" + qindex + data.title + "</span></div>";

        result += "<div class=\"col-xs-12 mingdiao-table\"><table class='table table-bordered'><tr><th width=\"30%\">选项1</th><th width=\"30%\">小计</th><th width=\"40%\">比例</th></tr>";
        $(data.options).each(function (index, option) {
            result += "<tr><td>" + option.content + "</td>" + "<td>" + option.num + "</td>" +
                "<td><table width=\"80%\"><tr><td width=\"80%\"><div class=\"progress\"><div class=\"progress-bar\" style=\"width:" + option.percent + "%\"/></div></td><td width=\"20%\" style=\"text-align: center\">" + option.percent + " %</td></tr></table></td>" +
                "</tr>";
        });
        result += "<tr><th>本题有效填写人次</th><th>" + data.sum + "</th><th></th></tr>";

        result += "</table></div>";
        result += "</div>";
        return result;
    }

    function parseReply(index, data) {
        var result = "<div class=\"row\">";
        var qindex = (index + 1).toString() + ".";
        result += "<div class=\"col-xs-12 mt20\"><span style=\"font-size:16px\">Q" + qindex + data.title + "</span></div>";
        result += "<div class=\"col-xs-12 mingdiao-table\"><table id='reply_" + data.id + "' class='table table-bordered'><tr><th>回复情况</th></tr>";
        replyData.set("reply_" + data.id, data.options);
        $(data.options).each(function (index, option) {
            if (index < tabTotal) {
                result += "<tr><td>" + option.content + "</td></tr>";
            }
        });
        result += "<tr><th>答题人数：" + data.sum + "</th></tr></table></div>";
        result += "<div id='paginator_" + data.id + "' name='paginator'></div>";
        result += "</div>";
        return result;
    }

    var replyData = new Map();
    var id = $("#surveyId").val();
    $(function () {
        $.ajax({
            type: "GET",
            url: 'poll/report?id=' + id,
            dataType: 'json',
            async: false,
            success: function (data) {
                if (data.length <= 0) {
                    layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>没有数据！", {
                        time: 2000
                    });
                    return;
                }
                var pollList = "";
                $(data).each(function (index, value) {
                    if (value.type == "option") {
                        pollList += parseOption(index, value);
                    }

                    if (value.type == "reply") {
                        pollList += parseReply(index, value);
                    }
                });
                $("#pollList").html(pollList);
                $($("div[name='paginator']")).each(function (index, value) {
                    var currentPaginator = $("div[name='paginator']").get(index);
                    var paginatorId = $(currentPaginator).attr("id");
                    var idNum = paginatorId.substring(paginatorId.indexOf("_") + 1);
                    var total = Math.ceil(replyData.get("reply_" + idNum).length / tabTotal);
                    $(currentPaginator).bootstrapPaginator({
                        totalPages: total,
                        onPageChanged: function (e, oldPage, newPage) {
                            var paginatorId = $(this).attr("id");
                            var idNum = paginatorId.substring(paginatorId.indexOf("_") + 1);
                            var tableId = "reply_" + idNum;
                            var tableData = replyData.get(tableId);
                            var trLength = $("#" + tableId + " tr").length;
                            for (var i = 1; i < trLength - 1; i++) {
                                $($("#" + tableId + " tr").get(1)).remove();
                            }

                            var currentTrs = "";
                            for (var i = 1; i < (tabTotal + 1); i++) {
                                if (((newPage - 1) * tabTotal + i - 1) < tableData.length) {
                                    currentTrs += "<tr><td>" + tableData[(newPage - 1) * tabTotal + i - 1].content + "</td></tr>";
                                }
                            }

                            $($("#" + tableId + " tr").get(0)).after(currentTrs);
                        },
                        tooltipTitles: function (type, page, current) {
                            switch (type) {
                                case "first":
                                    return "首页";
                                case "prev":
                                    return "前一页";
                                case "next":
                                    return "后一页";
                                case "last":
                                    return "末页";
                                case "page":
                                    return "第" + page + "页";
                            }
                        }
                    });
                });
            }, error: function () {
                layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>错误！", {
                    time: 2000
                });
            }
        });
    });
</script>

