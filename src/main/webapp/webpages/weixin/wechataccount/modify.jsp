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

	<form id="modifyform" action="" method="post">
		<input type="hidden" name="id" value="${entity.id }" />
		<div id="task">
			<div class='bs-callout bs-callout-info fade in'>
				<hr />
				<div class="row">
					<div class="col-sm-2">
						<label>名称 name:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="name" value="${entity.name }" />
					</div>
					<div class="col-sm-2">
						<label>token:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="token" value="${entity.token }" />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label>account id:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="accountid" value="${entity.accountid }" />
					</div>
					<div class="col-sm-2">
						<label>encoding aes key:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="encodingAesKey" value="${entity.encodingAesKey }" />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label>appid:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="appid" value="${entity.appid }" />
					</div>
					<div class="col-sm-2">
						<label>corpId:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="corpId" value="${entity.corpId }" />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label>type:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="type" value="${entity.type }" />
					</div>
					<div class="col-sm-2">
						<label>email:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="email" value="${entity.email }" />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label>description:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="description" value="${entity.description }" />
					</div>
					<div class="col-sm-2">
						<label>appsecret:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="appsecret" value="${entity.appsecret }" />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label>accesstoken:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="accesstoken" value="${entity.accesstoken }" />
					</div>
					<div class="col-sm-2">
						<label>agentid:</label>
					</div>
					<div class="col-sm-4">
						<input type='text' name="agentid" value="${entity.agentid }" />
					</div>
				</div>
			</div>
		</div>
	</form>

	<div id="foot" style="margin-bottom: 150px;"></div>

</div>
</body>
</html>