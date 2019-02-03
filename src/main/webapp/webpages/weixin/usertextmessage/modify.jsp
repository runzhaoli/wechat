<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/My97DatePicker/WdatePicker.js<%=version %>"></script>
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
						<label><span class='required'>*</span>活动代码 code:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="code" value="${entity.code }" validation='required' data-placement='right' />
					</div>
					<div class="col-sm-2">
						<label><span class='required'>*</span>活动名称 name:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="name" value="${entity.name }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>负责人 principal:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="principal" value="${entity.principal }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>开始时间 start:</label>
					</div>
					<div class="col-sm-4">
						<div class="input-group" id="selectstart">
							<span class="input-group-addon">选择开始时间...</span>
							<input type='text' id="start" name="start" value="${entity.start }" data-placement='right' onclick="javascript:WdatePicker({onpicked:function(){end.value='';},doubleCalendar:true,isShowClear:false,readOnly:true});" readonly='readonly' />
						</div>
					</div>
					<div class="col-sm-2">
						<label><span class='required'>*</span>结束时间 end:</label>
					</div>
					<div class="col-sm-4">
						<div class="input-group" id="selectend">
							<span class="input-group-addon">选择结束时间...</span>
							<input type='text' id="end" name="end" value="${entity.end }" data-placement='right' onclick="javascript:WdatePicker({minDate:'#F{$dp.$D(\'start\')}',doubleCalendar:true,isShowClear:false,readOnly:true});" readonly='readonly' />
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<div id="foot" style="margin-bottom: 150px;"></div>

</div>
</body>
</html>