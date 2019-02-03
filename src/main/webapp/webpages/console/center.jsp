<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../common/default-path.jsp" %>
<script type="text/javascript" src='<%=path %>/script/kklazy/kklazy-framework.js<%=version %>'></script>
<script type="text/javascript" >
if (window.self != window.top) {
	top.window.location = self.window.location;
}
$(document).ready(function() {
	kklazy._loading_resources('<%=path %>', '/template/framework', '<%=version %>');
	framework._jump('center', '<%=path %>/console/index<%=version %>');
	$(".left-icon-btn.on").click();
	$("#lock").click();
});
</script>
</head>
<title><%=SystemConfigUtils.getSystemConfig("system.title") %></title>
<body>
	<div style="border-bottom: 1px solid #e5e5e5; height: 75px; position: fixed; _position: fixed; width: 100%; top: 0; left: 0; background: #fff; background: rgba(255, 255, 255, .95); z-index: 999; -webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, .2); -moz-box-shadow: 0 1px 6px rgba(0, 0, 0, .2); box-shadow: 0 1px 6px rgba(0, 0, 0, .2);">
		<div style=" float: left; background: url(<%=path %>/images/top_logo.png) no-repeat; width: 600px; height: 75px; margin-top: 5px; margin-left: 25px; bottom: 50%;"></div>
		<div style="position: absolute; right: 25px; bottom: 15px; " ><security:authentication property="principal.employee.name" /> , 您好，欢迎登录！登录时间: <%=DateUtils.currentString() %></div>
	</div>
	<div class="framework">
		<div class="navigation">
			<div class="head" style="border-bottom: 1px solid #e5e5e5; height: 85px; "></div>
			<ul>
				<c:forEach var="r" items="${resources }" varStatus="i">
				<li>
					<section class="title">
						<div class="left">
							<div class="sort"><c:if test="${i.index + 1 <= 9 }">0${i.index + 1 }</c:if><c:if test="${i.index + 1  > 9 }">${i.index + 1 }</c:if></div>
						</div>
						<div class="right">
							<div class="text">${r.name }</div>
							<div class="code">${r.code }</div>
						</div>
					</section>
					<div class="list-item none">
						<div class="tag"><i class="icon-caret-down"></i></div>
						<c:forEach var="s" items="${r.sublevel }">
						<section class="item">
							<c:if test="${ not empty s.iconInit }"><i class="${s.iconInit }"></i></c:if>
							<c:if test="${ empty s.iconInit }"><i class="glyphicon glyphicon-picture"></i></c:if>
							<c:if test="${fn:containsIgnoreCase(s.url, '?') }">
								<a title="${s.tips }" href="javascript:void(0);"  onclick="javascript:framework._jump('center', '<%=path %>${s.url }');" >
									<span>${s.name }<small>${s.code }</small></span>
								</a>
							</c:if>
							<c:if test="${!fn:containsIgnoreCase(s.url, '?') }">
								<a title="${s.tips }" href="javascript:void(0);"  onclick="javascript:framework._jump('center', '<%=path %>${s.url }<%=version %>');" >
									<span>${s.name }<small>${s.code }</small></span>
								</a>
							</c:if>
						</section>
						</c:forEach>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
		
		<button class="left-icon-btn off btn" ><i class="icon-circle-arrow-left"></i></button>
		<button class="left-icon-btn on btn" ><i class="icon-circle-arrow-right"></i></button>
		<div class="bottom-icon-btn">
			<hr />
			<div class="left"><button id="logout"><i class="icon-off"></i>退出<small>Sign Out</small></button></div>
			<div class="right"><button id="lock" class="btn off"><i class="icon-lock"></i></button></div>
		</div>
	</div>
	
	<div class="clear"></div>
	
	<div class="center">
		<iframe id="center" name="center" frameborder="0" marginheight="0px" marginwidth="0px" src="" style="margin-top: 80px; "></iframe>
		<div id="loading" class="loading"><div class="spinner"></div></div>
	</div>

	<div class="layer-mask"></div>

	<div class="modal fade bs-example-modal-sm" id="exit" tabindex="-1" role="dialog" aria-labelledby="exitLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="exitLabel"><i class="icon-off"></i>退出<small>Sign Out</small></h4>
				</div>
				<div class="modal-body">
					<div >
						确定退出系统?
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="javascript:window.location.href='<%=path %>/j_spring_security_logout';" >确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
