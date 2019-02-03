<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<%-- <html lang="zh-CN"> --%>
<html lang="zh-CN">
<head>
	<%@ include file="common/path.jsp" %>
	<title>人事小助手</title>
</head>
<body>
<div class="app has-navbar-top has-navbar-bottom">

	<div class="navbar navbar-app navbar-absolute-top"></div>
	<div class="navbar navbar-app navbar-absolute-bottom"><%@ include file="common/menu.jsp" %></div>

	<div class="app-body">
		<div class="app-content">
			<div class="scrollable">
				<div class="scrollable-content">
					<div class="container">
						<div class="page-header">
							<h3>登录<br /><small>Sign in</small></h3>
						</div>
						<c:if test="${message != null }"><div class="alert alert-danger" role="alert">${message }</div></c:if>
<!-- 						<div class="alert alert-danger" role="alert">请通过企业号 [新致软件] 进入 '人事小助手' 噢~</div> -->
						<form id="login" method="post" onsubmit="submit.disabled = 1;" >
							<div class="form-group">
								<label for="empno">工号 Empno</label>
								<input type="text" class="form-control" name="empno" id="empno" placeholder="Empno" maxlength="32" />
							</div>
							<div class="form-group">
								<label for="identity">身份证号码 <span class="label label-danger">后四位</span> Identity</label>
								<input type="password" class="form-control" name="identity" id="identity" placeholder="Identity" maxlength="32" />
							</div>
							<button type="button" onclick="javascript:$('#login').submit();" class="btn btn-default">登录</button>
						</form>
					</div>
					<%@ include file="common/copyright.jsp" %>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
</html>
