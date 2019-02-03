<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="common/path.jsp"%>
<title>工会活动</title>
</head>
<body>
	<header class="weui-header">
		<h1 class="weui-title">登录<br /><small style="font-size: 60%;">sign in</small></h1>
	</header>
	<div class="weui-content-padded">
		<form id="login" method="post" onsubmit="submit.disabled = 1;">
			<div class="weui-cells weui-cells_form">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">工号</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" id="empno" name="empno" maxlength="8" placeholder="请输入工号">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">身份证</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" id="identity" name="identity" maxlength="8" placeholder="请输入身份证后四位">
					</div>
				</div>
			</div>
			<div class="weui-cells__tips">请输入身份证号码后四位</div>
			<div class="weui-btn-area">
				<a class="weui-btn weui-btn_primary" href="javascript:$('#login').submit();" id="showTooltips">确定</a>
			</div>
		</form>
	</div>
	<div class="weui-footer weui-footer_fixed-bottom">
		<p class="weui-footer__links">
			<a href="javascript:void(0);" class="weui-footer__link">新致软件</a>
		</p>
		<p class="weui-footer__text">Copyright © 2016-2026 newtouch.cn</p>
	</div>
	<c:if test="${message != null }">
		<script type="text/javascript">
			$(document).ready(function() {
				$.toptip('${message }', 'warning');
			});
		</script>
	</c:if>
</body>
</html>
