<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>查看历次举荐列表</title>
	<style type="text/css">
	.bs-callout { margin: 0 5px 20px; padding: 0 5px; border: 1px solid #eee; border-left: 5px solid #eee; }
	.bs-callout hr { margin-top: 0px; }
	.bs-callout .progress { margin-bottom: 10px; }
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
							<h3>历次举荐人员列表<br /><small>List</small></h3>
						</div>
						<div style="margin: 2px;"></div>
						<div class="form-group">
							<label for="empno">工号 Empno <span class="label label-danger">${user.empno }</span> / 姓名 Name <span class="label label-danger">${user.name }</span></label>
						</div>
					</div>
					<div style="margin: 0 0 10px 20px;"><span class="label label-success">被举荐人</span></div>
					<c:forEach var="s" items="${recommends }">
					<div class="bs-callout bs-callout-default fade in">
						<div class="panel-body">
							<label style="padding: 8px;">姓名：${s.name }</label>
							<hr />
							<label>举荐时间：</label><fmt:formatDate value="${s.create }" pattern="yyyy-MM-dd HH:mm:ss"/><br />
							<label>举荐状态：</label>
							<div class="progress">
								<div class="progress-bar <c:if test="${s.auditStatus == 0 }">progress-bar-danger</c:if><c:if test="${s.auditStatus != 0 }">progress-bar-info</c:if>" role="progressbar" aria-valuenow="${s.auditStatus }" aria-valuemin="0" aria-valuemax="4" style="min-width: 2em; width: ${s.progress }%;">
									<dic:text group="<%=ContextConstants.GROUP.RECOMMEND_AUDIT_STATUS %>" value="${s.auditStatus }"></dic:text>
								</div>
							</div>
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
