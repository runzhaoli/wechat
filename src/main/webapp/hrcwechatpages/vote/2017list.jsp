<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<%--  --%>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>2017年优秀员工、团队评选</title>
	<style type="text/css">
		.panel-heading a { padding: 10px 20px; font-size: 14px; font-family: Microsoft Yahei;}
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
							<h3>北京新致18年迎新大会优秀节目评选<br /><small>...</small>
								<div style="float: right; padding-top: 10px;"><button type="button" class="btn btn-success btn-xs" onclick="javascript:wechatmenu.go('excellentemployeequery');"><span class="glyphicon glyphicon-star"></span> 查看我的投票</button></div>
							</h3>
						</div>
					</div>
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<c:if test="${success != null }"><div class="alert alert-success" role="alert">${success }</div></c:if>
					<c:if test="${message != null }"><div class="alert alert-danger" role="alert">${message }</div></c:if>
					<c:if test="${_vote_start }">
						<div class="alert alert-danger" role="alert">投票还未开始噢~~</div>
					</c:if>
					<c:if test="${_vote_weekend }">
						<div class="alert alert-danger" role="alert">周末休息一下吧~~</div>
					</c:if>
					<c:if test="${_vote_end }">
						<div class="alert alert-danger" role="alert">感谢大家的积极参与~~<br/>投票已经截止啦~~</div>
					</c:if>
					<div class="alert alert-info" role="alert">北京新致18年迎新大会优秀节目评选投票开始啦！<br/>投票规则：每人可以投三票，决出前三名！<br/>投票时间：2018-01-27 14:30 至 2018-01-27 18:30 </div>

					<ol class="breadcrumb" style="margin-bottom: 5px;"><li class="active">节目列表</li></ol>
					<c:forEach items="${votes_type_1 }" var="s" varStatus="index">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="heading${s.id }" style="min-height: 55px; padding: 10px;" >
								<h4 class="panel-title" style="float: left; width: 80%;">
									<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${s.id }" aria-expanded="true" aria-controls="collapseOne" style="padding: 10px 0;">
										<div class="media">
											<div class="pull-right" style="width: 60px; margin: 10px 10px 0 0;">
												<span class="label label-info" <c:if test="${s.total > 500 }">style="background-color: #d9534f;"</c:if> ><fmt:formatNumber type="number" value="${s.percent }" pattern="0.0" maxFractionDigits="1"/> %</span>
											</div>
											<div class="pull-left">
												<img src="http://hr.newtouch.cn/attached/2017excellentemployee/body/${s.empno }.jpg" style="width: 35px; height: 35px;">
											</div>
											<div class="media-body" style="padding-left: 10px; font-size: 12px;">
												<h5 class="media-heading">${s.dept }</h5>
												${s.empno }, ${s.name }
											</div>
										</div>
									</a>
								</h4>
								<c:if test="${s.today != null }">
									<c:if test="${!_vote_start and !_vote_end and !_vote_weekend }">
									<form method="post" action="excellentemployeeselect">
										<input type="hidden" name="type" value="delete" />
										<input type="hidden" name="empno" value="${s.empno }" />
										<button type="submit" class="btn btn-xs btn-danger" style="float: right; margin-top: -4px; padding: 2px;" >取<br/>消</button>
									</form>
									</c:if>
									<c:if test="${_vote_start or _vote_end or _vote_weekend }">
										<button type="submit" class="btn btn-xs btn-danger" style="float: right; margin-top: -4px; padding: 2px;" disabled="disabled" >取<br/>消</button>
									</c:if>
									<span class="glyphicon glyphicon-ok" style="float: right; margin: 10px 18px; color: #5cb85c"></span>
								</c:if>
								<c:if test="${!_vote_start and !_vote_end and !_vote_weekend and s.today == null }">
									<form method="post" action="excellentemployeeselect">
										<input type="hidden" name="type" value="excellentemployee" />
										<input type="hidden" name="empno" value="${s.empno }" />
										<button type="submit" class="btn btn-warning" style="float: right; padding: 6px 12px;" >投票</button>
									</form>
								</c:if>
								<c:if test="${(_vote_start or _vote_end or _vote_weekend) and s.today == null }">
									<button type="submit" class="btn btn-warning" style="float: right; padding: 6px 12px;" disabled="disabled" >投票</button>
								</c:if>
							</div>
							<div id="collapse${s.id }" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body" style="font-size: 12px;">
<%-- 									<c:if test="${s.project != null }"><p><span class="label label-info">项目组名称</span></p><p style="line-height:25px;"><span style="padding-top: 10px; padding-left: 20px;">${s.project }</span></p></c:if> --%>
<%-- 									<p><span class="label label-info">节目类型</span></p><p style="line-height:25px;"><span style="padding-left: 20px;">${s.description }</span></p> --%>
<%-- 									<p><span class="label label-info">项目组名称</span></p><p style="line-height:25px;"><span style="padding-left: 20px;">${s.declaration }</span></p> --%>
<%-- 									<p><span class="label label-info">节目联系人及手机</span></p><p style="line-height:25px;"><span style="padding-left: 20px;">${s.recommend }</span></p> --%>
<%-- 									<p><span class="label label-info">时长</span></p><p style="line-height:25px;"><span style="padding-left: 20px;">${s.reference }</span></p> --%>
									<p><span class="label label-info">现场照片</span></p><p><img src="http://hr.newtouch.cn/attached/2017excellentemployee/body/${s.empno }.jpg" style="width: 100%; height: 100%; margin-top: 5px;"></p>
								</div>
							</div>
						</div>
					</c:forEach>

