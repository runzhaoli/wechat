<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>伊周聊</title>
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
			$("#certfyform").submit();
		});
	});
	</script>
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
							<img alt="" src="<%=path %>/images/wechat/yzl-logo.png" style="width: 52px; height: 52px; float: left; margin-right: 20px;" />
							<h3>伊周聊<br /><small>Chat with u</small></h3>
						</div>
						<c:if test="${message != null }"><div class="alert alert-danger" role="alert">${message }</div></c:if>
						<div class="panel panel-default">
							<div class="panel-body">
								<p style="text-indent: 20px;">也许你是毕业不久还不太适应的职场新人，也许你已经工作多年感到厌倦乏味，也许你渴望进步成长但是不得其法，也许你面临新的工作挑战压力山大，也许你在工作与生活的平衡中出现麻烦，欢迎你来“伊周聊”。</p>
								<p style="text-indent: 20px;">“伊周聊”是新致软件人力资源中心推出的员工关怀项目，关注每一位员工的状态和职业发展，“伊周聊”希望成为你可以信赖的朋友，陪伴你在新致更好地工作和发展。</p>
								<p style="text-indent: 20px;">请于下方填写预约信息，期待与你交流！</p>
							</div>
						</div>
						<form id="certfyform" method="post" action="yzlsave.kklazy" onsubmit="submit.disabled = 1;" >
							<div class="form-group">
								<label for="empno">工号 Empno</label>
								<input type="text" class="form-control" name="empno" id="empno" value="${user.empno }" readonly placeholder="Empno" maxlength="32" />
							</div>
							<div class="form-group">
								<label for="name">姓名 Name</label>
								<input type="text" class="form-control" name="name" id="name" value="${user.name }" readonly placeholder="Name" maxlength="32" />
							</div>
							<div class="form-group">
								<label for="identity">预约时间 Time</label>
								<div class="radio">
									<label>
										<input type="radio" name="chattime" id="chattime" value="1" checked />
										<span>①　周三 下午 3:00 — 4:00</span>
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="chattime" id="chattime" value="2" />
										<span>②　周四 下午 3:00 — 4:00</span>
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="description">沟通形式 Type</label>
								<div class="radio">
									<label>
										<input type="radio" name="chattype" id="chattype" value="1" checked />
										<span>①　面对面交流</span>
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="chattype" id="chattype" value="2" />
										<span>②　电话</span>
									</label>
								</div>
							</div>
							<div id="express">
								<div class="form-group">
									<label for="mobile">手机号码 Mobile</label>
									<input type="text" class="form-control" name="mobile" id="mobile" placeholder="Mobile" validation='required' data-placement='top' maxlength="32" />
								</div>
							</div>
							
							<button type="button" id="commit" class="btn btn-default">提交</button>
						</form>
						<div class="list-group" style="margin-top: 80px; text-align: center;">
							<div class="list-group-item active" >
								<div class="option">
									<button type="button" class="btn btn-default" onclick="javascript:wechatmenu.go('yzlresult.kklazy');" >查看伊周聊预约记录</button>
								</div>
							</div>
						</div>
					</div>

					<%@ include file="../common/copyright.jsp" %>
					
				</div>
			</div>
		</div>
	</div>
		
</div>
</body>
</html>
