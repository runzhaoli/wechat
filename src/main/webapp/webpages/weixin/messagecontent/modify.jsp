<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/jquery/ajaxfileupload.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/My97DatePicker/WdatePicker.js<%=version %>"></script>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	$("#uploadfile").change(function() {
		uploadimg();
	});
});

function uploadimg() {
	var path = "<%=path %>";
	var filepath = $("#uploadfile").val();
	if (filepath == null || filepath == "") {
		return false;
	}
	$.ajaxFileUpload({
		url : '<%=path %>/console/upload<%=version %>',
		type : 'post',
		secureuri : false,					// 一般设置为false
		fileElementId : 'uploadfile',		// 上传文件的id、name属性名
		dataType : 'json',					// 返回值类型，一般设置为json、application/json
		success : function(data, status) {
			if (data['result']) {
				$("#img").val(data['remote_path']);
				$("#imgurl").val(data['remote_path']);
				$("#imgsrc").attr("src", "");
				$("#imgsrc").attr("src", data['remote_path']);
			}
			$("#uploadfile").replaceWith('<input type="file" id="uploadfile" name="uploadfile" value="" />');
			$("#uploadfile").change(function() {
				uploadimg();
			});
		},
		error : function(data, status, e) {
			alert("图片上传失败：" + e);
			$("#uploadfile").replaceWith('<input type="file" id="uploadfile" name="uploadfile" value="" />');
			$("#uploadfile").change(function() {
				uploadimg();
			});
		}
	});
}
</script>
<style type="text/css">
.input-group:hover, .input-group input:hover { cursor: pointer; }
</style>
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
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>消息标题 title:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="title" value="${entity.title }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>所属消息模板 template:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<select name="template.id" validation='required' data-placement='right'>
								<option value="">请选择所属的消息模板</option>
							<c:forEach var="t" items="${templates }">
								<option value="${t.id }" <c:if test="${entity.template.id == t.id }">selected=selected</c:if> >${t.name } [ <dic:text group="<%=ContextConstants.GROUP.MESSAGE_TEMPLATE_TYPE %>" value="${t.type }"></dic:text> ]</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>消息图片 image:<br/><small>图片大小: [ 360 x 200 ]</small></label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<div style="float: left; width: 30%; height: 66px; text-align: center;">
							<img alt="logo" id="imgsrc" src="${entity.img }" width="90px" height="50px;">
						</div>
						<div style="float: right; width: 70%">
							<input type='text' id="imgurl" value="${entity.img }" readonly="readonly" data-placement='right' />
							<input type="hidden" id="img" name="img" value="${entity.img }" />
							<br />
							<input type='file' id="uploadfile" name="uploadfile" value="" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>消息内容 description:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<textarea rows="10" cols="100%" name="description" validation='required' data-placement='right' >${entity.description }</textarea>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>链接地址 url:</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<textarea rows="5" cols="100%" name="url" validation='required' data-placement='right' >${entity.url }</textarea>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="foot" style="margin-bottom: 80px;"></div>
	
</div>
</body>
</html>