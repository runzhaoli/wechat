<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<link type="text/css" rel="stylesheet" href="<%=path %>/style/icheck/skins/all.css<%=version %>">
	<script type="text/javascript" src="<%=path %>/script/icheck/jquery.icheck.min.js<%=version %>"></script>
	<title>投诉与建议</title>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#commit").click(function() {
			if (!validation()) {
				setTimeout(function () {
					$(":input").removeAttr("readonly");
					$("#empno").attr("readonly", "readonly");
					$("#name").attr("readonly", "readonly");
					$(":button,:submit").attr("disabled", null);
					$('[data-toggle=popover]').popover('show');
				}, 500);
				return ;
			}
			$("#complaintproposalform").submit();
		});
		$('#anonymous').iCheck({
			checkboxClass: 'icheckbox_minimal',
			radioClass: 'iradio_minimal',
			increaseArea: '20%' // optional
		});
		$('#anonymous').on('ifChecked', function() {
			$('#user').hide();
		});
		$('#anonymous').on('ifUnchecked', function() {
			$('#user').show();
		});
	});
	</script>
	<style type="text/css">
	.remeber { border: 0px; background-color: #fafafa; margin-top: 20px; }
	.remeber #checkbox { width: 30px; float: left; }
	</style>
</head>
<body>
<div class="app has-navbar-top has-navbar-bottom">

	<div class="navbar navbar-app navbar-absolute-top"></div>
	<div class="navbar navbar-app navbar-absolute-bottom"><%@ include file="../common/menu.jsp" %></div>

	<div class="app-body">
		<div class="app-content">
			<div class="scrollable">
				<div class="scrollable-content">
					<div class="container">
						<div class="page-header">
							<h3>投诉与建议<br /><small>Complaint And Proposal</small></h3>
						</div>
						<c:if test="${message != null }"><div class="alert alert-danger" role="alert">${message }</div></c:if>
						<form id="complaintproposalform" method="post" action="<%=MappingConstants.UrlMapping.COMPLAINTPROPOSAL_SAVE %>" onsubmit="submit.disabled = 1;" >
							<div class="ipt remeber" style="margin-bottom: 10px;">
								<div id="checkbox"><input id="anonymous" name="anonymous" type="checkbox" /></div>
								<div><label for="anonymous" style="font-weight: normal;">匿名</label></div>
								<span class="alert-danger" style="word-break: break-all; word-wrap:break-word;">提示：如勾选匿名提交，将会在提交时清除您的用户信息，您将无法在投诉与建议详情中查看到本次记录！</span>
							</div>
							<div id="user">
								<div class="form-group">
									<label for="empno">工号 Empno</label>
									<input type="text" class="form-control" id="empno" value="${user.empno }" readonly placeholder="Empno" maxlength="32" />
								</div>
								<div class="form-group">
									<label for="name">姓名 Name</label>
									<input type="text" class="form-control" id="name" value="${user.name }" readonly placeholder="Name" maxlength="32" />
								</div>
							</div>
							<div class="form-group">
								<label for="identity">投诉或建议类型 Type</label>
								<dic:select clazz="form-control" name="type" id="type" group="<%=ContextConstants.GROUP.COMPLAINT_PROPOSAL_TYPE %>" showcode="false" validation='required' dataplacement='top'></dic:select>
							</div>
							<div class="form-group">
								<label for="description">投诉或建议描述 Description</label>
								<textarea rows="5" class="form-control" name="description" id="description" placeholder="Description" validation='required' data-placement='top' maxlength="1024" ></textarea>
							</div>
							<button type="button" id="commit" class="btn btn-default">提交</button>
						</form>
					</div>
					<%@ include file="../common/copyright.jsp" %>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
</html>
