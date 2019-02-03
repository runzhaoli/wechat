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
		<li role="presentation"><a href="#role" aria-controls="role" role="tab" data-toggle="tab">关联的角色</a></li>
	</ul>
	<div class="tab-content">
		<div class='bs-callout bs-callout-default fade in tab-pane active' role="tabpanel" id="detail">
			<div class="row">
				<div class="col-sm-4">
					<label>基本信息 detail:</label>
					<hr />
				</div>
			</div>
			<c:if test="${not empty parent.parent.id }">
			<div class="row">
				<div class="col-sm-2">
					<label>上级资源 parent:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${parent.parent.name } / ${parent.parent.code }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			</c:if>
			<c:if test="${not empty parent.id }">
			<div class="row">
				<div class="col-sm-2">
					<label>上级资源 parent:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${parent.name } / ${parent.code }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			</c:if>
			<div class="row">
				<div class="col-sm-2">
					<label>资源图标 icon:</label>
				</div>
				<div class="col-sm-4">
					<div class='input-group'>
						<span class='input-group-addon'>
						<c:if test="${ not empty entity.iconInit }"><i class="${entity.iconInit }"></i></c:if>
						<c:if test="${ empty entity.iconInit }"><i class="glyphicon glyphicon-picture"></i></c:if>
						</span>
						<input type='text' value="${entity.iconInit }" readonly="readonly" disabled="disabled" />
					</div>
				</div>
				<div class="col-sm-2">
					<label>代码 code:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.code }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>资源名称 name:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.name }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>类型 type:</label>
				</div>
				<div class="col-sm-4">
					<dic:select group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${entity.type }" label="请选择" key="" readonly="readonly" disabled="disabled" ></dic:select>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>链接地址 url:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.url }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>排序 sort:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.sort }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
		</div>
		<div class='bs-callout bs-callout-default fade in tab-pane' role="tabpanel" id="role">
			<div class="row">
				<div class="col-sm-4">
					<label>关联的角色 role:</label>
					<hr />
				</div>
				<div class="col-sm-8">
					<ul class="branch in">
						<li style="list-style: none;"><label>角色名称 name / 角色代码 code</label></li>
						<c:forEach var="r" items="${entity.roles }">
							<li><label>${r.name } / ${r.code }</label>
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