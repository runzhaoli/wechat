<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="../../common/default-path.jsp" %>
<link type="text/css" rel="stylesheet" href="<%=path %>/umeditor/themes/default/css/umeditor.min.css<%=version %>" />
<script type="text/javascript" charset="utf-8" src="<%=path %>/umeditor/umeditor.config.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/umeditor/umeditor.min.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/umeditor/lang/zh-cn/zh-cn.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/jquery/ajaxfileupload.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/My97DatePicker/WdatePicker.js<%=version %>"></script>
<link type="text/css" rel="stylesheet" href="<%=path %>/style/icheck/skins/all.css<%=version %>" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/icheck/jquery.icheck.min.js<%=version %>"></script>
<jsp:include flush="true" page="../../common/default-operation-js.jsp">
	<jsp:param value="${namespace }commit" name="commiturl"/>
	<jsp:param value="${namespace }search" name="searchurl"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	$("#uploadfile").change(function() {
		uploadimg();
	});
	$("#commitcontext").click(function() {
		$("#content").val(um.getContent());
		modifyformcommit();
	});
<%-- 	$.getStyle('<%=path %>/umeditor/common.css<%=version %>'); --%>
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
.icheckbox_minimal { float: left; }
.input-group:hover, .input-group input:hover { cursor: pointer; }
.edui-editor-body .edui-body-container { font-variant: normal; }
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
					<button class="icon-btn" id="commitcontext">
						<i class="icon-check"></i>
						<div>保存</div>
					</button>
					<button class="icon-btn" id="historyback">
						<i class="icon-undo"></i>
						<div>取消</div>
					</button>
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
						<label><span class='required'>*</span>文章标题 title</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="title" value="${entity.title }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>文章类型 type</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<dic:select group="<%=ContextConstants.GROUP.ARTICLE_TYPE %>" name="type" value="${entity.type }" validation='required' dataplacement='right'></dic:select>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label><span class='required'>*</span>文章简介 description</label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<input type='text' name="description" value="${entity.description }" validation='required' data-placement='right' />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>文章显示图片 logo<br/><small>[图片大小: 128 x 128]</small></label>
					</div>
					<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
						<div style="float: left; width: 30%; height: 66px; text-align: center;">
							<img alt="logo" id="imgsrc" src="${entity.img }" width="64px" height="64px;">
						</div>
						<div style="float: right; width: 70%">
							<input type='text' id="imgurl" value="${entity.img }" readonly="readonly" data-placement='right' />
							<input type="hidden" id="img" name="img" value="${entity.img }" />
							<br />
							<input type='file' id="uploadfile" name="uploadfile" value="" data-placement='right' />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
						<label>文章内容 content</label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-9 col-md-9 col-lg-8" style="padding-left: 12px;">
						<script type="text/plain" id="myEditor" style="width:1000px;height:240px;">
						<p>${entity.content }</p>
						</script>
						<input type="hidden" id="content" name="content" value="" data-placement='right' />
					</div>
				</div>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		//实例化编辑器
		var um = UM.getEditor('myEditor');
		um.setWidth("100%");
		$(".edui-body-container").css("width", "98%");
	</script>

	<div id="foot" style="margin-bottom: 80px;"></div>

</div>
</body>
</html>