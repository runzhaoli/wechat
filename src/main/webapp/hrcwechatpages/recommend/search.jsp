<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>我要举荐</title>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#commit").click(function() {
			if (!validation()) {
				setTimeout(function () {
					$(":input").removeAttr("readonly");
					$("#empno").attr("readonly", "readonly");
					$(":button,:submit").attr("disabled", null);
					$('[data-toggle=popover]').popover('show');
				}, 500);
				return ;
			}
			$("#recommendform").submit();
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
							<h3>填写举荐信息<br /><small>Recommend Info</small></h3>
						</div>
						<c:if test="${message != null }"><div class="alert alert-danger" role="alert">${message }</div></c:if>
						<form id="recommendform" method="post" action="<%=MappingConstants.UrlMapping.RECOMMEND_SAVE %>" onsubmit="submit.disabled = 1;" >
							<span class="label label-default">我的信息</span><div style="margin-bottom: 10px;"></div>
							<div class="form-group">
								<label for="empno">工号 Empno</label>
								<input type="text" class="form-control" name="empno" id="empno" value="${user.empno }" readonly placeholder="Empno" maxlength="32" />
							</div>
							<span class="label label-success">请填写被举荐人的信息</span><div style="margin-bottom: 10px;"></div>
							<div class="form-group">
								<label for="name">姓名 Name</label>
								<input type="text" class="form-control" name="name" id="name" placeholder="Name" validation='required' data-placement='top' maxlength="32" />
							</div>
							<div class="form-group">
								<label for="position">职位 Position</label>
								<input type="text" class="form-control" name="position" id="position" placeholder="Position" validation='required' data-placement='top' maxlength="32" />
							</div>
							<div class="form-group">
								<label for="workingYears">工作年限 Working Years</label>
								<input type="text" class="form-control" name="workingYears" id="workingYears" placeholder="Working Years" validation='required' data-placement='top' maxlength="32" />
							</div>
							<div class="form-group">
								<label for="mobile">联系电话 Mobile</label>
								<input type="tel" class="form-control" name="mobile" id="mobile" placeholder="Mobile" validation='required' data-placement='top' maxlength="32" />
							</div>
							<div class="form-group">
								<label for="email">邮箱地址 Email</label>
								<input type="email" class="form-control" name="email" id="email" placeholder="Email" validation='required' data-placement='top' maxlength="32" />
							</div>
							<div class="form-group">
								<label for="description">描述 Description</label>
								<textarea rows="5" class="form-control" name="description" id="description" placeholder="Description" validation='required' data-placement='top' maxlength="128" ></textarea>
							</div>
							
							<button type="button" id="commit" class="btn btn-default">提交</button>
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
