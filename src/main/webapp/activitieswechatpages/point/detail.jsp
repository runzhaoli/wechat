<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN" manifest="">
<head>
	<%@ include file="../common/path.jsp" %>
	<style type="text/css">
	.weui-grid__icon { width: 64px; height: 64px; }
	</style>
	<title>二十三周年庆活动</title>
</head>
<body>
	<header class="weui-header">
		<h1 class="weui-title">总积分<br /><small style="font-size: 60%;">${total }</small></h1>
	</header>
	<div class="weui-panel">
		<div class="weui-panel__hd"><small>工号：${user.empno }，姓名：${user.name }</small></div>
		<div class="weui-panel__bd">
			<div class="weui-media-box weui-media-box_small-appmsg">
				<div class="weui-cells">
					<c:forEach var="s" items="${items }">
					<div class="weui-cell" >
						<div class="weui-cell__hd">
							<c:if test="${ not empty s.icon }"><img src="<%=path%>/images/wechat/${s.icon }" 
								alt="" style="width: 20px; margin-right: 5px; display: block"></c:if>
							<c:if test="${ empty s.icon }"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII="
								alt="" style="width: 20px; margin-right: 5px; display: block"></c:if>
						</div>
						<div class="weui-cell__bd weui-cell_primary">
							<small>${s.name }</small>
						</div>
						<div class="weui-cell__ft">
							<small style="margin-right: 10px;">分数</small><small>${s.point.score }</small>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/copyright.jsp" %>
</body>
</html>