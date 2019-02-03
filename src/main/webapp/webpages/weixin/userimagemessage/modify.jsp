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
<script type="text/javascript">
$(document).ready(function() {
	$("#activityid").change(function() {
		$("#itemid").empty(); 
		var activityid = $("#activityid").find("option:selected").val();
		$("#itemid").append("<option value=''>请选择所关联的活动项目</option>");
		if (activityid == null || activityid == '')
			return;
		$.ajax({
			type : 'POST',
			async : true,
			url : '<%=path %>/activityitem/activityitemdata<%=version %>',
			data : {'id' : activityid},
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					var body = data.body;
					$(body).each(function(index, object) {
						$("#itemid").append("<option value='" + object.id + "'>" + object.name + " / " + object.code + "</option>");
					});
				} else {
					showerrormessage("获取活动项目数据失败!", function (e) {
						popover();
					});
				}
			},
			error : function(data, status, e) {
				alert(e);
				popover();
			}
		});
	});
	
});
</script>
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
						<label><span class='required'>*</span>企业号 account:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="" value="${entity.account.name }" disabled="disabled" readonly="readonly"/>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>事业部 / 部门 :</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="" value="${staff.businessdepartment } / ${staff.department }" disabled="disabled" readonly="readonly"/>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>成员[工号] empno:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="" value="${entity.userID }" disabled="disabled" readonly="readonly"/>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>姓名 name:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="" value="${staff.name }" disabled="disabled" readonly="readonly"/>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>缩略图[点击查看图片] image:</label>
					</div>
<!-- 				</div> -->
<!-- 				<div class="row"> -->
					<div class="col-xs-6 col-md-3">
						<a href="${entity.localUrl }" target="view_window" class="thumbnail">
							<img src="${entity.localUrl }" alt="...">
						</a>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>选择关联的活动 activity:</label>
					</div>
					<div class="col-sm-4">
						<select id="activityid" name="activity.id" validation='required' data-placement='right'>
								<option value="">请选择所关联的活动</option>
<%-- 								<c:if test="${entity.activity.id == t.id }">selected=selected</c:if>  --%>
							<c:forEach var="t" items="${activities }">
								<option value="${t.id }" >${t.name } / ${t.code }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>选择关联的活动项目 activity item:</label>
					</div>
					<div class="col-sm-4">
						<select id="itemid" name="item.id" validation='required' data-placement='right'>
								<option value="">请选择所关联的活动项目</option>
						</select>
					</div>
				</div>
			</div>
		</div>
	</form>

	<div id="foot" style="margin-bottom: 150px;"></div>

</div>
</body>
</html>