<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN" manifest="">
<head>
<%@ include file="../common/path.jsp"%>
<title>二十三周年庆活动</title>
<style type="text/css">
.weui-icon_gallery-cancel {
	color: #fff;
	font-size: 34px;
}
</style>
<script type="text/javascript">
function _setting_pic(url) {
	$('.weui-gallery__img').css({'background-image' : 'url(' + url + ')'});
	$('.weui-gallery').show();
}
function _vote(num) {
	$.showLoading();
	$('#picid').val(num);
	$("#votephotoform").submit();
// 	setTimeout(function() {$.hideLoading();}, 3000)
}
</script>
<style type="text/css">
.weui-panel { background: rgba(255, 255, 255, 0); }
.weui-cells { background: rgba(255, 255, 255, 0); }
</style>
</head>
<body style="background-image: url(../images/100pic23/background.jpg); background-size: cover; background-repeat: no-repeat; background-attachment: fixed;">
	<header class="weui-header">
		<div style="position: absolute; left: 15px; top: 50px; "><img alt="" src="../images/100pic23/l.png" /></div>
		<h1 class="weui-title">最美照片评选</h1>
		<div style="position: absolute; right: 15px; top: 40px; "><img alt="" src="../images/100pic23/r.png" /></div>
	</header>
	<div class="weui-panel">
		<div class="weui-panel__hd">照片列表(点击图片查看大图)<br/>每人最多可投五票~<c:if test="${overtime }"><br /><span style="color: red;">投票已结束~感谢您的参与~</span></c:if></div>
		<div class="weui-panel__bd">
			<div class="weui-media-box weui-media-box_small-appmsg">
				<div class="weui-cells">
					<c:forEach var="x" items="${votes }">
					<div class="weui-cell">
						<div class="weui-cell__hd">
							<img src="<%=path %>/images/100pic23/${x.picid}.jpg"
								alt="" style="width: 46px; height: 46px; margin-right: 15px; display: block" onclick="javascript:_setting_pic($(this).attr('src'));">
						</div>
						<div class="weui-cell__bd weui-cell_primary">
							<div class="weui-cell" style="font-size: small; padding: 10px;">
								<div class="weui-cell__bd">序号 : ${x.picid}</div>
								<div class="weui-cell__ft"><c:if test="${total > 0 }">投票数 : ${x.total }</c:if></div>
							</div>
							<div class="weui-progress">
								<div class="weui-progress__bar">
									<c:if test="${total > 0 }"><div class="weui-progress__inner-bar js_progress" style="width: ${x.total / 5}%;"></div></c:if>
								</div>
							</div>
						</div>
						<div class="weui-cell__ft">
							<c:if test="${x.empno != user.empno }">
							<c:if test="${total < 5 and !overtime }"><button class="weui-vcode-btn" onclick="javascript: _vote('${x.picid}')">投票</button></c:if>
							<c:if test="${total >= 5 or overtime }"><i class="weui-icon-cancel" style="padding: 0 .6em 0 .7em;"></i></c:if>
							</c:if>
							<c:if test="${x.empno == user.empno }"><i class="weui-icon-success-no-circle" style="padding: 0 .6em 0 .7em;"></i></c:if>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<form id="votephotoform" action="votephoto" method="post">
		<input type="hidden" id="picid" name="picid" value="" />
	</form>
	<div class="weui-gallery" style="display: none;">
		<span class="weui-gallery__img" style="background-image: url(xxx);"></span>
		<div class="weui-gallery__opr">
			<a href="javascript:$('.weui-gallery').hide();" class="weui-gallery__del"> <i
				class="weui-icon-cancel weui-icon_gallery-cancel"></i>
			</a>
		</div>
	</div>
	<%@ include file="../common/copyright.jsp" %>
	<c:if test="${message != null }">
		<script type="text/javascript">
			$(document).ready(function() {
				$.toptip('${message }', 'warning');
			});
		</script>
	</c:if>
</body>
</html>