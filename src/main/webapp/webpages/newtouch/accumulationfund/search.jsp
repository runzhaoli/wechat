<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/jquery/ajaxfileupload.js<%=version %>"></script>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	$("#upload #commit").click(function() {
		var filepath = $("#file").val();
		if (filepath == null || filepath == "") {
			$("#messagecontext").html("请先选择一个需要上传的文件噢!");
			$("#upload #message").fadeIn(100);
			$(":button,:submit").attr("disabled", null);
			return false;
		}
		$(":button,:submit").attr("disabled", "disabled");
		$("#upload #message").fadeOut(100);
		$("#context").fadeOut(200, function() {
			$("#loading").fadeIn(200, function() {
				$.ajaxFileUpload({
					url : '<%=path %>/accumulationfund/excelupload<%=version %>',
					type : 'post',
					secureuri : false,			// 一般设置为false
					fileElementId : 'file',		// 上传文件的id、name属性名
					dataType : 'json',			// 返回值类型，一般设置为json、application/json
					success : function(data, status) {
						uploadcallback(data['result'], data['message']);
					},
					error : function(data, status, e) {
						uploadcallback(false, "文件上传失败：" + e);
					}
				});
			});
		});
	});
});
function uploadcallback(result, message) {
	if (result) {
		$('#upload').modal('hide');
		showsuccessmessage("上传成功, 页面即将自动刷新!", function (e) {
			$("#query #commit").click();
			parent.kklazy._show_loading();
		});
	} else {
		$("#loading").fadeOut(200, function() {
			$("#context").fadeIn(200, function() {
				$("#messagecontext").html(message);
				$("#upload #message").fadeIn(200, function() {
					$(":button,:submit").attr("disabled", null);
				});
			});
		});
	}
}
</script>
<title></title>
</head>
<body>
<div class="container">

	<div class="head">
		<div class="row">
			<div class="col-sm-6 col-xs-12">
				<div class="title">公积金账号<small>accumulation fund search</small></div>
			</div>
			<div class="col-sm-6 col-xs-12">
				<div class="right-icon-btn">
					<button class="icon-btn" id="search">
						<i class="icon-check"></i>
						<div>查询</div>
					</button>
					<button class="icon-btn" id="exceltemplate">
						<i class="icon-download"></i>
						<div>下载模板</div>
					</button>
					<button class="icon-btn" id="excelupload">
						<i class="icon-upload-alt"></i>
						<div>上传</div>
					</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include flush="true" page="../../common/default-nav-alert.jsp">
		<jsp:param value="${resource }" name="resource"/>
	</jsp:include>

	<form id="searchForm" action="" method="post">
		<div class="modal fade" id="query" tabindex="-1" role="dialog" aria-labelledby="queryLabel" aria-hidden="true">
			<div class="modal-dialog" >
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="queryLabel"><i class="icon-search"></i>查询<small>Search</small></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
								<label>工号 <small>empno</small>:</label>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type='text' name='empno' id='empno' value='${entity.empno }' autocomplete="off" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
								<label>身份证 <small>identity</small>:</label>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type='text' name='identity' id='identity' value='${entity.identity }' autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type='hidden' name='pageindex' id='pageindex' value='0' />
						<input type='hidden' name='pagesize'  id='pagesize'  value='10' />
						<button type="button" id="commit" class="btn btn-default" >Search</button>
						<button type="button" id="cancel" class="btn btn-default" >Reset</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<div class="data table-responsive">
		<table class="table table-hover ">
			<thead>
				<tr>
					<th>工号</th>
					<th>姓名</th>
					<th>身份证 [ 仅显示四位 ]</th>
					<th>事业部 / 部门</th>
					<th>公积金账号</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="s" items="${page.content }">
				<tr>
					<td>${s.staff.empno }</td>
					<td>${s.staff.name }</td>
					<td>${s.staff.idno }</td>
					<td>${s.staff.businessdepartment } / ${s.staff.department }</td>
					<td>${s.accumulationFundAccount }</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td id="pagebartd" colspan="1">
						<jsp:include flush="true" page="../../common/default-pagebar.jsp">
							<jsp:param value="${page }" name="page" />
							<jsp:param value="search" name="action" />
							<jsp:param value="searchForm" name="form" />
						</jsp:include>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>

</div>

<div id="foot" style="margin-bottom: 80px;"></div>
</body>
</html>