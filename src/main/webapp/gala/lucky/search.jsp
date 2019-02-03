<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>中奖查询</title>
	<script type="text/javascript">
	$(document).ready(function() {
		
	});
	</script>
</head>
<body>
<div class="app has-navbar-top has-navbar-bottom">

	<div class="navbar navbar-app navbar-absolute-top"></div>

	<div class="app-body">
		<div class="app-content">
			<div class="scrollable">
				<div class="scrollable-content">
					<div class="container">
						<div class="page-header">
							<h3>新致2018年会中奖查询<br /><small>Search</small></h3>
						</div>
						<div class="alert alert-success" role="alert">走过路过不要错过！<br /><span style="color: #a94442;">仔细点看，是不是中奖了？</span></div>
						<c:if test="${message != null }"><br/><div class="alert alert-danger" role="alert">${message }</div></c:if>
						<table class="table table-striped">
							<c:forEach var="lucky" items="${luckies }">
								<tr>
									<td>${lucky.a }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<%@ include file="../common/copyright.jsp" %>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
</html>
