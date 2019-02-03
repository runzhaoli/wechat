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
				<c:if test="${!entity.anonymous }">
					<div class="row">
						<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
							<label>工号 empno</label>
						</div>
						<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
							<input type='text' name="empno" value="${entity.staff.empno }" readonly="readonly" disabled="disabled" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
							<label>姓名 name</label>
						</div>
						<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
							<input type='text' name="name" value="${entity.staff.name }" readonly="readonly" disabled="disabled" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
							<label>是否匿名 anonymous</label>
						</div>
						<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
							否
						</div>
					</div>
				</c:if>
				<c:if test="${entity.anonymous }">
					<div class="row">
						<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
							<label>工号 empno</label>
						</div>
						<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
							<input type='text' name="empno" value="***" readonly="readonly" disabled="disabled" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
							<label>姓名 name</label>
						</div>
						<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
							<input type='text' name="name" value="***" readonly="readonly" disabled="disabled" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
							<label>是否匿名 anonymous</label>
						</div>
						<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
							是
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>投诉与建议类型 type</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<dic:select group="<%=ContextConstants.GROUP.COMPLAINT_PROPOSAL_TYPE %>" value="${entity.type }" showcode="false" readonly="readonly" disabled="disabled" ></dic:select>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>投诉或建议描述 description</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<textarea rows="12" name="description" readonly="readonly" disabled="disabled" >${entity.description }</textarea>
					</div>
				</div>
			</div>
		</div>
	</form>

	<div id="foot" style="margin-bottom: 80px;"></div>

</div>
</body>
</html>