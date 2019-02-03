<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<script type="text/javascript" src="<%=path %>/script/My97DatePicker/WdatePicker.js<%=version %>"></script>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
<style type="text/css">
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
		<input type="hidden" id="eid" name="id" value="${entity.id }" />
		<div id="task">
			<div class='bs-callout bs-callout-info fade in'>
				<hr />
				<c:if test="${fn:containsIgnoreCase(resource.url, 'create') }">
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>登录账号 username:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="user.username" validation='required' data-placement='right' />
					</div>
					<div class="col-sm-2">
						<label><span class='required'>*</span>登录密码 password:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="user.password" validation='required' data-placement='right' />
					</div>
				</div>
				</c:if>
				<c:if test="${fn:containsIgnoreCase(resource.url, 'modify') }">
				<div class="row">
					<div class="col-sm-2">
						<label>登录账号 username:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="user.username" value="${entity.user.username }" readonly="readonly" disabled="disabled" />
					</div>
				</div>
				</c:if>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>姓名 name:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="name" value="${entity.name }" validation='required' data-placement='right' />
					</div>
					<div class="col-sm-2">
						<label><span class='required'>*</span>性别 gender:</label>
					</div>
					<div class="col-sm-4">
						<dic:select name="gender" validation='required' dataplacement='right' label="—— select ——" group="<%=ContextConstants.GROUP.GENDER %>" value="${entity.gender }"></dic:select>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label><span class='required'>*</span>用户类型 type:</label>
					</div>
					<div class="col-sm-4">
						<dic:select name="type" validation='required' dataplacement='right' label="—— select ——" group="<%=ContextConstants.GROUP.EMPLOYEE_TYPE %>" value="${entity.type }" ></dic:select>
					</div>
					<div class="col-sm-2">
						<label>电子邮箱 email:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="email" value="${entity.email }" />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label>生日 birthday:</label>
					</div>
					<div class="col-sm-4">
						<div class='input-group'>
							<span class='input-group-addon'><i class='icon-calendar'></i></span>
							<input type='text' id='birthday' name="birthday" value="<fmt:formatDate value='${entity.birthday }' pattern='<%=DateUtils.DAY_PATTERN %>' />" data-placement='right' onclick='javascript:WdatePicker({doubleCalendar:true,isShowClear:false,readOnly:true});' readonly='readonly' />
						</div>
					</div>
					<div class="col-sm-2">
						<label>联系电话 tel:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="telephone" value="${entity.telephone }" data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label>手机 mobile:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="mobile" value="${entity.mobile }" />
					</div>
					<div class="col-sm-2">
						<label>联系地址 address:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="address" value="${entity.address }" data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label>入职时间 in date:</label>
					</div>
					<div class="col-sm-4">
						<div class='input-group'>
							<span class='input-group-addon'><i class='icon-calendar'></i></span>
							<input type='text' id='indate' name="indate" value="<fmt:formatDate value='${entity.indate }' pattern='<%=DateUtils.DAY_PATTERN %>' />" data-placement='right' onclick='javascript:WdatePicker({doubleCalendar:true,isShowClear:false,readOnly:true});' readonly='readonly' />
						</div>
					</div>
					<div class="col-sm-2">
						<label><span class='required'>*</span>所属机构 org:</label>
					</div>
					<div class="col-sm-4">
						<select name="organization.id" validation='required ' data-placement='right' >
							<option value="">—— select ——</option>
						<c:forEach var="o" items="${organizations }">
							<option value="${o.id }" <c:if test="${o.id == entity.organization.id }"> selected="selected" </c:if> >${o.name } / ${o.code}</option>
						</c:forEach>
						</select>
					</div>
				</div>
			</div>
		
		</div>
	</form>

	<div id="foot" style="margin-bottom: 150px;"></div>
</div>
</body>
</html>