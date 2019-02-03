<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>公积金帐号查询</title>
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
							<h3>公积金帐号查询结果<br /><small>Provident Fund Account Query Result</small></h3>
						</div>
						<div class="form-group">
							<label for="empno">工号 Empno</label>
							<input type="text" class="form-control" name="empno" id="empno" value="${user.empno }" readonly placeholder="Empno" maxlength="32" />
						</div>
						<div class="form-group">
							<label for="name">姓名 Name</label>
							<input type="text" class="form-control" name="name" id="name" value="${user.name }" readonly placeholder="Name" maxlength="32" />
						</div>
						<div class="form-group">
							<label for="identity">身份证号码 Identity</label>
							<input type="text" class="form-control" name="identity" id="identity" value="${user.idno }" readonly placeholder="Identity" maxlength="32" />
						</div>
						<div class="form-group">
							<label for="name">公积金账号 Provident Fund Account</label>
							<input type="text" class="form-control" name="accumulationFundAccount" id="accumulationFundAccount" value="${fund.accumulationFundAccount }" readonly placeholder="Account" maxlength="64" />
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
