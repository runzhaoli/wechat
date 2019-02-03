<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
<script type="text/javascript">

</script>
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
					<th>工号 / 姓名</th>
					<th>区域</th>
					<th>证明类型</th>
					<th>说明</th>
					<th>申请时间</th>
					<th>接收类型</th>
					<th>电话</th>
					<th>状态</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="entity" items="${page.content }">
				<tr class=""><td colspan="9" style="padding: 2px; background-color: #eee;" ></td></tr>
				<tr class="" >
					<td>${entity.empno } / ${entity.staff.name }</td>
					<td>${entity.staff.area }</td>
					<td><span class="label <c:if test="${entity.proofType == '1' }"> label-success </c:if><c:if test="${entity.proofType != '1' }"> label-info </c:if>"><dic:text group="<%=ContextConstants.GROUP.PROOF_TYPE %>" value="${entity.proofType }"></dic:text></span></td>
					<td style="white-space: normal; word-break: break-word;">${entity.description }</td>
					<td><fmt:formatDate value="${entity.create }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><span class="label <c:if test="${entity.receiveType == '1' }"> label-danger </c:if><c:if test="${entity.receiveType != '1' }"> label-warning </c:if>"><dic:text group="<%=ContextConstants.GROUP.RECEIVE_TYPE %>" value="${entity.receiveType }"></dic:text></span></td>
					<td>${entity.mobile }</td>
					<td><dic:text group="<%=ContextConstants.GROUP.CERTIFY_AUDIT_STATUS %>" value="${entity.auditStatus }"></dic:text></td>
					<td rowspan="2">
<%-- 						<button class="btn btn-success btn-xs" title="详情" onclick="javascript:jumpentity('detail/${entity.id}<%=version %>');"><i class="icon-search"></i> 详情</button> --%>
						<button class="btn btn-primary btn-xs" title="修改" onclick="javascript:jumpentity('modify/${entity.id}<%=version %>');"><i class="icon-pencil"></i> 修改</button>
<%-- 						<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${entity.id}<%=version %>');"><i class="icon-trash"></i> 删除</button> --%>
					</td>
				</tr>
				<tr class="" >
					<td><strong>地址: </strong></td><td colspan="4" style="text-align: left;">${entity.address }</td><td><strong>快递单号: </strong></td><td colspan="2">${entity.express }</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
				<tr class=""><td colspan="9" style="padding: 2px; background-color: #eee;" ></td></tr>
				<tr>
					<td colspan="9">
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