<!-- 					<ol class="breadcrumb" style="margin-top: 15px; margin-bottom: 5px;"><li class="active">优秀团队</li></ol> -->
<%-- 					<c:forEach items="${votes_type_2 }" var="s" varStatus="index"> --%>
<!-- 						<div class="panel panel-default"> -->
<%-- 							<div class="panel-heading" role="tab" id="heading${s.id }" style="min-height: 55px; padding: 10px;" > --%>
<!-- 								<h4 class="panel-title" style="float: left; width: 80%;"> -->
<%-- 									<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${s.id }" aria-expanded="true" aria-controls="collapseOne" style="padding: 10px 0;"> --%>
<!-- 										<div class="media"> -->
<!-- 											<div class="pull-right" style="width: 60px; margin: 10px 10px 0 0;"> -->
<%-- 												<span class="label label-info" <c:if test="${s.total > 500 }">style="background-color: #d9534f;"</c:if> ><fmt:formatNumber type="number" value="${s.percent }" pattern="0.0" maxFractionDigits="1"/> %</span> --%>
<!-- 											</div> -->
<!-- 											<div class="pull-left"> -->
<%-- 												<img src="http://hr.newtouch.cn/attached/2017excellentemployee/body/${s.empno }.jpg" style="width: 35px; height: 35px;"> --%>
<!-- 											</div> -->
<!-- 											<div class="media-body" style="padding-left: 10px; font-size: 12px;"> -->
<%-- 												<h5 class="media-heading">${s.dept }</h5> --%>
<%-- 												${s.empno }, ${s.name } --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</a> -->
<!-- 								</h4> -->
<%-- 								<c:if test="${s.today != null }"> --%>
<%-- 									<c:if test="${!_vote_start and !_vote_end and !_vote_weekend }"> --%>
<!-- 									<form method="post" action="excellentemployeeselect"> -->
<!-- 										<input type="hidden" name="type" value="delete" /> -->
<%-- 										<input type="hidden" name="empno" value="${s.empno }" /> --%>
<!-- 										<button type="submit" class="btn btn-xs btn-danger" style="float: right; margin-top: -4px; padding: 2px;" >取<br/>消</button> -->
<!-- 									</form> -->
<%-- 									</c:if> --%>
<%-- 									<c:if test="${_vote_start or _vote_end or _vote_weekend }"> --%>
<!-- 										<button type="submit" class="btn btn-xs btn-danger" style="float: right; margin-top: -4px; padding: 2px;" disabled="disabled" >取<br/>消</button> -->
<%-- 									</c:if> --%>
<!-- 									<span class="glyphicon glyphicon-ok" style="float: right; margin: 10px 18px; color: #5cb85c"></span> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${!_vote_start and !_vote_end and !_vote_weekend and s.today == null }"> --%>
<!-- 									<form method="post" action="excellentemployeeselect"> -->
<!-- 										<input type="hidden" name="type" value="excellentgroup" /> -->
<%-- 										<input type="hidden" name="empno" value="${s.empno }" /> --%>
<!-- 										<button type="submit" class="btn btn-warning" style="float: right; padding: 6px 12px;" >投票</button> -->
<!-- 									</form> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${(_vote_start or _vote_end or _vote_weekend) and s.today == null }"> --%>
<!-- 									<button type="submit" class="btn btn-warning" style="float: right; padding: 6px 12px;" disabled="disabled" >投票</button> -->
<%-- 								</c:if> --%>
<!-- 							</div> -->
<%-- 							<div id="collapse${s.id }" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne"> --%>
<!-- 								<div class="panel-body" style="font-size: 12px;"> -->
<%-- 									<p><span class="label label-info">所在项目</span></p><p style="line-height:25px;"><span style="padding-left: 20px;">${s.project }</span></p> --%>
<%-- 									<p><span class="label label-info">事迹介绍</span></p><p style="line-height:25px;"><span style="padding-left: 20px;">${s.description }</span></p> --%>
<%-- 									<c:if test="${s.declaration != null }"><p><span class="label label-info">宣言</span></p><p style="line-height:25px;"><span style="padding-left: 20px;">${s.declaration }</span></p></c:if> --%>
<%-- 									<p><span class="label label-info">推荐词</span></p><p style="line-height:25px;"><span style="padding-left: 20px;">${s.recommend }</span></p> --%>
<%-- 									<p><span class="label label-info">推荐人</span></p><p style="line-height:25px;"><span style="padding-left: 20px;">${s.reference }</span></p> --%>
<%-- 									<p><span class="label label-info">照片</span></p><p><img src="http://hr.newtouch.cn/attached/2017excellentemployee/body/${s.empno }.jpg" style="width: 100%; height: 100%; margin-top: 5px;"></p> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<%-- 					</c:forEach> --%>

					</div>
					<%@ include file="../common/copyright.jsp" %>
					
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>