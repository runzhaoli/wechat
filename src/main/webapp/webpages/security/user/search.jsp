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
	<button class="icon-btn" id="search">
		<i class="icon-check"></i>
		<div>查询</div>
	</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include flush="true" page="../../common/default-nav-alert.jsp">
		<jsp:param value="${resource }" name="resource"/>
	</jsp:include>

	<form id="searchForm" action="" method="post">
		<div class="modal fade" id="query" tabindex="-1" role="dialog" aria-labelledby="queryLabel" aria-hidden="true">
			<div class="modal-dialog" >
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="queryLabel"><i class="icon-search"></i>查询<small>Search</small></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
								<label>登录用户名 <small>username</small>:</label>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type='text' name='title' id='title' value='${entity.username }' autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type='hidden' name='pageindex' id='pageindex' value='0' />
						<input type='hidden' name='pagesize'  id='pagesize'  value='10' />
						<button type="button" id="commit" class="btn btn-default" >Search</button>
						<button type="button" id="cancel" class="btn btn-default" >Reset</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div class="data table-responsive">
		<table class="table table-hover ">
			<thead>
				<tr>
					<th>登录用户名</th>
					<th>姓名</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="entity" items="${page.content }">
				<tr>
					<td>${entity.username }</td>
					<td>${entity.employee.name }</td>
					<td>
						<button class="btn btn-primary btn-xs" title="重置密码" onclick="javascript:jumpentity('modify/${entity.id}<%=version %>');"><i class="icon-pencil"></i> 重置密码</button>
<%-- 						<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${entity.id}<%=version %>');"><i class="icon-trash"></i> 删除</button> --%>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td id="pagebartd" colspan="1">
						<jsp:include flush="true" page="../../common/default-pagebar.jsp">
							<jsp:param value="${page }" name="page" />
							<jsp:param value="search" name="action" />
							<jsp:param value="searchForm" name="form" />
						</jsp:include>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
    
</div>

<div id="foot" style="margin-bottom: 150px;"></div>
</body>
</html>