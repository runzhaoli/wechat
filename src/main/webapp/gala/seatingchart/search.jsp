<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>座位查询</title>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#commit").click(function() {
			if (!validation()) {
				setTimeout(function () {
					$(":input").removeAttr("readonly");
					$("#name").attr("readonly", "readonly");
					$(":button,:submit").attr("disabled", null);
					$('[data-toggle=popover]').popover('show');
				}, 500);
				return ;
			}
			$("#accumulationfundform").submit();
		});
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
							<h3>新致2018年会座位查询<br /><small>Search</small></h3>
						</div>
						<div class="alert alert-success" role="alert">输入“工号”或“姓名”查询您的座位<br /><span style="color: #a94442;">任意输入一个条件即可</span></div>
						<form id="accumulationfundform" method="post" action="" onsubmit="submit.disabled = 1;" >
							<div class="form-group">
								<label for="name">工号 Empno</label>
								<input type="text" class="form-control" name="empno" id="empno" value="${empno }" placeholder="Empno" maxlength="32" />
							</div>
							<div class="form-group">
								<label for="name">姓名 Name</label>
								<input type="text" class="form-control" name="name" id="name" value="${name }" placeholder="Name" maxlength="32" />
							</div>
							<button type="button" id="commit" class="btn btn-default">查询</button>
						</form>
						<c:if test="${message != null }"><br/><div class="alert alert-danger" role="alert">${message }</div></c:if>
						<c:forEach var="chart" items="${charts }">
							<div class="panel panel-info" style="margin-top: 25px;">
								<div class="panel-heading">
									<h3 class="panel-title">第 ${chart.no } 桌，
										<c:if test="${chart.no > 0   && chart.no <= 50  }">A</c:if>
										<c:if test="${chart.no > 50  && chart.no <= 100 }">B</c:if>
										<c:if test="${chart.no > 100 && chart.no <= 150 }">C</c:if>
										<c:if test="${chart.no > 150 && chart.no <= 200 }">D</c:if>
										<c:if test="${chart.no > 200 && chart.no <= 250 }">E</c:if>
										<c:if test="${chart.no > 0   && chart.no <= 250 }">区</c:if>
									</h3>
								</div>
								<div class="panel-body" style="line-height: 30px;">
									<c:if test="${chart.a != null }"><span class="label label-primary">${chart.a }</span></c:if>
<%-- 									<c:if test="${chart.b != null }"><span class="label label-success">${chart.b }</span></c:if> --%>
									<c:if test="${chart.c != null }"><span class="label label-info">${chart.c }</span></c:if>
<%-- 									<c:if test="${chart.d != null }"><span class="label label-warning">${chart.d }</span></c:if> --%>
									<c:if test="${chart.e != null }"><span class="label label-success">${chart.e }</span></c:if>
<%-- 									<c:if test="${chart.f != null }"><span class="label label-success">${chart.f }</span></c:if> --%>
									<c:if test="${chart.g != null }"><span class="label label-warning">${chart.g }</span></c:if>
<%-- 									<c:if test="${chart.h != null }"><span class="label label-warning">${chart.h }</span></c:if> --%>
									<c:if test="${chart.i != null }"><span class="label label-primary">${chart.i }</span></c:if>
<%-- 									<c:if test="${chart.j != null }"><span class="label label-success">${chart.j }</span></c:if> --%>
									<c:if test="${chart.k != null }"><span class="label label-info">${chart.k }</span></c:if>
<%-- 									<c:if test="${chart.l != null }"><span class="label label-warning">${chart.l }</span></c:if> --%>
									<c:if test="${chart.m != null }"><span class="label label-success">${chart.m }</span></c:if>
<%-- 									<c:if test="${chart.n != null }"><span class="label label-success">${chart.n }</span></c:if> --%>
									<c:if test="${chart.o != null }"><span class="label label-warning">${chart.o }</span></c:if>
<%-- 									<c:if test="${chart.p != null }"><span class="label label-warning">${chart.p }</span></c:if> --%>
									<c:if test="${chart.q != null }"><span class="label label-primary">${chart.q }</span></c:if>
<%-- 									<c:if test="${chart.r != null }"><span class="label label-success">${chart.r }</span></c:if> --%>
									<c:if test="${chart.s != null }"><span class="label label-info">${chart.s }</span></c:if>
<%-- 									<c:if test="${chart.t != null }"><span class="label label-warning">${chart.t }</span></c:if> --%>
									<c:if test="${chart.u != null }"><span class="label label-success">${chart.u }</span></c:if>
<%-- 									<c:if test="${chart.v != null }"><span class="label label-success">${chart.v }</span></c:if> --%>
									<c:if test="${chart.w != null }"><span class="label label-warning">${chart.w }</span></c:if>
<%-- 									<c:if test="${chart.x != null }"><span class="label label-warning">${chart.x }</span></c:if> --%>
									<c:if test="${chart.y != null }"><span class="label label-primary">${chart.y }</span></c:if>
<%-- 									<c:if test="${chart.z != null }"><span class="label label-success">${chart.z }</span></c:if> --%>
								</div>
							</div>
						</c:forEach>
						<div class="thumbnail" style="margin-top: 20px;">
							<div class="caption" style="text-align: center;">
								<h4>场馆区域图</h4>
							</div>
							<img src="../images/20180131145216.jpg" alt="...">
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
