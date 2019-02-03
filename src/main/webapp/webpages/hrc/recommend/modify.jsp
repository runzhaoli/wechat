<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
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
				<div style="margin: 0 0 20px 10px;"><span class="label label-success">举荐人信息</span></div>
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
				<div style="margin: 0 0 20px 10px;"><span class="label label-success">被举荐人信息</span></div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>姓名 name</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="name" value="${entity.name }" readonly="readonly" disabled="disabled" maxlength="32" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>职位 position</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="position" value="${entity.position }" readonly="readonly" disabled="disabled" maxlength="32" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>工作年限 working years</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="workingYears" value="${entity.workingYears }" readonly="readonly" disabled="disabled" maxlength="32" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>联系电话 mobile</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="mobile" value="${entity.mobile }" readonly="readonly" disabled="disabled" maxlength="32" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>邮箱地址 email</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="email" value="${entity.email }" readonly="readonly" disabled="disabled" maxlength="32" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>对被举荐人的描述 description</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<textarea name="description" rows="6" readonly="readonly" disabled="disabled" maxlength="128" >${entity.description }</textarea>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>审核状态 audit status</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<dic:select name="auditStatus" validation='required' dataplacement='right' group="<%=ContextConstants.GROUP.RECOMMEND_AUDIT_STATUS %>" value="${entity.auditStatus }"></dic:select>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>审核描述 audit remark</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<textarea name="auditRemark" rows="6" validation='required' data-placement='right'  maxlength="128" >${entity.auditRemark }</textarea>
					</div>
				</div>
			</div>
		</div>
	</form>

	<div id="foot" style="margin-bottom: 80px;"></div>

</div>
</body>
</html>