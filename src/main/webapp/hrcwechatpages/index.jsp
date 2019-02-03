<%@ page language="java" import="kklazy.utils.*, kklazy.persistence.utils.*, kklazy.common.constants.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" buffer="512kb" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>
<% String uri  = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort(); %>
<% String version = "?v="+ request.getSession().getId(); %>
<!doctype html>
<html lang="zh-CN" manifest="">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">

	<title>人事小助手</title>

	<style type="text/css"> body { display: none; } </style>

	<link rel="icon" type="image/png" href="<%=path %>/images/favicon.png" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/style/kklazy/wechat/wechat-common.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/style/kklazy/wechat/wechat-menu.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/style/gmu/assets/widget/slider/slider.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/style/gmu/assets/widget/slider/slider.default.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/style/kklazy/wechat/wechat-index.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/style/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/style/font-awesome/font-awesome.min.css">

	<link rel="stylesheet" type="text/css" href="<%=path %>/style/kklazy/wechat/wechat-mobile.css" />

	<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/zepto.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/script/kklazy/wechat/wechat-menu.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/core/gmu.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/core/event.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/core/widget.js"></script>
</head>
<body>
<div class="app has-navbar-top has-navbar-bottom">

	<div class="navbar navbar-app navbar-absolute-top"></div>
	<div class="navbar navbar-app navbar-absolute-bottom"><%@ include file="common/menu.jsp" %></div>

	<div class="app-body">
		<div class="app-content">
			<div class="scrollable">
				<div class="scrollable-content">

					<div class="container">
						<div class="page-header">
							<h3>人事小助手<br /><small>Newtouch HRC</small></h3>
						</div>
					</div>
					<div id="slider">
						<c:forEach var="s" items="${sliderloop }">
						<div>
							<a href="${s.url }"><img src="${s.img }"></a>
							<p>${s.title }</p>
						</div>
						</c:forEach>
					</div>
					<div class="container">
						<c:if test="${message != null }"><div class="alert alert-danger" role="alert">${message }</div></c:if>
<%-- 						<c:if test="${v_status }"> --%>
							<div class="alert alert-info" role="alert" style="min-height: 65px; text-align: center;">
								<div style="font-size: 18px; margin-left: 10px; margin-bottom: 10px;">E-HR 系统工作流操作方法</div>
								<button type="button" class="btn btn-success" onclick="javascript:wechatmenu.go('workflowoperation');">查看详情</button>
<!-- 								<div style="font-size: 12px; margin-left: 10px; margin-bottom: 20px;">投票截止时间：2018-01-27 18:30</div> -->
								<button type="button" class="btn btn-success" onclick="javascript:wechatmenu.go('excellentemployeeselect');">开始投票</button>
							</div>
<%-- 						</c:if> --%>
						<div class="row">
							<div class="col-xs-4">
								<div class="thumbnail" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.ACCUMULATIONFUND %>');">
									<div class="caption"><img alt="公积金账号查询" src="<%=path %>/images/wechat/gjjzhcx.png<%=version %>"></div>
									<div class="caption"><p>公积金<br />账号查询</p></div>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="thumbnail" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.ARTICLE_LIST %>?type=HELP');">
									<div class="caption"><img alt="我要办事" src="<%=path%>/images/wechat/wybs.png<%=version %>"></div>
									<div class="caption"><p>我要办事</p></div>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="thumbnail" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.CERTIFY_SELECT %>');">
									<div class="caption"><img alt="我要开证明" src="<%=path %>/images/wechat/wykzm.png<%=version %>"></div>
									<div class="caption"><p>我要开证明</p></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4">
								<div class="thumbnail" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.RECOMMEND_SELECT %>');">
									<div class="caption"><img alt="我要举荐" src="<%=path %>/images/wechat/wyjj.png<%=version %>"></div>
									<div class="caption"><p>我要举荐</p></div>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="thumbnail" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.ARTICLE_LIST %>?type=POLICY');">
									<div class="caption"><img alt="人事政策" src="<%=path %>/images/wechat/rszc.png<%=version %>"></div>
									<div class="caption"><p>人事政策</p></div>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="thumbnail" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.COMPLAINTPROPOSAL_SELECT %>');">
									<div class="caption"><img alt="投诉与建议" src="<%=path %>/images/wechat/tsyjy.png<%=version %>"></div>
									<div class="caption"><p>投诉与建议</p></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4">
<%-- 								<div class="thumbnail" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.YZL_SEARCH %>');"> --%>
								<div class="thumbnail" onclick="javascript:wechatmenu.go('divisiontemp');">
									<div class="caption"><img alt="薪资、考勤 负责人" src="<%=path %>/images/wechat/yzl.png<%=version %>"></div>
									<div class="caption"><p>薪资、考勤<br />负责人</p></div>
								</div>
							</div>
							<div class="col-xs-4">
<!-- 								<div class="thumbnail" onclick="javascript:wechatmenu.go('articlelist?name=POLICY');"> -->
								<div class="thumbnail" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.ARTICLE_LIST %>?type=QUESTION');">
									<div class="caption"><img alt="人事问答" src="<%=path %>/images/wechat/rswd.png<%=version %>"></div>
									<div class="caption"><p>人事问答</p></div>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="thumbnail" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.DIVISION_RESULT %>');">
<!-- 								<div class="thumbnail" onclick="javascript:alert('...');"> -->
									<div class="caption"><img alt="人事分工" src="<%=path %>/images/wechat/rsfg.png<%=version %>"></div>
									<div class="caption"><p>人事分工</p></div>
								</div>
							</div>
						</div>
					</div>
					<%@ include file="common/copyright.jsp" %>
				</div>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/extend/touch.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/extend/matchMedia.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/extend/event.ortchange.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/extend/parseTpl.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/extend/fix.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/widget/slider/slider.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/widget/slider/arrow.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/widget/slider/dots.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/widget/slider/$touch.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/gmu/widget/slider/$autoplay.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	setTimeout(function() {
		$('#slider').slider({loop:true, arrow:false});
	}, 200);
});
</script>

</body>
</html>