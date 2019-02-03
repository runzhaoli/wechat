<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>公积金帐号查询</title>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#commit").click(function() {
			if (!validation()) {
				setTimeout(function () {
					$(":input").removeAttr("readonly");
					$("#empno").attr("readonly", "readonly");
					$("#name").attr("readonly", "readonly");
					$(":button,:submit").attr("disabled", null);
					$('[data-toggle=popover]').popover('show');
				}, 500);
				return ;
			}
			$("#accumulationfundform").submit();
		});
	});
	</script>
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
							<h3>公积金帐号查询<br /><small>Provident Fund Account Search</small></h3>
						</div>
						<c:if test="${message != null }"><div class="alert alert-danger" role="alert">${message }</div></c:if>
						<form id="accumulationfundform" method="post" action="<%=MappingConstants.UrlMapping.ACCUMULATIONFUND_SEARCH %>" onsubmit="submit.disabled = 1;" >
							<div class="form-group">
								<label for="empno">工号 Empno</label>
								<input type="text" class="form-control" name="empno" id="empno" value="${user.empno }" readonly placeholder="Empno" maxlength="32" />
							</div>
							<div class="form-group">
								<label for="name">姓名 Name</label>
								<input type="text" class="form-control" name="name" id="name" value="${user.name }" readonly placeholder="Name" maxlength="32" />
							</div>
							<div class="form-group">
								<label for="identity">身份证号码 <span class="label label-danger">十八位</span> Identity</label>
								<input type="password" class="form-control" name="identity" id="identity" placeholder="Identity" validation='required' data-placement='top' maxlength="32" />
							</div>
							<button type="button" id="commit" class="btn btn-default">查询</button>
						</form>
					</div>
					<%@ include file="../common/copyright.jsp" %>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
</html>
