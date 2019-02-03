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
					url : '<%=path %>/staff/excelupload<%=version %>',
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
	$("#init").click(function() {
		$(":button,:submit").attr("disabled", "disabled");
		$('#initmodal').modal('show');
		$.ajax({
			type : 'POST',
			async : true,
			url : '<%=path %>/staff/init<%=version %>',
			dataType : "json",
			success : function(data) {
				if (data['result']) {
					$("#initmsg").html("初始化数据完成~");
					setTimeout(function () {
						$("#query #commit").click();
					}, 2000);
				} else {
					$("#initmsg").html(data['message']);
					setTimeout(function () {
						$(":button,:submit").attr("disabled", null);
					}, 500);
				}
			},
			error : function(data, status, e) {
				$("#initmsg").html("初始化出错[系统错误]:" + e);
				setTimeout(function () {
					$(":button,:submit").attr("disabled", null);
				}, 500);
			}
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
function operation(empno, type) {
	$(":button,:submit").attr("disabled", "disabled");
	$.ajax({
		type : 'POST',
		async : true,
		url : '<%=path %>/staff/operation<%=version %>',
		dataType : "json",
		data : { "empno" : empno, "type" : type },
		success : function(data) {
			if (data['result']) {
				showsuccessmessage("操作成功!", function (e) {
					$("#query #commit").click();
				});
			} else {
				showerrormessage("操作失败, " + data['message'], function (e) {
					$(":button,:submit").attr("disabled", null);
				});
			}
		},
		error : function(data, status, e) {
			showerrormessage("操作失败, " + e, function (e) {
				$(":button,:submit").attr("disabled", null);
			});
		}
	});
}
</script>
<title></title>
</head>
<body>
<div class="container">

	<div class="head">
		<div class="row">
			<div class="col-sm-6 col-xs-12">
				<div class="title">新致员工<small>newtouch staff search</small></div>
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
					<button class="icon-btn" id="init">
						<i class="icon-bolt"></i>
						<div>同步数据</div>
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
								<label>姓名 <small>name</small>:</label>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type='text' name='name' id='name' value='${entity.name }' autocomplete="off" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
								<label>部门 <small>department</small>:</label>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type='text' name='department' id='department' value='${entity.department }' autocomplete="off" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
								<label>状态 <small>status</small>:</label>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type='text' name='status' id='status' value='${entity.status }' autocomplete="off" />
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
					<th>邮箱</th>
					<th>进公司日期</th>
					<th>区域</th>
					<th>职级</th>
					<th title="0:离职状态,n:每次导入状态都会+1">状态 [ ${max } ] </th>
					<th>是否管理员</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="entity" items="${page.content }">
				<tr>
					<td>${entity.empno }</td>
					<td>${entity.name }</td>
					<td>${entity.idno }</td>
					<td>${entity.businessdepartment } / ${entity.department }</td>
					<td>${entity.email }</td>
					<td><fmt:formatDate value="${entity.indate }" pattern="yyyy-MM-dd"/></td>
					<td>${entity.area }</td>
					<td>${entity.level }</td>
					<td>${entity.status }<c:if test="${entity.status == '0' }"> [离职]</c:if></td>
					<c:if test="${ not empty entity.type and entity.type == '1' }">
						<td><i class="icon-star" style="color: red"></i></td>
						<td>
							<c:if test="${entity.status != '0' }">
							<button class="btn btn-warning btn-xs" title="取消管理员" onclick="javascript:operation('${entity.empno }', 'guest');"><i class="icon-arrow-right"></i></button>
							<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${entity.id}<%=version %>');"><i class="icon-trash"></i> 删除</button>
							</c:if>
						</td>
					</c:if>
					<c:if test="${ empty entity.type or entity.type == '0'}">
						<td></td>
						<td>
							<c:if test="${entity.status != '0' }">
							<button class="btn btn-primary btn-xs" title="设为管理员" onclick="javascript:operation('${entity.empno }', 'guest');"><i class="icon-arrow-left"></i></button>
							<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${entity.id}<%=version %>');"><i class="icon-trash"></i> 删除</button>
							</c:if>
						</td>
					</c:if>
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

	<div class="modal fade" id="initmodal" tabindex="-1" role="dialog" aria-labelledby="initLabel" aria-hidden="true">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="queryLabel"><i class="icon-upload-alt"></i>初始化员工状态<small>Refresh</small></h4>
				</div>
				<div class="modal-body">
					<div class="row" id="initmsg">
						<div class="col-xs-12">
							<label>正在初始化所有员工状态,请稍后...</label>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

</div>

<div id="foot" style="margin-bottom: 80px;"></div>
</body>
</html>