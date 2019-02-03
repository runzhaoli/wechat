<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<%--  --%>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>2017年优秀员工评选-我的投票</title>
	<style type="text/css">
		.panel-heading a { padding: 10px 20px; font-size: 14px; font-family: Microsoft Yahei;}
		.panel-heading a:hover, .panel-heading a:focus { text-decoration: none; }
		.panel-group { padding: 0px 5px; }
		.panel-group .panel { border-radius: 0px; }
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
							<h3>我的投票<br /><small>...</small></h3>
						</div>
						<div class="alert alert-info" role="alert">
							如果对您的投票有疑问，请发邮件给xianghui.fu@newtouch.cn
						</div>
						<div class="form-group">
							<label for="empno">工号 Empno <span class="label label-danger">${user.empno }</span> / 姓名 Name <span class="label label-danger">${user.name }</span></label>
						</div>
						<div class="table-responsive" style="text-align: center;">
							<table class="table table-striped">
								<thead>
									<tr>
<!-- 										<td><strong>类型</strong></td> -->
										<td colspan="2"><strong>投票给[ 编号 / 节目名称 ]</strong></td>
										<td><strong>投票日期</strong></td>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${results }" var="s" varStatus="index">
									<tr>
<%-- 										<td><c:if test="${s.type == 'excellentemployee'}">个人</c:if><c:if test="${s.type == 'excellentgroup'}">团队</c:if></td> --%>
										<td>${s.empno }</td>
										<td>${s.name }</td>
										<td>${s.vdate }</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

					<%@ include file="../common/copyright.jsp" %>

				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>