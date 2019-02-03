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
		<input type="hidden" name="id" value="${entity.id }" />
		<div id="task">
			<div class='bs-callout bs-callout-info fade in'>
				<hr />
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>键 key:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="key" value="${entity.key }" validation='required' data-placement='right' />
					</div>
					<div class="col-sm-2">
						<label><span class='required'>*</span>值 value:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="value" value="${entity.value }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>备注remark:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="remark" value="${entity.remark }" validation='required' data-placement='right' />
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="foot" style="margin-bottom: 150px;"></div>
	
</div>
</body>
</html>