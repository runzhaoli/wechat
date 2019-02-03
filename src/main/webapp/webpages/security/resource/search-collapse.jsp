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
					<th>#</th>
					<th>排序</th>
					<th>图标</th>
					<th>代码</th>
					<th>名称</th>
					<th>类型</th>
					<th>URL</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="entity" items="${page.content }">
				<tr class="<c:if test="${ not empty entity.sort }">success</c:if> <c:if test="${ empty entity.sort }">warning</c:if>" >
					<td>
					<c:if test="${ not empty entity.sublevel or fn:length(entity.sublevel) != 0 }">
					<a class="btn btn-default btn-xs" style="padding-left: 15px; padding-right: 15px;" role="button" data-toggle="collapse" data-parent="#accordion" href="#${entity.id }" aria-expanded="true" aria-controls="collapseOne"><small class="caret"></small></a>
					</c:if>
					</td>
					<td>${entity.sort }</td>
					<td>
						<c:if test="${ not empty entity.iconInit }"><i class="${entity.iconInit }"></i></c:if>
						<c:if test="${ empty entity.iconInit }"><i class="glyphicon glyphicon-picture"></i></c:if>
					</td>
					<td>${entity.code }</td>
					<td>${entity.name }</td>
					<td><dic:text group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${entity.type }"></dic:text></td>
					<td>${entity.url }</td>
					<td style="text-align: right;">
						<button class="btn btn-default btn-xs" title="新增" onclick="javascript:window.location.href='create/${entity.id}<%=version %>';"><i class="icon-plus"></i> 新增</button>
						<button class="btn btn-success btn-xs" title="查看详情" onclick="javascript:jumpentity('detail/${entity.id}<%=version %>');"><i class="icon-search"></i> 详情</button>
						<button class="btn btn-primary btn-xs" title="修改" onclick="javascript:jumpentity('modify/${entity.id}<%=version %>');"><i class="icon-pencil"></i> 修改</button>
						<c:if test="${ not empty entity.sort }">
						<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${entity.id}<%=version %>');"><i class="icon-trash"></i> 删除</button>
						</c:if>
					</td>
				</tr>
				<c:if test="${ not empty entity.sublevel or fn:length(entity.sublevel) != 0 }">
					<tr>
						<td class="collapse-td" colspan="8">
							<div id="${entity.id }" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
								<table class="table ">
									<tr>
										<th style="width: 40px"></th>
										<th>#</th>
										<th>排序</th>
										<th>图标</th>
										<th>代码</th>
										<th>名称</th>
										<th>类型</th>
										<th>URL</th>
										<th>#</th>
									</tr>
									<c:forEach var="sublevel" items="${entity.sublevel }">
									<c:if test="${sublevel.enabled and !sublevel.delete }">
										<tr class="<c:if test="${ not empty sublevel.sort }">active</c:if> <c:if test="${ empty entity.sort or empty sublevel.sort }">warning</c:if>" >
											<td></td>
											<td>
											<c:if test="${ not empty sublevel.sublevel or fn:length(sublevel.sublevel) != 0 }">
											<a class="btn btn-default btn-xs" style="padding-left: 15px; padding-right: 15px;" role="button" data-toggle="collapse" data-parent="#accordion" href="#${sublevel.id }" aria-expanded="true" aria-controls="collapseOne"><small class="caret"></small></a>
											</c:if>
											</td>
											<td>${sublevel.sort }</td>
											<td>
												<c:if test="${ not empty sublevel.iconInit }"><i class="${sublevel.iconInit }"></i></c:if>
												<c:if test="${ empty sublevel.iconInit }"><i class="glyphicon glyphicon-picture"></i></c:if>
											</td>
											<td>${sublevel.code }</td>
											<td>${sublevel.name }</td>
											<td><dic:text group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${sublevel.type }"></dic:text></td>
											<td>${sublevel.url }</td>
											<td style="text-align: right;">
												<c:if test="${ not empty entity.sort and not empty sublevel.sort  }">
												<button class="btn btn-default btn-xs" title="新增" onclick="javascript:jumpentity('create/${sublevel.id}<%=version %>');"><i class="icon-plus"></i> 新增</button>
												</c:if>
												<button class="btn btn-success btn-xs" title="查看详情" onclick="javascript:jumpentity('detail/${sublevel.id}<%=version %>');"><i class="icon-search"></i> 详情</button>
												<button class="btn btn-primary btn-xs" title="修改" onclick="javascript:jumpentity('modify/${sublevel.id}<%=version %>');"><i class="icon-pencil"></i> 修改</button>
												<c:if test="${ not empty entity.sort }">
												<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${sublevel.id}<%=version %>');"><i class="icon-trash"></i> 删除</button>
												</c:if>
											</td>
										</tr>
										<c:if test="${ not empty sublevel.sublevel or fn:length(sublevel.sublevel) != 0 }">
											<tr>
												<td class="collapse-td" colspan="9">
													<div id="${sublevel.id }" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
														<table class="table ">
															<tr>
																<th style="width: 100px"></th>
																<th>排序</th>
																<th>图标</th>
																<th>代码</th>
																<th>名称</th>
																<th>类型</th>
																<th>URL</th>
																<th>#</th>
															</tr>
															<c:forEach var="operation" items="${sublevel.sublevel }">
															<c:if test="${operation.enabled and !operation.delete }">
																<tr>
																	<td></td>
																	<td>${operation.sort }</td>
																	<td>
																		<c:if test="${ not empty operation.iconInit }"><i class="${operation.iconInit }"></i></c:if>
																		<c:if test="${ empty operation.iconInit }"><i class="glyphicon glyphicon-picture"></i></c:if>
																	</td>
																	<td>${operation.code }</td>
																	<td>${operation.name }</td>
																	<td><dic:text group="<%=ContextConstants.GROUP.RESOURCE_TYPE %>" value="${operation.type }"></dic:text></td>
																	<td>${operation.url }</td>
																	<td style="text-align: right;">
																		<button class="btn btn-success btn-xs" title="查看详情" onclick="javascript:jumpentity('detail/${operation.id}<%=version %>');"><i class="icon-search"></i> 详情</button>
																		<button class="btn btn-primary btn-xs" title="修改" onclick="javascript:jumpentity('modify/${operation.id}<%=version %>');"><i class="icon-pencil"></i> 修改</button>
																		<button class="btn btn-danger btn-xs"  title="删除" onclick="javascript:optionentity('delete/${operation.id}<%=version %>');"><i class="icon-trash"></i> 删除</button>
																	</td>
															</c:if>
															</c:forEach>
														</table>
													</div>
												</td>
											</tr>
										</c:if>
									</c:if>
									</c:forEach>
								</table>
							</div>
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

<div id="foot" style="margin-bottom: 150px;"></div>
</body>
</html>