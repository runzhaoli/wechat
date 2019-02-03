<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dic" uri="/dictionary-select-tag" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<script type="text/javascript" src="<%=path %>/script/My97DatePicker/WdatePicker.js<%=version %>"></script>
<link type="text/css" href="<%=path %>/style/icheck/skins/all.css<%=version %>" rel="stylesheet">
<script type="text/javascript" src="<%=path %>/script/icheck/jquery.icheck.min.js<%=version %>"></script>
<script type="text/javascript">
$(document).ready(function() {
$("#commit").click(function() {

	if (!validation()) {
		setTimeout(function () {
			$(":input").removeAttr("readonly");
			$(":button,:submit").attr("disabled", null);
			$('[data-toggle=popover]').popover('show');
			
		}, 500);
		
		return ;
	}
	
	$("#employeeform").ajaxSubmit({
		type : 'POST',
		async : true,
		url : "<%=path %>/employee/authorizecommitemployee?v=" + Math.random(),
		dataType : "json",
		data : {
			"t" : "<%=DateUtils.currentDate().getTime() %>",
			"s" : "<%=request.getRequestedSessionId() %>",
			"l" : "<%=request.getHeader("Accept-Language") %>",
			"m" : "<%=MD5Utils.md5Hex(url) %>",
		},
		success : function(data) {

			var status = data['status'];
			var code   = data['code'];
			var msg    = data['msg'];
			console.log("status:" + status + "; msg:" + msg);

			if (status == 'success') {
				window.location.href = '<%=path %>/employee/searchemployee<%=version %>';
			}

			if (status = 'error') {
				$("#" + code).warning(msg);
				
				setTimeout(function () {
					$(":input").removeAttr("readonly");
					$(":button,:submit").attr("disabled", null);
					$('[data-toggle=popover]').popover('show');
					
				}, 500);
			}
		},
		error : function(data, status, e) {
			alert(e);
		}
	});
	
});
$("#cancel").click(function() {
	history.back();
});
});
</script>
<title></title>
</head>
<body>
<div class="container">

	<div style="margin-top: 20px;">
		<h2><span class="glyphicon glyphicon-pencil" style="font-size: 18px; margin-right: 10px;"></span>Change Password</h2>
	</div>

	<div style="margin: 40px 0; background: #fff;">
		<ol class="breadcrumb" style="border-radius: 0px; background: #fff;">
			<li><i class="icon-home"></i><a href="console!index">Home</a></li>
			<li><a href="javascript:void(0);">Profile</a></li>
			<li class="active">Change Password</li>
		</ol>
	</div>
	
	<div class="right-icon-btn">
		<button class="icon-btn span2" id="commit">
			<span class="glyphicon glyphicon-check"></span>
			<div>Save</div>
		</button>
		<button class="icon-btn span2" id="cancel">
			<span class="glyphicon glyphicon-remove-circle"></span>
			<div>Back</div>
		</button>
	</div>

	<form id="employeeform" method="post" >
		<input type="hidden" id="method" name="method" value="${method }" />
		<input type="hidden" id="eid" name="employee.id" value="${employee.id }" />
		<div id="role">
			<div class='bs-callout bs-callout-info fade in'>
<!-- 				<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button> -->
				<label><i class='icon-edit'></i>Change Password, Write Date: <%=DateUtils.currentString()  %></label>
				<hr />
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						更新用户 Employee:
					</div>
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<div class='input-group'>
							<span class='input-group-addon'><i class='icon-th-large'></i></span>
							<input type='text' value="${employee.username } / ${employee.um } / ${employee.organization.name } / ${employee.organization.code }" disabled="disabled" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="tree">
			<div class='bs-callout bs-callout-info fade in'>
<!-- 				<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button> -->
				<label><i class='icon-check'></i>Set Password</label>
				<hr />
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						设置新密码 password:
					</div>
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<div class="tree">
							<ul class="branch in" style="padding-left: 10px; -webkit-padding-start: 10px;">
								<li><input id="all" type="checkbox" /><label for="all">所有角色 All Role</label>
									<ul class="branch in">
										<c:forEach var="r" items="${roles }">
										<li>
											<input name="role" id="${r.id }" value="${r.id }" type="checkbox" /><label for="${r.id }"><c:if test="${r.icon != null }"><i class="${r.icon }"></i></c:if><c:if test="${r.icon == null }"><i class="icon-picture"></i></c:if>${r.name } / ${r.code }</label>
										</li>
										</c:forEach>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div id="foot" style="margin-bottom: 150px;"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('input').iCheck({
		checkboxClass: 'icheckbox_minimal',
		radioClass: 'iradio_minimal',
		increaseArea: '20%' // optional
	});
	<c:forEach var="r" items="${employee.roles }">$('#' + ${r.id }).iCheck('check');</c:forEach>
});
$('#all').on('ifChecked', function(event) {
	$('input').iCheck('check');
});
$('#all').on('ifUnchecked', function(event) {
	$('input').iCheck('uncheck');
});
</script>
</body>
</html>