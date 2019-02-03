<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>历次投诉与建议列表</title>
	<style type="text/css">
	.bs-callout { margin: 0 5px 20px; padding: 0 5px; border: 1px solid #eee; border-left: 5px solid #eee; }
	.bs-callout hr { margin: 8px 0; }
	.bs-callout .progress { margin-bottom: 10px; }
	.bs-callout .table { width: auto; }
	.bs-callout .table tr td, .bs-callout .table tr th { color: #777; border: 0px; text-align: left; font-size: 13px; font-variant: small-caps; border-bottom: 1px solid #fff; }
	.bs-callout-warning { background-color: #faf8f0; border-left-color: #faebcc; }
	.bs-callout-warning h4 { color: #8a6d3b; }
	.bs-callout-danger { background-color: #fdf7f7; border-left-color: #eed3d7; }
	.bs-callout-danger h4 { color: #b94a48; }
	</style>
</head>
<body>
<div class="app has-navbar-top has-navbar-bottom">

	<div class="navbar navbar-app navbar-absolute-top"></div>
	<div class="navbar navbar-app navbar-absolute-bottom"><%@ include file="../common/menu.jsp" %></div>

	<div class="app-body">
		<div class="app-content">
			<div class="scrollable">
				<div class="scrollable-content">
					<div class="container">
						<div class="page-header">
							<h3>历次投诉与建议列表<br /><small>List</small></h3>
						</div>
						<div style="margin: 2px;"></div>
						<div class="alert alert-danger" role="alert">请注意：匿名提交的投诉与建议无法查看!</div>
						<div class="form-group">
							<label for="empno">工号 Empno <span class="label label-danger">${user.empno }</span> / 姓名 Name <span class="label label-danger">${user.name }</span></label>
						</div>
					</div>
					<c:forEach var="s" items="${complaintproposals }">
					<div class="bs-callout bs-callout-warning fade in">
						<div class="panel-body">
							投诉与建议类型：<dic:text group="<%=ContextConstants.GROUP.COMPLAINT_PROPOSAL_TYPE %>" value="${s.type }" showcode="false"></dic:text>
							<hr />
							投诉内容：${s.description }
							<c:if test="${reply != null and reply != '' }">
							<hr /><strong>回复内容：${s.reply }</strong>
							</c:if>
						</div>
					</div>
					</c:forEach>
					<%@ include file="../common/copyright.jsp" %>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
</html>
