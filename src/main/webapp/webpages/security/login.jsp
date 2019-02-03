<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../common/default-path.jsp" %>
<link type="text/css" rel="stylesheet" href="<%=path %>/style/kklazy/login/login.css<%=version %>" />
<link type="text/css" rel="stylesheet" href="<%=path %>/style/icheck/skins/all.css<%=version %>">
<script type="text/javascript" src="<%=path %>/script/icheck/jquery.icheck.min.js<%=version %>"></script>
<script type="text/javascript">
if (window.self != window.top) {
	top.window.location = self.window.location;
}
$(document).ready(function() {
	$.verify('verifycode', '<%=path %>/kaptcha/verify');
	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if(ev.keyCode==13) {
			$("#signin").focus();
			$("#signin").click();
			$(":button, :submit").attr("disabled", "disabled");
		}
	};
	$("#remove").click(function() {
		$(".error").fadeOut(500);
	});
	$("#signin").click(function() {
		$(".error").fadeOut(100);
		$(".body").fadeOut(100, function() {
			$(".loading").fadeIn(500, function() {
				$("#loginForm").submit();
			});
		});
	});
	$('#_spring_security_remember_me').iCheck({
		checkboxClass: 'icheckbox_minimal',
		radioClass: 'iradio_minimal',
		increaseArea: '20%' // optional
	});
});
</script>
<title><%=SystemConfigUtils.getSystemConfig("system.title") %></title>
</head>
<body style="background-color: #fff;">

<div class="login">
	<div class="head">
		<div class="title">登录 <small>SIGN IN</small></div>
		<div class="help"><button type="button" ><i class="icon-exclamation-sign"></i></button></div>
	</div>
	<c:if test='${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message != null }'>
	<div class="error">
		<div class="context" style=" ">
			<div class="message" style=" width: 100%; line-height: 30px; ">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }</div>
			<div class="remove" style=""><button id="remove" type="button" ><i class="icon-remove"></i></button></div>
		</div>
	</div>
	</c:if>
	<div class="body">
		<form name="loginForm" id="loginForm" action="<%=path %>/j_spring_security_check" method="post" onsubmit="submit.disabled = 1;">
			<div class="ipt">
				<i class="icon-user" style="padding: 16px 4px 16px 13px;"></i><input style="border: 0px;" type="text" name="j_username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" id="username" placeholder="username" validation="required " data-placement="top" maxlength="80" tabindex="1" autocomplete="off" />
			</div>
			<div class="ipt">
				<i class="icon-lock" style="padding: 16px 6px 16px 13px;"></i><input type="password" name="j_password" value="" id="password" placeholder="password" validation="required" data-placement="top" maxlength="80" tabindex="2" autocomplete="off" />
			</div>
			<div class="ipt verify" >
				<i class="icon-camera" style="padding: 18px 2px 16px 13px;"></i><input style="border: 0px; /* border-right: 1px solid #ddd; */" type="text" name="j_verify" value="" id="verify" placeholder="code" validation="required" data-placement="top" maxlength="4" tabindex="3" autocomplete="off" /><img src="" id="verifycode" /><div class="refresh"><button type="button" title="change.." onclick="javascript:$.verify('verifycode', '<%=path %>/kaptcha/verify');"><i class="icon-refresh"></i></button></div>
			</div>
			<div class="ipt remeber">
				<div id="checkbox"><input id="_spring_security_remember_me" name="_spring_security_remember_me" tabindex="4" type="checkbox" /></div>
				<div><label for="_spring_security_remember_me" style="font-weight: normal;">记住我 <small>REMEMBER ME</small></label></div>
			</div>
			<div class="ipt sign" style="border: 0px;">
				<button type="button" id="signin" tabindex="4" >登录 SIGN IN</button>
			</div>
		</form>
	</div>
	<div class="loading none">
		<div class="spinner"></div>
		<div class="text">......</div>
	</div>
	<div class="foot">

	</div>
</div>

</body>
</html>
