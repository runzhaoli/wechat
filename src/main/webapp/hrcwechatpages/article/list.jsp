<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<c:if test="${query.type == 'HELP' }"><title>我要办事</title></c:if>
	<c:if test="${query.type == 'POSITION' }"><title>查看已发布职位</title></c:if>
	<c:if test="${query.type == 'POLICY' }"><title>人事政策</title></c:if>

	<style type="text/css">
		.panel-heading a { padding: 10px 40px 10px 20px; font-size: 14px; font-family: Microsoft Yahei;}
		.panel-heading a:hover, .panel-heading a:focus { text-decoration: none; }
		.panel-group { padding: 0px 5px; }
		.panel-group .panel { border-radius: 0px; }
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
							<c:if test="${query.type == 'HELP' }"><h3>我要办事<br /><small>...</small></h3></c:if>
							<c:if test="${query.type == 'POSITION' }"><h3>查看职位<br /><small>...</small></h3></c:if>
							<c:if test="${query.type == 'POLICY' }"><h3>人事政策<br /><small>...</small></h3></c:if>
							<c:if test="${query.type == 'QUESTION' }"><h3>人事问答<br /><small>...</small></h3></c:if>
							
						</div>
					</div>
					<c:if test="${message != null }"><div class="alert alert-danger" role="alert">${message }</div></c:if>
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<c:forEach items="${article }" var="s" varStatus="index">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="heading${index.index }" >
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${index.index }" aria-expanded="true" aria-controls="collapseOne">${s.title }</a>
								</h4>
							</div>
							<div id="collapse${index.index }" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">${s.content }</div>
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