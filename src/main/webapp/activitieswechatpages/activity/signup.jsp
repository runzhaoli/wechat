<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN" manifest="">
<head>
<%@ include file="../common/path.jsp"%>
<title>嘉年华活动</title>
</head>
<body>
	<header class="weui-header">
		<h1 class="weui-title">报名</h1>
	</header>
	<div class="weui-cells__title">请选择你要报名的活动(四选一)</div>
	<div class="weui-cells weui-cells_radio">
		<label class="weui-cell weui-check__label" for="x11">
			<div class="weui-cell__bd">
				<p>羽毛球团队赛</p>
			</div>
			<div class="weui-cell__ft">
				<input type="radio" class="weui-check" name="radio1" id="x11">
				<span class="weui-icon-checked"></span>
			</div>
		</label>
		<label class="weui-cell weui-check__label" for="x12">
			<div class="weui-cell__bd">
				<p>乒乓球团队赛</p>
			</div>
			<div class="weui-cell__ft">
				<input type="radio" name="radio1" class="weui-check" id="x12">
				<span class="weui-icon-checked"></span>
			</div>
		</label>
		<label class="weui-cell weui-check__label" for="x13">
			<div class="weui-cell__bd">
				<p>3V3篮球对抗赛</p>
			</div>
			<div class="weui-cell__ft">
				<input type="radio" name="radio1" class="weui-check" id="x13">
				<span class="weui-icon-checked"></span>
			</div>
		</label>
		<label class="weui-cell weui-check__label" for="x14">
			<div class="weui-cell__bd">
				<p>王者荣耀争霸赛</p>
			</div>
			<div class="weui-cell__ft">
				<input type="radio" name="radio1" class="weui-check" id="x14">
				<span class="weui-icon-checked"></span>
			</div>
		</label>
	</div>
	<div class="weui-cells__title">请输入你所在的部门</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">所属部门</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="请输入">
			</div>
		</div>
	</div>
	<div class="weui-cells__title">请输入姓名+工号(例:张三 00000)<br/>根据所选项目,最多填写五人</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">一</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="请输入">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">二</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="请输入">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">三</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="请输入">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">四</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="请输入">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">五</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="请输入">
			</div>
		</div>
	</div>
	<div class="weui-cells__title">
		请填写你的手机号码<br/>如有多人请填写队长、副队长的
	</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">手机号(1)</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="tel" placeholder="请输入手机号">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">手机号(2)</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="tel" placeholder="请输入手机号">
			</div>
		</div>
	</div>
	<div class="weui-cells__title">
		请留下你的微信号<br/>如有多人请填写队长的
	</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">微信</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="请输入微信号">
			</div>
		</div>
	</div>
	<label for="weuiAgree" class="weui-agree">
		<input id="weuiAgree" type="checkbox" class="weui-agree__checkbox">
		<span class="weui-agree__text"> 阅读并同意<a href="javascript:void(0);">《相关条款》</a></span>
	</label>
	<div class="weui-btn-area">
		<a class="weui-btn weui-btn_primary" href="javascript:"
			id="showTooltips">确定</a>
	</div>
	<%@ include file="../common/copyright.jsp" %>
</body>
</html>