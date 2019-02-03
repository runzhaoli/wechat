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

	<form id="modifyform" action="" method="post">
		<input type="hidden" name="parent.id" value="${parent.id }" />
		<input type="hidden" name="id" value="${entity.id }" />
		<div id="task">
			<div class='bs-callout bs-callout-info fade in'>
				<hr />
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
						<input type='text' name="iconInit" value="${entity.iconInit }" />
					</div>
					<div class="col-sm-2">
						<label><span class='required'>*</span>代码 code:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="code" value="${entity.code }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>资源名称 name:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="name" value="${entity.name }" validation='required' data-placement='right' />
					</div>
					<div class="col-sm-2">
						<label>类型 type:</label>
					</div>
					<div class="col-sm-4">
						<c:if test="${fn:containsIgnoreCase(resource.url, 'create') }">
						<dic:select group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${resource_type.value }" label="请选择" key="" readonly="readonly" disabled="disabled" ></dic:select>
						<input type="hidden" name="type" value="${resource_type.value }" data-placement='right' />
						</c:if>
						<c:if test="${fn:containsIgnoreCase(resource.url, 'modify') }">
						<dic:select group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${entity.type }" label="请选择" key="" readonly="readonly" disabled="disabled" ></dic:select>
						<input type="hidden" name="type" value="${entity.type }" data-placement='right' />
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>链接地址 url:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="url" value="${entity.url }" validation='required' data-placement='right' />
					</div>
					<div class="col-sm-2">
						<label>排序 sort:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="sort" value="${entity.sort }" />
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="foot" style="margin-bottom: 150px;"></div>
	
</div>
</body>
</html>