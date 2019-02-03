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
								<label>标题 <small>title</small>:</label>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type='text' name='title' id='title' value='${query.title }' autocomplete="off" />
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
					<th>所属模板</th>
					<th>图片</th>
					<th>标题</th>
					<th>链接地址</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="entity" items="${page.content }">
				<tr >
					<td>${entity.template.name } [ <dic:text group="<%=ContextConstants.GROUP.MESSAGE_TEMPLATE_TYPE %>" value="${entity.template.type }"></dic:text> ]</td>
					<td>
						<c:if test="${ not empty entity.img }">
							<img alt="logo" id="imgsrc" src="${entity.img }" width="64px" height="30px;">
						</c:if>
					</td>
					<td>${entity.title }</td>
					<td style="white-space: normal; word-break: break-word; text-align: left;">${entity.url }</td>
					<td>
						<button class="btn btn-primary btn-xs" title="修改" onclick="javascript:jumpentity('modify/${entity.id}<%=version %>');"><i class="icon-pencil"></i> 修改</button>
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

<div id="foot" style="margin-bottom: 80px;"></div>
</body>
</html>