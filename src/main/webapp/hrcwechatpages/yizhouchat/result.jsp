<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>查看伊周聊预约记录</title>
	<style type="text/css">
	.bs-callout { margin: 0 5px 20px; padding: 0 5px; border: 1px solid #eee; border-left: 5px solid #eee; }
	.bs-callout hr { margin-top: 0px; }
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
							<h3>伊周聊预约记录<br /><small>List</small></h3>
						</div>
						<div style="margin: 2px;"></div>
						<div class="form-group">
							<label for="empno">工号 Empno <span class="label label-danger">${user.empno }</span> / 姓名 Name <span class="label label-danger">${user.name }</span></label>
						</div>
						<c:forEach var="s" items="${chats }">
						<div class="bs-callout fade in">
							<div class="panel-body">
								<label>预约时间：</label>
								<c:if test="${s.chattime == '1'}">周三 下午 3:00 — 4:00</c:if><c:if test="${s.chattime == '2'}">周四 下午 3:00 — 4:00</c:if>
								<br />
								<label>沟通形式：</label>
								<c:if test="${s.chattype == 1}">面对面交流</c:if><c:if test="${s.chattype == 2}">电话</c:if>
							</div>
						</div>
						</c:forEach>
					</div>

					<%@ include file="../common/copyright.jsp" %>
					
				</div>
			</div>
		</div>
	</div>
		
</div>
</body>
</html>
