<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
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
			
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">基本信息</a></li>
		<li role="presentation"><a href="#role" aria-controls="role" role="tab" data-toggle="tab">关联的角色</a></li>
		<li role="presentation"><a href="#data" aria-controls="data" role="tab" data-toggle="tab">数据(机构)权限</a></li>
	</ul>
	<div class="tab-content">
		<div class='bs-callout bs-callout-default fade in tab-pane active' role="tabpanel" id="detail">
			<div class="row">
				<div class="col-sm-4">
					<label>基本信息 detail:</label>
					<hr />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>登录账号 username:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.user.username }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>姓名 name:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.name }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>性别 gender:</label>
				</div>
				<div class="col-sm-4">
					<dic:select label="—— select ——" group="<%=ContextConstants.GROUP.GENDER %>" value="${entity.gender }" readonly="readonly" disabled="disabled" ></dic:select>
				</div>
			</div>
			<div class="row">
<!-- 				<div class="col-sm-2"> -->
<!-- 					<label>用户类型 type:</label> -->
<!-- 				</div> -->
<!-- 				<div class="col-sm-4"> -->
<%-- 					<dic:select label="—— select ——" group="<%=ContextConstants.GROUP.EMPLOYEE_TYPE %>" value="${entity.type }" readonly="readonly" disabled="disabled" ></dic:select> --%>
<!-- 				</div> -->
				<div class="col-sm-2">
					<label>电子邮箱 email:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.email }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>生日 birthday:</label>
				</div>
				<div class="col-sm-4">
					<div class='input-group'>
						<span class='input-group-addon'><i class='icon-calendar'></i></span>
						<input type='text' value="<fmt:formatDate value='${entity.birthday }' pattern='<%=DateUtils.DAY_PATTERN %>' />" onclick='javascript:WdatePicker({doubleCalendar:true,isShowClear:false,readOnly:true});' readonly="readonly" disabled="disabled" />
					</div>
				</div>
				<div class="col-sm-2">
					<label>联系电话 tel:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.telephone }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>手机 mobile:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.mobile }" readonly="readonly" disabled="disabled" />
				</div>
				<div class="col-sm-2">
					<label>联系地址 address:</label>
				</div>
				<div class="col-sm-4">
					<input type='text' value="${entity.address }" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label>入职时间 in date:</label>
				</div>
				<div class="col-sm-4">
					<div class='input-group'>
						<span class='input-group-addon'><i class='icon-calendar'></i></span>
						<input type='text' value="<fmt:formatDate value='${entity.indate }' pattern='<%=DateUtils.DAY_PATTERN %>' />" onclick='javascript:WdatePicker({doubleCalendar:true,isShowClear:false,readOnly:true});' readonly="readonly" disabled="disabled" />
					</div>
				</div>
				<div class="col-sm-2">
					<label>所属机构 org:</label>
				</div>
				<div class="col-sm-4">
					<select disabled="disabled" >
						<option>${entity.organization.name } / ${entity.organization.code}</option>
					</select>
				</div>
			</div>
		</div>
		<div class='bs-callout bs-callout-default fade in tab-pane ' role="tabpanel" id="role">
			<div class="row">
				<div class="col-sm-4">
					<label>关联的角色 role:</label>
					<hr />
				</div>
				<div class="col-sm-8">
					<ul class="branch in">
						<li style="list-style: none;"><label>角色名称 name / 角色代码 code</label></li>
						<c:forEach var="r" items="${entity.roles }">
							<li><label>${r.name } / ${r.code }</label>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class='bs-callout bs-callout-default fade in tab-pane ' role="tabpanel" id="data">
			<div class="row">
				<div class="col-sm-4">
					<label>数据(机构)权限 organizations:</label>
					<hr />
				</div>
				<div class="col-sm-8">
					<ul class="branch in">
						<li style="list-style: none;"><label>机构名称 name / 机构代码 code</label></li>
						<c:forEach var="d" items="${entity.data }">
							<li><label>${d.name } / ${d.code }</label>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div id="foot" style="margin-bottom: 150px;"></div>

</div>
</body>
</html>