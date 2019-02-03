<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<script type="text/javascript" src="<%=path %>/script/My97DatePicker/WdatePicker.js<%=version %>"></script>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
<style type="text/css">
.input-group:hover, .input-group input:hover { cursor: pointer; }
</style>
<title></title>
</head>
<body>
<div class="container">

	<div class="head">
		<div class="row">
			<%@ include file="../../common/default-pagetitle.jsp" %>
			<div class="col-sm-6 col-xs-12">
				<div class="right-icon-btn">
				<%@ include file="../../common/default-operation-button.jsp" %>
				</div>
			</div>
		</div>
	</div>
	<jsp:include flush="true" page="../../common/default-nav-alert.jsp">
		<jsp:param value="${resource }" name="resource"/>
	</jsp:include>

	<form id="modifyform" action="" method="post">
		<input type="hidden" id="eid" name="id" value="${entity.id }" />
		<div id="task">
			<div class='bs-callout bs-callout-info fade in'>
				<hr />
				<div class="row">
					<div class="col-sm-2">
						<label>登录账号 username:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' value="${entity.username }" readonly="readonly" disabled="disabled" />
						<input type="hidden" name="username" value="${entity.username }" />
					</div>
					<div class="col-sm-2">
						<label>姓名 name:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' value="${entity.employee.name }" readonly="readonly" disabled="disabled" />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>新登录密码 password:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="password" validation='required' data-placement='right' />
					</div>
				</div>
			</div>
		
		</div>
	</form>

	<div id="foot" style="margin-bottom: 150px;"></div>
</div>
</body>
</html>