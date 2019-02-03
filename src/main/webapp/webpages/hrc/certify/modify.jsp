<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<link type="text/css" rel="stylesheet" href="<%=path %>/umeditor/themes/default/css/umeditor.min.css<%=version %>" />
<script type="text/javascript" charset="utf-8" src="<%=path %>/umeditor/umeditor.config.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/umeditor/umeditor.min.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/umeditor/lang/zh-cn/zh-cn.js<%=version %>"></script>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
<script type="text/javascript">

</script>
<style type="text/css">
.icheckbox_minimal { float: left; }
.input-group:hover, .input-group input:hover { cursor: pointer; }
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

	<form id="modifyform" action="" method="post">
		<input type="hidden" name="id" value="${entity.id }" />
		<div id="task">
			<div class='bs-callout bs-callout-info fade in'>
				<hr />
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>工号 empno</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="empno" value="${entity.empno }" readonly="readonly" disabled="disabled" maxlength="32" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>姓名 name</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="staff.name" value="${entity.staff.name }" readonly="readonly" disabled="disabled" maxlength="32" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>身份证 identity</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="identity" value="${entity.staff.idno }" readonly="readonly" disabled="disabled" maxlength="32" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>用途描述 description</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="description" value="${entity.description }" readonly="readonly" disabled="disabled" maxlength="128" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>证明类型 proof type</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<dic:text group="<%=ContextConstants.GROUP.PROOF_TYPE %>" value="${entity.proofType }"></dic:text>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>接收类型 receive type</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<dic:text group="<%=ContextConstants.GROUP.RECEIVE_TYPE %>" value="${entity.receiveType }"></dic:text>
					</div>
				</div>
				<c:if test="${entity.receiveType == '1' }">
					<div class="row">
						<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
							<label>手机号码 mobile</label>
						</div>
						<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
							<input type='text' name="mobile" value="${entity.mobile }" readonly="readonly" disabled="disabled" maxlength="32" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
							<label>寄送地址 address</label>
						</div>
						<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
							<input type='text' name="address" value="${entity.address }" readonly="readonly" disabled="disabled" maxlength="128" />
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>审核状态 audit status</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<dic:select name="auditStatus" validation='required' dataplacement='right' group="<%=ContextConstants.GROUP.CERTIFY_AUDIT_STATUS %>" value="${entity.auditStatus }"></dic:select>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>审核描述 audit remark</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="auditRemark" value="${entity.auditRemark }" validation='required' data-placement='right' maxlength="128" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>快递单号 express</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="express" value="${entity.express }" data-placement='right' maxlength="32" />
					</div>
				</div>
				
			</div>
		</div>
	</form>

	<div id="foot" style="margin-bottom: 80px;"></div>

</div>
</body>
</html>