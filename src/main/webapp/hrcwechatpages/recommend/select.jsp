<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>我要举荐</title>
	<style type="text/css">
		.option { margin: 5px; text-align: right; }
		.option button { height: 40px; width: 120px; }
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
							<h3>我要举荐<br /><small>Recommend</small></h3>
						</div>
						<div class="list-group">
							<div class="list-group-item active" >
								<h4 class="list-group-item-heading">查看发布的职位信息</h4>
								<p class="list-group-item-text">...</p>
								<div class="option">
									<button type="button" class="btn btn-success" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.ARTICLE_LIST %>?type=POSITION');" >查看职位</button>
								</div>
							</div>
						</div>
						<div class="list-group">
							<div class="list-group-item active" >
								<h4 class="list-group-item-heading">填写举荐信息</h4>
								<p class="list-group-item-text">...</p>	
								<div class="option">
									<button type="button" class="btn btn-info" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.RECOMMEND_SEARCH %>');" >我要举荐</button>
								</div>
							</div>
						</div>
						<div class="list-group">
							<div class="list-group-item active" >
								<h4 class="list-group-item-heading">查看历次的举荐信息</h4>
								<p class="list-group-item-text">...</p>
								<div class="option">
									<button type="button" class="btn btn-warning" onclick="javascript:wechatmenu.go('<%=MappingConstants.UrlMapping.RECOMMEND_RESULT %>');" >查看详情</button>
								</div>
							</div>
						</div>
					</div>
					<%@ include file="../common/copyright.jsp" %>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
</html>
