<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Content Header (Page header) -->
<div class="row">
    <ol class="breadcrumb col-xs-12 col-md-12 col-lg-12">
        <li><a href="main">主页</a></li>
        <li><a onclick='loadPageForNav("main/pollManage.api")' href="javascript:void(0);">民调管理</a></li>
    </ol>
</div>

<!-- content div -->
<div class="row" style="margin-top: 20px;">
    <div class="col-sm-12">
        <div class="login-panel panel panel-info">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-6 col-md-6 col-lg-6 text-left"><strong>民调管理</strong></div>
                    <div class="col-xs-6 col-md-6 col-lg-6 text-right">
                        <button type="button" onclick="addPoll();" class="btn btn-primary btn-default"
                                style="background-color:#37A8F8;border-radius:15px;">添加问卷调查
                        </button>
                        <button type="button" onclick="surveyconfirm();" class="btn btn-primary btn-default"
                                style="background-color:#37A8F8;border-radius:15px;">发布
                        </button>
                        <button type="button" onclick="nonsurveyconfirm();" class="btn btn-primary btn-default"
                                style="background-color:#37A8F8;border-radius:15px;">取消发布
                        </button>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <table id="topic_list"></table>
            </div><!-- ./end of panelbody -->
        </div><!-- ./end of panel -->
    </div><!-- ./end of col -->
</div>

<!-- 发布 的提示框 -->
<div class="modal fade" id="confirm_survey" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">取消</span></button>
                <h4 class="modal-title">发布</h4>
            </div>
            <div class="modal-body">
                <p>确定要发布吗？<br>(若之前已有发布内容，则会自动更新为目前这条记录)</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="survey_confirm();">确认</button>
            </div>
        </div>
    </div>
</div>

<!-- 取消发布 的提示框 -->
<div class="modal fade" id="confirm_non_survey" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">取消</span></button>
                <h4 class="modal-title">取消发布</h4>
            </div>
            <div class="modal-body">
                <p>确定取消发布吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="non_survey_confirm();">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 带操作的删除提示框 -->
