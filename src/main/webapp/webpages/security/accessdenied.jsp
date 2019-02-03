<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../common/default-path.jsp" %>
</head>
<body>
<div style="margin-top: 40px;"></div>
<div class="container">
	<div class="alert alert-default fade in">
		<h4 align="center"><b>[拒绝访问]</b> Access Denied !!<small>没有访问权限</small></h4>
	</div>
	<div class="table-responsive">
		<table class="table  table-condensed">
			<thead>
				<tr>
					<th style="white-space: nowrap;">参数名[Parameter]</th>
					<th>属性值[Value]</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>Now</th>
					<td><%=DateUtils.currentString() %></td>
				</tr>
				<tr>
					<th>RemoteAddr</th>
					<td><%=request.getRemoteAddr() %></td>
				</tr>
				<tr>
					<th>RemoteHost</th>
					<td><%=request.getRemoteHost() %></td>
				</tr>
				<tr>
					<th>Cookie</th>
					<td><%=request.getHeader("Cookie") %></td>
				</tr>
				<tr>
					<th>Referer</th>
					<td><%=request.getHeader("Referer") %></td>
				</tr>
				<tr>
					<th>User-Agent</th>
					<td><%=request.getHeader("User-Agent") %></td>
				</tr>
			</tbody>
		</table>
	</div>
	<blockquote class="pull-left" style="width: 100%;">
		<small>Program exception information <cite title="Source Title">By kkLazy</cite> <a href="<%=path %>/console/center">home</a></small>
	</blockquote>

</div>

</body>
</html>