<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/jquery/ajaxfileupload.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/My97DatePicker/WdatePicker.js<%=version %>"></script>
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
		<input type="hidden" name="id" value="${entity.id }" />
		<div id="task">
			<div class='bs-callout bs-callout-info fade in'>
				<hr />
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>父菜单 parent:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="name" value="<c:if test="${ not empty parent.id }">${parent.name } [ <dic:text group="<%=ContextConstants.GROUP.WECHAT_MENU_TYPE %>" value="${parent.type }"></dic:text> ] / ${parent.key }</c:if>" disabled="disabled"/>
						<input type="hidden" name="parent.id" value="${parent.id }" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>关联的企业号ID account id:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="accountId" value="${entity.accountId }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>菜单名称 name:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="name" value="${entity.name }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>菜单类型 type:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<dic:select name="type" group="<%=ContextConstants.GROUP.WECHAT_MENU_TYPE %>" value="${entity.type }" validation='required' ></dic:select>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>菜单KEY key:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="key" value="${entity.key }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>菜单URL url:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="url" value="${entity.url }" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>关联的消息模板 template:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<select name="template.id">
								<option value="">请选择关联的消息模板(父菜单可不选择该项)</option>
							<c:forEach var="t" items="${templates }">
								<option value="${t.id }" <c:if test="${entity.template.id == t.id }">selected=selected</c:if> >${t.name } [ <dic:text group="<%=ContextConstants.GROUP.MESSAGE_TEMPLATE_TYPE %>" value="${t.type }"></dic:text> ]</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>排序 sort:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="sort" value="${entity.sort }" validation='required' data-placement='right' />
					</div>
				</div>
			</div>
		</div>
	</form>

	<div id="foot" style="margin-bottom: 80px;"></div>

</div>
</body>
</html>