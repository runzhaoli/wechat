<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>查看历次证明申请列表</title>
	<style type="text/css">
	.bs-callout { margin: 0 5px 20px; padding: 0 5px; border: 1px solid #eee; border-left: 5px solid #eee; }
	.bs-callout hr { margin-top: 0px; }
	.bs-callout .progress { margin-bottom: 10px; }
	.bs-callout .table { width: auto; }
	.bs-callout .table tr td, .bs-callout .table tr th { color: #777; border: 0px; text-align: left; font-size: 13px; font-variant: small-caps; border-bottom: 1px solid #fff; }
	.bs-callout-warning { background-color: #faf8f0; border-left-color: #faebcc; }
	.bs-callout-warning h4 { color: #8a6d3b; }
	.bs-callout-danger { background-color: #fdf7f7; border-left-color: #eed3d7; }
	.bs-callout-danger h4 { color: #b94a48; }
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
							<h3>历次证明申请列表<br /><small>List</small></h3>
						</div>
						<div style="margin: 2px;"></div>
						<div class="form-group">
							<label for="empno">工号 Empno <span class="label label-danger">${user.empno }</span> / 姓名 Name <span class="label label-danger">${user.name }</span></label>
						</div>
					</div>
					<c:forEach var="s" items="${certifies }">
					<div class="bs-callout <c:if test="${s.proofType == 1}">bs-callout-warning</c:if><c:if test="${s.proofType == 2}">bs-callout-danger</c:if> fade in">
						<div class="panel-body">
							<label style="padding: 8px;">证明类型：<dic:text group="<%=ContextConstants.GROUP.PROOF_TYPE %>" value="${s.proofType }"></dic:text></label>
							<hr />
							<label>申请时间：</label><fmt:formatDate value="${s.create }" pattern="yyyy-MM-dd HH:mm:ss"/><br />
							<label>申请状态：</label>
							<div class="progress">
								<div class="progress-bar <c:if test="${s.auditStatus == 0 }">progress-bar-danger</c:if> <c:if test="${s.proofType == 1 and s.auditStatus != 0 }">progress-bar-success</c:if><c:if test="${s.proofType == 2 and s.auditStatus != 0 }">progress-bar-info</c:if>" role="progressbar" aria-valuenow="${s.auditStatus }" aria-valuemin="0" aria-valuemax="4" style="min-width: 2em; width: ${s.progress }%;">
									<dic:text group="<%=ContextConstants.GROUP.CERTIFY_AUDIT_STATUS %>" value="${s.auditStatus }"></dic:text>
								</div>
							</div>
							<label>审核描述：</label><span style="font-size: 12px;">${s.auditRemark }</span><br />
							<c:if test="${s.auditStatus == 4}"><label>快递单号：</label><span style="font-size: 12px;">${s.express }</span></c:if>
							
						</div>
					</div>
					</c:forEach>

					<%@ include file="../common/copyright.jsp" %>
					
				</div>
			</div>
		</div>
	</div>
		
</div>
</body>
</html>
