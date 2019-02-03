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
<style type="text/css">
.table-responsive .table th { border-bottom: 1px solid #ddd; }
</style>
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
								<label>工号 <small>empno</small>:</label>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type='text' name='empno' id='empno' value='${query.empno }' autocomplete="off" />
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
		<table class="table">
			<thead>
				<tr>
					<th colspan="2">推荐人信息</th>
					<th colspan="5" style="border-left: 1px solid #ccc;">被推荐人信息</th>
					<th colspan="3" style="border-left: 1px solid #ccc;"></th>
				</tr>
				<tr>
					<th>工号</th>
					<th>姓名</th>
					<th style="border-left: 1px solid #ccc;">姓名</th>
					<th>职位</th>
					<th>工作年限</th>
					<th>联系电话</th>
					<th>联系邮箱</th>
					<th style="border-left: 1px solid #ccc;">审核状态</th>
					<th>举荐时间</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="entity" items="${page.content }">
				<tr class=""><td colspan="10" style="padding: 2px; background-color: #eee;" ></td></tr>
				<tr>
					<td>${entity.empno }</td>
					<td>${entity.staff.name }</td>
					<td style="border-left: 1px solid #ccc;">${entity.name }</td>
					<td>${entity.position }</td>
					<td>${entity.workingYears }</td>
					<td>${entity.mobile }</td>
					<td>${entity.email }</td>
					<td style="border-left: 1px solid #ccc;"><span class="label label-success"><dic:text group="<%=ContextConstants.GROUP.RECOMMEND_AUDIT_STATUS %>" value="${entity.auditStatus }"></dic:text></span></td>
					<td><fmt:formatDate value="${entity.create }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td rowspan="3">
<%-- 						<button class="btn btn-success btn-xs" title="详情" onclick="javascript:jumpentity('detail/${entity.id}<%=version %>');"><i class="icon-search"></i> 详情</button> --%>
						<button class="btn btn-primary btn-xs" title="修改" onclick="javascript:jumpentity('modify/${entity.id}<%=version %>');"><i class="icon-pencil"></i> 修改</button>
<%-- 						<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${entity.id}<%=version %>');"><i class="icon-trash"></i> 删除</button> --%>
					</td>
				</tr>
				<tr>
					<td colspan="2"><strong>对被举荐人的描述: </strong></td><td colspan="7" style="text-align: left; white-space: normal; word-break: break-word;">${entity.description }</td>
				</tr>
				<tr>
					<td colspan="2"><strong>审核描述: </strong></td><td colspan="7" style="text-align: left; white-space: normal; word-break: break-word;">${entity.auditRemark }</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
				<tr class=""><td colspan="10" style="padding: 2px; background-color: #eee;" ></td></tr>
				<tr>
					<td colspan="10">
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

<div id="foot" style="margin-bottom: 80px;"></div>
</body>
</html>