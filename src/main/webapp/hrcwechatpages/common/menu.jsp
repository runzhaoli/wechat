<%@page import="kklazy.common.constants.MappingConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="toolbar" class="toolbar" >
	<div class="ui-toolbar-wrap">
		<div class="ui-toolbar-left">
			<span class="bottom-menu ui-toolbar-button"><i class="icon-th-large"></i></span>
		</div>
		<div class="ui-toolbar-right">
			<span class="ui-toolbar-about" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.ABOUT_TEAM %>');">开发团队</span>
		</div>
	</div>
</div>
<div class="bottom-menu-content">
<div class="context">
	<div class="menu">
		<ul>
			<li onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.INDEX %>');" style="border-bottom: 1px solid #ddd;">
				<div class="left"><div class="img"><i class="icon-home"></i></div></div>
				<div class="center"><div class="text">首页</div></div>
			</li>
			<li onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.ACCUMULATIONFUND %>');" >
				<div class="left"><div class="img"><i class="icon-search"></i></div></div>
				<div class="center"><div class="text">公积金账号</div></div>
			</li>
			<li onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.ARTICLE_LIST %>?type=HELP');">
				<div class="left"><div class="img"><i class="icon-briefcase"></i></div></div>
				<div class="center"><div class="text">我要办事</div></div>
			</li>
			<li onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.CERTIFY_SELECT %>');">
				<div class="left"><div class="img"><i class="icon-file"></i></div></div>
				<div class="center"><div class="text">我要开证明</div></div>
			</li>
			<li onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.RECOMMEND_SELECT %>');">
				<div class="left"><div class="img"><i class="icon-envelope"></i></div></div>
				<div class="center"><div class="text">我要举荐</div></div>
			</li>
			<li onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.ARTICLE_LIST %>?type=POLICY');">
				<div class="left"><div class="img"><i class="icon-book"></i></div></div>
				<div class="center"><div class="text">人事政策</div></div>
			</li>
			<li onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.COMPLAINTPROPOSAL_SELECT %>');">
				<div class="left"><div class="img"><i class="icon-edit"></i></div></div>
				<div class="center"><div class="text">投诉与建议</div></div>
			</li>
		</ul>
	</div>
	<div class="nav">
		<span class="bottom-menu-close" style=" "><i class="icon-th-large"></i></span>
	</div>
	</div>
</div>
<div class="layer-mask"></div>

<script type="text/javascript">
$(document).ready(function() {
	setTimeout(function() {
		$("body").show();
	}, 100);
});
</script>