<div class="modal fade" id="confirm_delete_survey" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">取消</span></button>
                <h4 class="modal-title">确定要删除问卷调查</h4>
            </div>
            <div class="modal-body">
                <p>确定要删除问卷调查吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="del_survey">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var oTable;

    function addPoll() {
        $("#mainPan").empty().load('poll/add');
    }

    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#topic_list').bootstrapTable({
                url: 'poll/list',         //请求后台的URL（*）
                method: 'get',                      //请求方式（*）
                //toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: false,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                //sortOrder: "asc",                   //排序方式
                queryParams: oTableInit.queryParams,//传递参数（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch: true,
                showColumns: false,                  //是否显示所有的列
                showRefresh: false,                  //是否显示刷新按钮
                //minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                singleSelect: true,
                columns: [{
                    checkbox: true,
                    formatter: function (value, row, index) {
                        var ch_show = {
                            checked: false,
                            disabled: false
                        };
                        if (row.status == 1) {
                            ch_show.checked = true;
                            ch_show.disabled = true;
                        }
                        return ch_show;
                    }
                }, {
                    field: 'title',
                    title: '标题',
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (value != "" && value != null) {
                            var _ret = autoAddEllipsis(value, 20);
                            var _cutFlag = _ret.cutflag;
                            var _cutStringn = _ret.cutstring;
                            if ("1" == _cutFlag) {
                                return "<span title=" + value + ">" + _cutStringn + "...</span>";
                            } else {
                                return _cutStringn;
                            }
                        }
                    }
                }, {
                    field: 'surveyTime',
                    title: '调查时间',
                    align: 'center'
                }, {
                    field: 'status',
                    title: '发布状态',
                    align: 'center'
                }, {
                    field: 'creator',
                    title: '创建人',
                    align: 'center'
                }, {
                    field: 'createTime',
                    title: '创建时间',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var createTime = "";
                        var unixTimestamp = new Date(value);
                        createTime = unixTimestamp.toLocaleString();
                        return createTime;
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    align: 'center',
                    width: '80',
                    formatter: function (value, row, index) {
                        if (row.status == 1) {
                            var lookUp = '<div class="btn-group"><a class="glyphicon glyphicon-list-alt" ></a></div>';
                            var edit = '<div class="btn-group"><a class="glyphicon glyphicon-edit" ></a></div>';
                            var del = '<div class="btn-group"><a class="glyphicon glyphicon-trash" ></a></div>';
                        } else {
                            var lookUp = '<div class="btn-group"><a class="glyphicon glyphicon-list-alt" onclick="lookUpPoll(\'' + row.title + '\',' + row.id + ')"></a></div>';
                            var edit = '<div class="btn-group"><a class="glyphicon glyphicon-edit" onclick="editPoll(' + row.id + ')"' + '></a></div>';
                            var del = '<div class="btn-group"><a class="glyphicon glyphicon-trash" href="javascript:void(0);" onclick="deleteSurvey(' + row.id + ')"></a></div>';

                        }
                        return lookUp + '&nbsp;' + edit + '&nbsp;' + del + '&nbsp;';
                    }
                }]
            });
        };

        //得到查询的参数
        oTableInit.queryParams = function (params) {
            var pageNum = 0;
            if (0 != params.offset) {
                pageNum = params.offset / params.limit;
            }
            pageNum += 1;
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                pageSize: params.limit,   //页面大小
                pageNumber: pageNum  //页码
            };
            return temp;
        };
        return oTableInit;
    };

    function editPoll(pollId) {
        $.ajax({
            method: 'GET',
            url: 'poll/edit',
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            data: {
                surveyId: pollId
            },
            success: function (data) {
                $("#mainPan").html(data);
            }
        });
    }

    function lookUpPoll(pollTitle, pollId) {
        $.ajax({
            method: 'GET',
            url: 'poll/count',
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            data: {
                title: pollTitle,
                id: pollId
            },
            success: function (data) {
                $("#mainPan").html(data);
            }
        });
    }

    //删除问卷调查
    function deleteSurvey(surveyId) {
        $("#confirm_delete_survey").modal("show");
        $("#del_survey").off("click").on("click", function () {
            $.ajax({
                type: 'get',
                url: 'poll/delete',
                dataType: 'json',
                data: {
                    "surveyId": surveyId
                },
                success: function (data) {
                    $("#confirm_survey").modal("hide");
                    $(".modal-backdrop.fade.in").remove();
                    layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>删除成功！", {
                        time: 2000
                    });
                    loadPageForNav("main/pollManage.api");
                }, error: function () {
                    layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>网络异常，请稍后再试！", {
                        time: 2000
                    });
                }
            });
        });
    }

    // 发布
    function survey_confirm() {
        var rows = $('#topic_list').bootstrapTable('getSelections');
        if (rows != null && rows.length > 0) {
            for (var i = 0; i < rows.length; i++) {
                var surveyId = rows[i].id;
                $.ajax({
                    type: 'GET',
                    url: 'poll/publish',
                    data: {
                        "surveyId": surveyId
                    },
                    dataType: 'json',
                    success: function (data) {
                        $("#confirm_survey").modal("hide");
                        $(".modal-backdrop.fade.in").remove();
                        layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>发布成功！", {
                            time: 2000
                        });
                        loadPageForNav("main/pollManage.api");
                    }, error: function () {
                        layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>网络异常，请稍后再试！", {
                            time: 2000
                        });
                    }
                });
            }
        }
    }

    //取消 发布
    function non_survey_confirm() {
        $.ajax({
            type: 'GET',
            url: 'poll/unpublish',
            dataType: 'json',
            success: function (data) {
                $("#confirm_non_survey").modal("hide");
                $(".modal-backdrop.fade.in").remove();
                layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>清空发布记录成功！", {
                    time: 2000
                });
                loadPageForNav("main/pollManage.api");
            }, error: function () {
                layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>网络异常，请稍后再试！", {
                    time: 2000
                });
            }
        });
    }

    //'发布' button 的条件
    function surveyconfirm() {
        var rows = $('#topic_list').bootstrapTable('getSelections');
        if (rows == null || rows == "") {   //没有选择内容，则不响应
            layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>请选择发布的内容！", {
                time: 2000
            });
            return false;
        }
        else if (rows != null && rows.length > 0) {
            for (var i = 0; i < rows.length; i++) {
                if (rows[i].status == 1) { //有内容，但是status是1，也不响应
                    layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>此条记录正发布中，不能再次发布！", {
                        time: 2000
                    });
                    return false;
                }
                else {
                    $("#confirm_survey").modal("show");
                }
            }
        }
    }

    //'取消发布' button 的条件
    function nonsurveyconfirm() {
        var rows = $('#topic_list').bootstrapTable('getSelections');
        if (rows == null || rows == "") {   //没有选择，则不响应
            layer.msg("<i class='fa fa-exclamation-circle' style='color: #FFD700;font-size: 16px;margin-right: 5px;'></i>请选中选项后再操作！", {
                time: 2000
            });
            return false;
        }
        else {
            $("#confirm_non_survey").modal("show");
        }
    }

    //提示框鼠标移动特效
    function setStyle(btnId) {
        if (btnId == "del_btn") {
            $("#del_btn").attr("class", "mybtn btn-save");
            $("#cancle_btn").attr("class", "mybtn btn-cancel ml10");
        }
        if (btnId == "cancle_btn") {
            $("#cancle_btn").attr("class", "mybtn btn-save");
            $("#del_btn").attr("class", "mybtn btn-cancel ml10");
        }
    }

    $(function () {
        //1.初始化Table
        //console.log("初始化Table");
        oTable = new TableInit();
        oTable.Init();
        Date.prototype.toLocaleString = function () {
            return this.getFullYear() + "-" + addZeroForTime((this.getMonth() + 1)) + "-" + addZeroForTime(this.getDate()) + " " + addZeroForTime(this.getHours()) + ":" + addZeroForTime(this.getMinutes()) + ":" + addZeroForTime(this.getSeconds());
        };

        //补0操作
        function addZeroForTime(num) {
            if (parseInt(num) < 10) {
                num = '0' + num;
            }
            return num;
        }

        $('#topic_list').bootstrapTable('hideColumn', 'status');
    });
</script>