<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>团队介绍</title>
	<style type="text/css">
	.jumbotron h1 { margin-bottom: 20px; }
	.jumbotron { padding-left: 15px; padding-right: 15px; }
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
							<h3>团队介绍 <br /><small>About</small></h3>
						</div>
						<div class="jumbotron">
							<h1>Hello, Newtouch!</h1>
							<p style="font-size: 14px; color: #999; "><small>颜值担当 [ 纯属拍领导马屁 ] ：</small> 李峰</p>
							<p style="font-size: 14px;"><small><b>低调，低调！ [ 对，就是我 ] ：</b></small> kk</p>
							<p style="font-size: 14px;"><small>有事别找我 [ 请叫我管爷 ] ：</small> 管宏伟</p>
							<p style="font-size: 14px;"><small>不知道是什么担当 [ 被迫添加 ] ：</small> 龚黎雯</p>
							<p style="font-size: 14px;">...</p>
							<p style="font-size: 14px; color: #ccc; ">.<small>[ 虚位以待，欢迎加入 ]</small>.</p>
							<p style="font-size: 14px;">...</p>
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
