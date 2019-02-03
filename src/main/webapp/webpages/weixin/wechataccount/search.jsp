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
		<table class="table ">
			<thead>
				<tr>
					<th colspan="2">ID</th>
					<th>名称</th>
					<th>类型</th>
					<th>联系人邮箱</th>
					<th>描述</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="entity" items="${page.content }">
				<tr class=""><td colspan="7" style="padding: 2px; background-color: #eee;" ></td></tr>
				<tr>
					<td colspan="2">${entity.id }</td>
					<td>${entity.name }</td>
					<td><span class="label label-warning"><dic:text group="<%=ContextConstants.GROUP.WECHAT_ACCOUNT_TYPE %>" value="${entity.type }" ></dic:text></span></td>
					<td>${entity.email }</td>
					<td>${entity.description }</td>
					<td rowspan="6" style="white-space: normal; word-break: break-word;">
<%-- 						<button class="btn btn-success btn-xs" title="查看详情" onclick="javascript:jumpentity('detail/${entity.id}<%=version %>');"><i class="icon-search"></i> 详情</button> --%>
						<button class="btn btn-primary btn-xs" title="修改" onclick="javascript:jumpentity('modify/${entity.id}<%=version %>');"><i class="icon-pencil"></i> 修改</button>
<%-- 						<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${entity.id}<%=version %>');"><i class="icon-trash"></i> 删除</button> --%>
					</td>
				</tr>
				<tr>
					<td><span class="label label-success">企业号</span></td>
					<td><strong>CORPID</strong></td>
					<td>${entity.corpId }</td>
					<th>AGENTID</th>
					<td>${entity.agentid }</td>
					<td></td>
				</tr>
				<tr>
					<td><span class="label label-success">企业号</span></td>
					<td><strong>ENCODINGAESKEY</strong></td>
					<td colspan="4">${entity.encodingAesKey }</td>
				</tr>
				<tr>
					<td><span class="label label-danger">企业号、服务号</span></td>
					<td><strong>APPSECRET</strong></td>
					<td colspan="4">${entity.appsecret }</td>
				</tr>
				<tr>
					<td><span class="label label-danger">企业号、服务号</span></td>
					<td><strong>TOKEN</strong></td>
					<td>${entity.token }</td>
					<td><span class="label label-info">服务号</span></td>
					<td><strong>APPID</strong></td>
					<td>${entity.appid }</td>
				</tr>
				<tr>
					<td><span class="label label-info">服务号</span></td>
					<td><strong>ACCOUNTID</strong></td>
					<td colspan="4">${entity.accountid }</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
				<tr class=""><td colspan="7" style="padding: 2px; background-color: #eee;" ></td></tr>
				<tr>
					<td colspan="7">
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