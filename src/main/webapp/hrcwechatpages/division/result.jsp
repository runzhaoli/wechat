<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>人事分工</title>
	<style type="text/css">
	.panel-default { padding-top: 20px; }
	.d-name {
		text-align: center;
		font-size: 2.5em;
		margin-top: -40px;
		margin-bottom: 5px;
		filter:alpha(opacity=50); /* IE */
		-moz-opacity:0.50; /* FireFox */
		opacity:0.50; /* Chrome, Opera, Safari */
	}
	.d-duty {
		text-align: center;
	}
	.d-contact {
		text-align: center;
	}
	</style>
</head>
<body>
<div class="app has-navbar-top has-navbar-bottom">
	
	<div class="navbar navbar-app navbar-absolute-top"></div>
	<div class="navbar navbar-app navbar-absolute-bottom"><%@ include file="../common/menu.jsp" %></div>
	
	<div class="app-body">
		<div class="app-content">
			<div class="scrollable">
				<div class="scrollable-content">
					
					<div class="container">
						<div class="page-header">
							<h3>人事分工<br /><small>List</small></h3>
						</div>
						<div style="margin: 2px;"></div>
						<c:forEach var="d" items="${divisions }">
							<div class="panel panel-default" style="background-color: #6161b1; color: #fff;">
								<span class="label label-primary" style="border-radius: 0em; font-size: 100%;"> ${d.module } </span>
								<div class="panel-body">
									<div class="d-name">${d.principal }</div>
									<div class="d-duty">${d.duty }</div>
									<hr style="width: 60%; border-color: #999;" />
									<div class="d-contact">${d.contact }</div>
								</div>
							</div>
						</c:forEach>
					</div>

					<%@ include file="../common/copyright.jsp" %>
					
				</div>
			</div>
		</div>
	</div>
		
</div>
</body>
</html>
