<%@page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../common/default-path.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	
	
});
function synchronizationservertime() {
	$.ajax({
		type : 'POST',
		async : true,
		url : "<%=path %>/attendance/synchronizationservertime?v=" + Math.random(),
		dataType : "json",
		data : {
			"t" : "<%=DateUtils.currentDate().getTime() %>",
			"s" : "<%=request.getRequestedSessionId() %>",
			"l" : "<%=request.getHeader("Accept-Language") %>",
			"m" : "<%=MD5Utils.md5Hex(url) %>"
		},
		success : function(data) {
			_d = data['time'];
// 			console.log(_d);
		},
		error : function(data, status, e) {
// 			alert(e);
		}
	});
}
</script>
<title>?</title>
</head>
<body>
<div class="container">

	<div class="head">
		<div class="row">
			<%@ include file="../common/default-pagetitle.jsp" %>
		</div>
	</div>

	<div class="nav">
		<ol class="breadcrumb">
			<li><i class="icon-home"></i><a href="javascript:void(0);" onclick="javascript:parent.kklazy._show_loading();parent.kklazy._jump('<%=path %>/console/center<%=version %>')">首页</a></li>
			<c:if test="${ not empty resource.parent.parent.name }">
			<li><a href="javascript:void(0);">${resource.parent.parent.name }</a></li>
			</c:if>
			<li><a href="javascript:void(0);">${resource.parent.name }</a></li>
			<li class="active">${resource.name }</li>
		</ol>
	</div>

	<div id="index">
		<div class="row">
			<div class="col-md-4">
				<section class="panel">
					<div class="row">
						<div class="user col-sm-12">
							<div class="portrait" style="color: ${ details.color };">
								<i class="icon-user icon-4x"></i>
							</div>
							<div class="info">
								<div class="name"><span><b>${ details.employee.name }</b></span>,
									<%
									int hour = DateUtils.getCalendar().get(Calendar.HOUR_OF_DAY);
									if (0 <= hour && hour <= 5) { %>天还没亮呢!<% }
									if (5 < hour && hour <= 8) { %>早上好!<% }
									if (8 < hour && hour <= 10) { %>上午好!<% }
									if (10 < hour && hour <= 11) { %>中午好!<% }
									if (11 < hour && hour <= 17) { %>下午好!<% }
									if (17 < hour && hour <= 21) { %>晚上好!<% }
									if (21 < hour && hour <= 23) { %>注意休息!<% }
									%>
								</div>
								<div class="um">ID: ${ details.employee.id }</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<div class="col-md-1"><div class="clear"></div></div>
			<div class="col-md-7">
				<section class="panel" >
					<div class="row">
						<div class="time col-sm-6">
							<div class="servertime">服务器当前时间:</div>
							<div class="localtime">
								<div id="_t"></div>
							</div>
							<div class="date" id="_d"></div>
							<div class="country"><i class="icon-map-marker"></i>中国</div>
							<script type="text/javascript">
							var _d = <%= System.currentTimeMillis() + 200 %>;
							function t(t) { var n = new Date(t), h = n.getHours(), m = n.getMinutes(), s = n.getSeconds(); if (h < 10) { h = "0" + h } if (m < 10) { m = "0" + m } if (s < 10) { s = "0" + s } return h + ":" + m + ":" + s; }
							function d(t) { var n = new Date(t), y = n.getFullYear(), m = n.getMonth() + 1, d = n.getDate(); if (m < 10) { m = "0" + m } if (d < 10) { d = "0" + d } return y + " 年 " + m + " 月 " + d + " 日"; }
							$('#_t').html(t(_d));
							$('#_d').html(d(_d) + ' 星期'+'日一二三四五六'.charAt(new Date(_d).getDay()));
							setInterval("javascript:_d += 200; $('#_t').html(t(_d)); $('#_d').html(d(_d) + ' 星期'+'日一二三四五六'.charAt(new Date(_d).getDay()))", 200);
// 							setInterval("javascript:synchronizationservertime();", 1000 * 60 );
							</script>
						</div>
						<div class="sign col-sm-6">
							<button id="attendance" class="btn " style="background: ${ details.color };">
								<span class="glyphicon glyphicon-time"></span>
							</button>
						</div>
					</div>
				</section>
			</div>
		</div>
		<div style="margin-top: 30px;"></div>
	
		<div class="row state-overview">
			<div class="col-sm-6">
				<section class="panel">
					<div class="symbol "
						style="background: ${ details.color };">
						<span class="glyphicon glyphicon-time"></span>
					</div>
					<div class="value">
						<h1 class="count">495</h1>
						<p>New Users</p>
					</div>
				</section>
			</div>
			<div class="col-sm-6">
				<section class="panel">
					<div class="symbol "
						style="background: ${ details.color };">
						<span class="glyphicon glyphicon-tags"></span>
					</div>
					<div class="value">
						<h1 class=" count2">947</h1>
						<p>Sales</p>
					</div>
				</section>
			</div>
			<div class="col-sm-6">
				<section class="panel">
					<div class="symbol "
						style="background: ${ details.color };">
						<span class="glyphicon glyphicon-thumbs-up"></span>
					</div>
					<div class="value">
						<h1 class=" count3">328</h1>
						<p>New Order</p>
					</div>
				</section>
			</div>
			<div class="col-sm-6">
				<section class="panel">
					<div class="symbol "
						style="background: ${ details.color };">
						<span class="glyphicon glyphicon-stats"></span>
					</div>
					<div class="value">
						<h1 class=" count4">10328</h1>
						<p>Total Profit</p>
					</div>
				</section>
			</div>
		</div>
	</div>
	
	<div id="foot" style="padding-bottom: 80px;"></div>
</div>
</body>
</html>