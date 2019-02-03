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
					<label>分组 group:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.group }" readonly="readonly" disabled="disabled" />
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
					<label>显示名称 name:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.name }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>值 value:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.value }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>注释 desc:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.desc }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>排序 sort:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.sort }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
		</div>
	</div>

	<div id="foot" style="margin-bottom: 150px;"></div>

</div>
</body>
</html>