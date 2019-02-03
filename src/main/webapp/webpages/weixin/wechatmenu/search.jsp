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
$(document).ready(function() {
	$("#sync").click(function() {
		$(":button,:submit").attr("disabled", "disabled");
		$('#syncmenu').modal('show');
		$.ajax({
			type : 'POST',
			async : true,
			url : "<%=path %>/wechatmenu/sync<%=version %>",
			dataType : "json",
			success : function(data) {
				syncmenucallback(data['result'], data['message']);
			},
			error : function(data, status, e) {
				syncmenucallback(false, "同步菜单失败：" + e);
			}
		});
	});
});
function syncmenucallback(result, message) {
	$("#syncmessagecontext").html(message);
	$(":button,:submit").attr("disabled", null);
	if (result) {
		setTimeout(function () {
			$("#query #commit").click();
			parent.kklazy._show_loading();
		}, 2000);
	}
}
</script>
<title></title>
</head>
<body>
<div class="container">

	<div class="head">
		<div class="row">
			<%@ include file="../../common/default-pagetitle.jsp"%>
			<div class="col-sm-6 col-xs-12">
				<div class="right-icon-btn">
					<button class="icon-btn" id="search">
						<i class="icon-check"></i>
						<div>查询</div>
					</button>
					<button class="icon-btn" id="sync">
						<i class="icon-copy"></i>
						<div>同步菜单</div>
					</button>
					<button class="icon-btn" id="create">
						<i class="icon-plus"></i>
						<div>新增</div>
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
								<label>名称 <small>name</small>:</label>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input type='text' name='title' id='title' value='${query.name }' autocomplete="off" />
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

	<div class="modal fade" id="syncmenu" tabindex="-1" role="dialog" aria-labelledby="queryLabel" aria-hidden="true">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="queryLabel"><i class="icon-copy"></i>同步菜单<small>Sync</small></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<label>正在将菜单信息同步至微信服务器上,请稍后...</label>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<label id="syncmessagecontext" style="color: red;"></label>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="data table-responsive">
		<table class="table table-hover ">
			<thead>
				<tr>
					<th>关联的企业号ID</th>
					<th>菜单名称</th>
					<th>菜单类型</th>
					<th>菜单KEY</th>
					<th>菜单URL(适用菜单类型[view])</th>
					<th>关联的消息模板</th>
					<th>排序</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="entity" items="${page.content }">
				<tr>
					<td>${entity.accountId }</td>
					<td>${entity.name }</td>
					<td><dic:text group="<%=ContextConstants.GROUP.WECHAT_MENU_TYPE %>" value="${entity.type }"></dic:text></td>
					<td>${entity.key }</td>
					<td style="white-space: normal; word-break: break-word; text-align: left;">${entity.url }</td>
					<td>${entity.template.name } [ <dic:text group="<%=ContextConstants.GROUP.MESSAGE_TEMPLATE_TYPE %>" value="${entity.template.type }"></dic:text> ]</td>
					<td>${entity.sort }</td>
					<td>
						<button class="btn btn-default btn-xs" title="新增" onclick="javascript:jumpentity('create/${entity.id}<%=version %>');"><i class="icon-plus"></i> 新增</button>
						<button class="btn btn-primary btn-xs" title="修改" onclick="javascript:jumpentity('modify/${entity.id}<%=version %>');"><i class="icon-pencil"></i> 修改</button>
<%-- 						<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${entity.id}<%=version %>');"><i class="icon-trash"></i> 删除</button> --%>
					</td>
				</tr>
				<c:if test="${ not empty entity.sublevel }">
				<tr>
					<td colspan="7" style="padding: 0px;">
						<table class="table voteoption">
							<thead>
								<tr>
									<th style="width: 20%;"></th>
									<th>子菜单名称</th>
									<th>子菜单类型</th>
									<th>子菜单KEY</th>
									<th>子菜单URL(适用view菜单)</th>
									<th>关联的消息模板</th>
									<th>排序</th>
									<th>#</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="sublevel" items="${m.sublevel }">
								<tr>
									<td></td>
									<td>${sublevel.name }</td>
									<td><dic:text group="<%=ContextConstants.GROUP.WECHAT_MENU_TYPE %>" value="${sublevel.type }"></dic:text></td>
									<td>${sublevel.key }</td>
									<td>${sublevel.url }</td>
									<td>${sublevel.template.name } [ <dic:text group="<%=ContextConstants.GROUP.MESSAGE_TEMPLATE_TYPE %>" value="${sublevel.template.type }"></dic:text> ]</td>
									<td>${sublevel.sort }</td>
									<td>
										<button class="btn btn-primary btn-xs" title="修改" onclick="javascript:jumpentity('modify/${sublevel.id}<%=version %>');"><i class="icon-pencil"></i> 修改</button>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				</c:if>
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