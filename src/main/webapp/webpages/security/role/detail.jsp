<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<link type="text/css" href="<%=path %>/style/icheck/skins/all.css<%=version %>" rel="stylesheet">
<script type="text/javascript" src="<%=path %>/script/icheck/jquery.icheck.min.js<%=version %>"></script>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
<style type="text/css">
.branch .in li { padding-left: 40px; }
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

	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">基本信息</a></li>
		<li role="presentation"><a href="#employee" aria-controls="employee" role="tab" data-toggle="tab">关联的用户</a></li>
		<li role="presentation"><a href="#resources" aria-controls="resources" role="tab" data-toggle="tab">关联的资源</a></li>
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
					<label>图标 icon:</label>
				</div>
				<div class="col-sm-4">
					<div class='input-group'>
						<span class='input-group-addon'>
						<c:if test="${ not empty entity.icon }"><i class="${entity.icon }"></i></c:if>
						<c:if test="${ empty entity.icon }"><i class="glyphicon glyphicon-picture"></i></c:if>
						</span>
						<input type='text' name="iconInit" value="${entity.icon }" readonly="readonly" disabled="disabled" />
					</div>
				</div>
				<div class="col-sm-2">
					<label>角色代码 code:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' name="code" value="${entity.code }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>角色名称 name:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' name="name" value="${entity.name }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>备注 description:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' name="description" value="${entity.description }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>排序 sort:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' name="sort" value="${entity.sort }" readonly="readonly" disabled="disabled" />
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
						<li style="list-style: none;"><label>姓名 name / 用户登录名 username</label></li>
						<c:forEach var="e" items="${entity.employees }">
							<li><label style="font-variant: normal;">${e.name } / ${e.user.username }</label></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class='bs-callout bs-callout-default fade in tab-pane ' role="tabpanel" id="resources">
			<div class="row">
				<div class="col-sm-4">
					<label>关联的资源 resources:</label>
					<hr />
				</div>
				<div class="col-sm-8">
					<div class="tree table-responsive">
						<ul class="branch in">
							<li style="list-style: none;"><label>资源名称 name / 资源代码 code / 资源类型 type</label></li>
							<c:forEach var="r" items="${resources }">
							<li><input name="resourceid" id="${r.id }" value="${r.id }" type="checkbox" /><label for="${r.id }"><c:if test="${ not empty r.iconInit }"><i class="${r.iconInit }"></i></c:if><c:if test="${ empty r.iconInit }"><i class="icon-picture"></i></c:if>${r.name } / ${r.code } / <dic:text group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${r.type }"></dic:text></label>
								<ul class="branch in">
									<c:forEach var="m" items="${r.sublevel }">
									<c:if test="${m.enabled and !m.delete }">
									<li><input name="resourceid" id="${m.id }" value="${m.id }" type="checkbox" /><label for="${m.id }"><c:if test="${ not empty m.iconInit }"><i class="${m.iconInit }"></i></c:if><c:if test="${ empty m.iconInit }"><i class="icon-picture"></i></c:if>${m.name } / ${m.code } / <dic:text group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${m.type }"></dic:text></label>
										<ul class="branch in">
											<c:forEach var="s" items="${m.sublevel }">
											<c:if test="${s.enabled and !s.delete }">
											<li><input name="resourceid" id="${s.id }" value="${s.id }" type="checkbox" /><label for="${s.id }"><c:if test="${ not empty s.iconInit }"><i class="${s.iconInit }"></i></c:if><c:if test="${ empty s.iconInit }"><i class="icon-picture"></i></c:if>${s.name } / ${s.code } / <dic:text group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${s.type }"></dic:text></label>
											</li>
											</c:if>
											</c:forEach>
										</ul>
									</li>
									</c:if>
									</c:forEach>
								</ul>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="foot" style="margin-bottom: 150px;"></div>
	
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('input').iCheck({
		checkboxClass: 'icheckbox_minimal',
		radioClass: 'iradio_minimal',
		increaseArea: '20%' // optional
	});
	$('input').iCheck('disable');
	<c:forEach var="r" items="${entity.resources }">$('#${r.id }').iCheck('check');
	</c:forEach>
});
</script>
</body>
</html>