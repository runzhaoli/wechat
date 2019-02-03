<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<link type="text/css" href="<%=path %>/style/icheck/skins/all.css<%=version %>" rel="stylesheet">
<script type="text/javascript" src="<%=path %>/script/icheck/jquery.icheck.min.js<%=version %>"></script>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }resourceauthorizecommit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
<style type="text/css">
.branch .in li { padding-left: 40px; }
</style>
<title></title>
</head>
<body>
<div class="container">

	<div class="head">
		<div class="row">
			<%@ include file="../../common/default-pagetitle.jsp" %>
			<div class="col-sm-6 col-xs-12">
				<div class="right-icon-btn">
				<%@ include file="../../common/default-operation-button.jsp" %>
				</div>
			</div>
		</div>
	</div>
	<jsp:include flush="true" page="../../common/default-nav-alert.jsp">
		<jsp:param value="${resource }" name="resource"/>
	</jsp:include>

	<form id="modifyform" method="post" >
		<input type="hidden" id="id" name="id" value="${entity.id }" />
		<div id="role" >
			<div class='bs-callout bs-callout-info fade in'>
				<hr />
				<div class="row">
					<div class="col-sm-4">
						<label>授权角色 role:</label>
					</div>
					<div class="col-sm-8">
						<div class='input-group'>
							<span class='input-group-addon'><i class='icon-th-large'></i></span>
							<input type='text' value="${entity.name } / ${entity.code } / ${entity.description }" disabled="disabled" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="tree" style="margin-top: 40px;">
			<div class='bs-callout bs-callout-info fade in'>
				<hr />
				<div class="row">
					<div class="col-sm-4">
						<label>${resource.name } ${resource.code }:</label>
					</div>
					<div class="col-sm-8">
						<div class="tree table-responsive">
							<ul class="branch in" style="padding-left: 10px; -webkit-padding-start: 10px;">
								<li><input id="all" type="checkbox" /><label for="all">所有资源 all resource</label>
									<ul class="branch in">
										<c:forEach var="r" items="${resources }">
										<li><input name="resourceid" id="${r.id }" value="${r.id }" type="checkbox" /><label for="${r.id }">${r.name } / ${r.code } / <dic:text group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${r.type }"></dic:text></label>
											<ul class="branch in">
												<c:forEach var="m" items="${r.sublevel }">
												<c:if test="${m.enabled and !m.delete }">
												<li><input name="resourceid" id="${m.id }" value="${m.id }" type="checkbox" /><label for="${m.id }"><c:if test="${m.iconInit != null }"><i class="${m.iconInit }"></i></c:if><c:if test="${m.iconInit == null }"><i class="icon-picture"></i></c:if>${m.name } / ${m.code } / <dic:text group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${m.type }"></dic:text></label>
													<ul class="branch in">
														<c:forEach var="s" items="${m.sublevel }">
														<c:if test="${s.enabled and !s.delete }">
														<li><input name="resourceid" id="${s.id }" value="${s.id }" type="checkbox" /><label for="${s.id }"><c:if test="${s.iconInit != null }"><i class="${s.iconInit }"></i></c:if><c:if test="${s.iconInit == null }"><i class="icon-picture"></i></c:if>${s.name } / ${s.code } / <dic:text group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${s.type }"></dic:text></label>
														</li>
														</c:if>
														</c:forEach>
													</ul>
												</li>
												</c:if>
												</c:forEach>
											</ul>
										</li>
										</c:forEach>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div id="foot" style="margin-bottom: 150px;"></div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('input').iCheck({
		checkboxClass: 'icheckbox_minimal',
		radioClass: 'iradio_minimal',
		increaseArea: '20%' // optional
	});
	if ($("input[name='resourceid']").length == ${fn:length(entity.resources) }) {
		$('input').iCheck('check');
	}
	<c:forEach var="r" items="${entity.resources }">$('#${r.id }').iCheck('check');
	</c:forEach>
});
$('#all').on('ifChecked', function(event) {
	$('input').iCheck('check');
});
$('#all').on('ifUnchecked', function(event) {
	$('input').iCheck('uncheck');
});
</script>
</body>
</html>