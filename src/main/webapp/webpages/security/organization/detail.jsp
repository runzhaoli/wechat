<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
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
	
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">基本信息</a></li>
		<li role="presentation"><a href="#employee" aria-controls="employee" role="tab" data-toggle="tab">关联的用户</a></li>
	</ul>
	<div class="tab-content">
		<div class='bs-callout bs-callout-default fade in tab-pane active' role="tabpanel" id="detail">
			<div class="row">
				<div class="col-sm-4">
					<label>基本信息 detail:</label>
					<hr />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>代码 code:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.code }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>机构名称 name:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.name }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>类型 type:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.type }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>机构级别 level:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.level }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>机构状态 status:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.status }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>排序 sort:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.sort }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
		</div>
		<div class='bs-callout bs-callout-default fade in tab-pane ' role="tabpanel" id="employee">
			<div class="row">
				<div class="col-sm-4">
					<label>关联的用户 employee:</label>
					<hr />
				</div>
				<div class="col-sm-8">
					<ul class="branch in">
						<li style="list-style: none;"><label>用户姓名 name / 用户登录代码 code</label></li>
						<c:forEach var="e" items="${entity.employees }">
							<li><label style="font-variant: normal;">${e.name } / ${e.user.username }</label>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div id="foot" style="margin-bottom: 150px;"></div>
	
</div>
</body>
</html>