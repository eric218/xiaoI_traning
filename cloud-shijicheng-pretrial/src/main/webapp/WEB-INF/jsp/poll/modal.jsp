<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 发布 的提示框 -->
<div class="modal fade tips1" id="confirm_survey" tabindex="-1" role="dialog" aria-labelledby="myModalLabel6">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
      	<div class="row tips-title">
      		确定要发布吗？<br>(若之前已有发布内容，则会自动更新为目前这条记录)
      	</div>
      	<div class="text-center mt20">
      		<a href="javascript:;" id="del_btn" class="mybtn btn-cancel ml10" onclick="survey_confirm()" onmouseover="setStyle('del_btn')">确定</a>
   			<a href="javascript:;" id="cancle_btn" class="mybtn btn-save" data-dismiss="modal" onmouseover="setStyle('cancle_btn')">取消</a>
      	</div>
      </div>
    </div>
  </div>
</div>

<!-- 取消发布 的提示框 -->
<div class="modal fade tips1" id="confirm_non_survey" tabindex="-1" role="dialog" aria-labelledby="myModalLabel7">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
      	<div class="row tips-title">
      		确定取消发布吗？<br>
      	</div>
      	<div class="text-center mt20">
      		<a href="javascript:;" id="del_btn" class="mybtn btn-cancel ml10" onclick="non_survey_confirm()" onmouseover="setStyle('del_btn')">确定</a>
   			<a href="javascript:;" id="cancle_btn" class="mybtn btn-save" data-dismiss="modal" onmouseover="setStyle('cancle_btn')">取消</a>
      	</div>
      </div>
    </div>
  </div>
</div>




	
<!-- 带操作的删除提示框 -->
<div class="modal fade tips1" id="confirm_delete_survey" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
      	<div class="row tips-title">
      		<input id="deleteTopicId" type="hidden"/> 确定要删除问卷调查吗？
      	</div>
      	<input type="hidden" value="" name="countTopbyexam" id="countTopbyexam"/>
      	<div class="text-center mt20">
      		<a href="javascript:;" id="del_survey" class="mybtn btn-cancel ml10" onmouseover="setStyle('del_btn')">确定</a>
   			<a href="javascript:;" id="cancle_btn" class="mybtn btn-save" data-dismiss="modal" onmouseover="setStyle('cancle_btn')">取消</a>
      	</div>
      </div>
    </div>
  </div>
</div>

<!-- 批量删除题目提示框 -->
<div class="modal fade tips1" id="confirm_group_delete_topic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row tips-title">
					 确定要批量删除此题目记录吗？<br>(只能删除没有被选为考核的题目)
                </div>
                <div class="text-center mt20">
                    <a id="CONFIRM_DELETE_ALL" href="javascript:;" class="mybtn btn-cancel ml10">确定</a>
                    <a href="javascript:;" class="mybtn btn-save layer1" data-dismiss="modal">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 高级搜索 -->
<div class="modal fade advancedSearch2" id="topic_sec" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header text-center">
        <button type="button" class="close" onclick="search_rest()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">高级搜索</h4>
      </div>
      <div class="modal-body">
        <div class="row mt20">
			<div class="col-sm-2 text-right">
				试题类型:
			</div>
			<div class="col-sm-4 pr0">
				<select id="sh_topictype" class="form-control">
    					<option value=''>全部</option>
    					<option value="0">单选题</option>
    					<option value="1">多选题</option>
    				</select>
			</div>
			<div class="col-sm-2 text-right pl0">
				试题状态:
			</div>
			<div class="col-sm-4">
				<select id="sh_topicstatus" class="form-control">
					<option value=''>全部</option>
					<option value="0">生效</option>
					<option value="1">失效</option>
				</select>
			</div>
        </div>
        <div class="row mt20">
			<div class="col-sm-2 text-right">
				题库名称:
			</div>
			<div class="col-sm-4 pr0">
				<select id="sh_libid" class="form-control"></select>
			</div>
			<div class="col-sm-2 text-right pl0">
				创建人:
			</div>
			<div class="col-sm-4"><input id="sh_ctname" class="form-control" type="text" placeholder="请输入创建人姓名" /></div>
        </div>
        <div class="row mt20">
       		<div class="col-sm-2 text-right">创建日期:</div>
        	<div class="col-sm-5">
				<input type="text" style="display: block;width: 100%;line-height: 20px;padding: 6px 12px;border: 1px solid #ccc;" id="datetimeStart" readonly="readonly" UNSELECTABLE="on">
				<i class="fa fa-calendar" style="position: absolute;top: 11px;right: 25px;"></i>
        	</div>
        	<div style="width: 15px;float: left;margin-left: -7px;margin-right: -8px;">
	        	至
        	</div>
        	<div class="col-sm-5">
        		<input type="text" style="display: block;width: 100%;line-height: 20px;padding: 6px 12px;border: 1px solid #ccc;" id="datetimeEnd" readonly="readonly" UNSELECTABLE="on">
        		<i class="fa fa-calendar" style="position: absolute;top: 11px;right: 25px;"></i>
        	</div>
        </div>
      </div>
      <div class="modal-footer text-center">
        <a href="javascript:;" onclick="search_ex()" class="mybtn btn-save">搜索</a>
		<a href="javascript:;" onclick="search_rest()" class="mybtn btn-cancel ml10">取消</a>
      </div>
    </div>
  </div>
</div>


<!-- 带操作的提示框 -->
<div class="modal fade tips1" id="tips3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
      	<div class="row tips-title">
      		当前没有题库， 请先创建一个题库！
      	</div>
      	<div class="text-center mt20">
      		<a href="javascript:;" id="lib_show" class="mybtn btn-save">确定</a>
      	</div>
      </div>
    </div>
  </div>
</div>
