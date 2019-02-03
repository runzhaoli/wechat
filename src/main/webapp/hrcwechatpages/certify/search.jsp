<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="../common/path.jsp" %>
	<title>我要开证明</title>
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
							<c:if test='${query.type == "1" }'><h3>在职证明开具申请<br /><small>Employment Letter</small></h3></c:if>
							<c:if test='${query.type == "2" }'><h3>收入证明开具申请<br /><small>Proof Of Income</small></h3></c:if>
						</div>
						<c:if test="${message != null }"><div class="alert alert-danger" role="alert">${message }</div></c:if>
						<form id="certfyform" method="post" action="<%=MappingConstants.UrlMapping.CERTIFY_SAVE %>" onsubmit="submit.disabled = 1;" >
							<input type="hidden" name="proofType" id="proofType" value="${query.type }" />
							<div class="form-group">
								<label for="empno">工号 Empno</label>
								<input type="text" class="form-control" name="empno" id="empno" value="${user.empno }" readonly placeholder="Empno" maxlength="32" />
							</div>
							<div class="form-group">
								<label for="name">姓名 Name</label>
								<input type="text" class="form-control" name="name" id="name" value="${user.name }" readonly placeholder="Name" maxlength="32" />
							</div>
							<div class="form-group">
								<label for="identity">身份证号码 <span class="label label-danger">十八位</span> Identity</label>
								<input type="password" class="form-control" name="identity" id="identity" value="${certify.identity }" placeholder="Identity" validation='required' data-placement='top' maxlength="32" />
							</div>
							<div class="form-group">
								<label for="description">用途描述 Description</label>
								<input type="text" class="form-control" name="description" id="description" value="${certify.description }" placeholder="Description" validation='required' data-placement='top' maxlength="128" />
							</div>
							<div class="form-group">
								<label for="receiveType">收取类型 Receive Type</label>
								<div class="radio">
									<label>
										<input type="radio" name="receiveType" id="receiveType" value="1" onclick="javascript:$('#express').show(); $('#mobile').attr('validation', 'required'); $('#address').attr('validation', 'required');" checked />
										<strong>顺丰快递</strong><br />
										<span class="alert-danger">提示：快递到付，费用由员工自行支付</span>
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="receiveType" id="receiveType" value="2"  onclick="javascript:$('#express').hide(); $('#mobile').val(''); $('#address').val(''); $('#mobile').attr('validation', ''); $('#address').attr('validation', '');" />
										<strong>公司自取</strong><br />
										<span class="alert-danger" style="word-break: break-all; word-wrap:break-word;">
										<span class="alert-info">上海区域：</span>浦东新区峨山路91弄98号陆家嘴软件园1号楼4F
										<br />请员工于3个工作日后至总部人力资源中心401-402办公室陶桃处领取，座机：021-51105660-6835，邮箱：tao.tao@newtouch.cn
										<br />
										<span class="alert-info">北京新致：</span>北京市西城区西直门成铭大厦C座13层
										<br />请员工于3个工作日后至姚伟/屈聪聪处领取，座机：010-56025253/010-56025253，邮箱：wei.yao@newtouch.cn/congcong.qu@newtouch.cn
										<br /><span class="alert-success">注：北京地区各部门事业部总经理邮件审批才可盖章！北京地区员工需自备模板，模板请提交至人事部姚伟，邮箱：wei.yao@newtouch.cn</span>
										<br />
										<span class="alert-info">大连新致：</span>大连市软件园东路21号12号楼301C室
										<br />请员工于3个工作日后至李秀萍处领取，座机：0411-84768351-803，邮箱：xiuping.li@newtouch.cn
										<br />
										<span class="alert-info">西安新致：</span>西安市高新区科技二路68号西安软件园秦风阁综合楼601室
										<br />请员工于3个工作日后至曲中星处领取，座机：029-87669728-17，邮箱：zhongxing.qu@newtouch.cn
<!-- 										提示：请员工于三个工作日后至人力资源中心401-402办公室'王祎萍 \ 陶桃'处领取<br />分机：3004 \ 6835<br />邮箱：yiping.wang@newtouch.cn \ tao.tao@newtouch.cn -->
										</span>
									</label>
								</div>
							</div>
							<div id="express">
								<div class="form-group">
									<label for="mobile">手机号码 Mobile</label>
									<input type="text" class="form-control" name="mobile" id="mobile" value="${certify.mobile }" placeholder="Mobile" validation='required' data-placement='top' maxlength="32" />
								</div>
								<div class="form-group">
									<label for="address">寄送地址 Address</label>
									<input type="text" class="form-control" name="address" id="address" value="${certify.address }" placeholder="Address" validation='required' data-placement='top' maxlength="128" />
								</div>
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
