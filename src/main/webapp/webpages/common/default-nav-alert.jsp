<%@ page language="java" import="kklazy.utils.*, kklazy.persistence.utils.*, kklazy.common.constants.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" buffer="512kb" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% String path = request.getContextPath(), version = "?v=" + StringUtils.random(); %>
<div class="nav">
	<ol class="breadcrumb">
		<li><i class="icon-home"></i><a href="javascript:void(0);" onclick="javascript:parent.kklazy._show_loading();parent.kklazy._jump('<%=path %>/console/center<%=version %>')">首页</a></li>
		<c:if test="${ not empty resource.parent.parent.name }">
		<li><a href="javascript:void(0);">${resource.parent.parent.name }</a></li>
		</c:if>
		<li><a href="javascript:void(0);">${resource.parent.name }</a></li>
		<li class="active">${resource.name }</li>
	</ol>
</div>
<c:if test="${!fn:containsIgnoreCase(resource.url, 'search') }">
<div class='bs-callout bs-callout-info fade in'>
	<label style="padding: 8px; font-size: 16px; font-variant: small-caps;">
		<i class='icon-edit'></i>
		<c:if test="${ not empty resource.parent.parent.code }">${resource.parent.parent.code } , </c:if>
		<c:if test="${ not empty resource.parent.code }">${resource.parent.code } , </c:if>
		<c:if test="${ not empty resource.code }">${resource.code } , </c:if>
		date time<span style="font-size: 12px;"> : <%=DateUtils.currentString() %></span>
	</label>
</div>
</c:if>
<div class="modal fade bs-example-modal-sm" id="alertsuccess" tabindex="-1" role="dialog" aria-labelledby="exitLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm" >
		<div class="modal-content">
			<div class="alert alert-success alert-dismissible" role="alert">
				<span><i class="icon-ok-circle"></i></span> <span id="message"></span>
			</div>
		</div>
	</div>
</div>
<div class="modal fade bs-example-modal-sm" id="alerterror" tabindex="-1" role="dialog" aria-labelledby="exitLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm" >
		<div class="modal-content">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<span><i class="icon-info-sign"></i></span> <span id="message"></span>
			</div>
		</div>
	</div>
</div>
<form id="uploadForm" action="" method="post">
	<div class="modal fade" id="upload" tabindex="-1" role="dialog" aria-labelledby="uploadLabel" aria-hidden="true">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="queryLabel"><i class="icon-upload-alt"></i>上传数据<small>upload Excel</small></h4>
				</div>
				<div class="modal-body">
					<div class="row" id="context">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
							<label>选择要上传的文件 <small>file</small>:</label>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<input type='file' name='file' id='file' />
						</div>
					</div>
					<div class="row" id="message" style="display: none;">
						<div class="col-xs-12">
							<label id="messagecontext" style="color: red;"></label>
						</div>
					</div>
					<div class="row" id="loading" style="display: none;">
						<div class="col-xs-12">
							<label>文件正在上传,请稍后...</label>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="commit" class="btn btn-default" >上传 upload</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭 close</button>
				</div>
			</div>
		</div>
	</div>
</form>