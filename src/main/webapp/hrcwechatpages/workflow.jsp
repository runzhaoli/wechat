<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<%@ include file="common/path.jsp" %>
	<title>E-HR 系统工作流操作方法</title>
	<style type="text/css">
	.panel-default { padding-top: 20px; }
	.d-name {
		text-align: center;
		font-size: 2.5em;
		margin-top: -40px;
		margin-bottom: 5px;
		filter:alpha(opacity=50); /* IE */
		-moz-opacity:0.50; /* FireFox */
		opacity:0.50; /* Chrome, Opera, Safari */
	}
	.d-duty {
		text-align: center;
	}
	.d-contact {
		text-align: center;
	}
	</style>
</head>
<body>
<div class="app has-navbar-top has-navbar-bottom">
	
	<div class="navbar navbar-app navbar-absolute-top"></div>
	<div class="navbar navbar-app navbar-absolute-bottom"><%@ include file="common/menu.jsp" %></div>
	
	<div class="app-body">
		<div class="app-content">
			<div class="scrollable">
				<div class="scrollable-content">
					
					<div class="container">
						<div class="page-header">
							<h3>E-HR 系统工作流操作方法<br /><small>Show</small></h3>
						</div>
						<div style="margin: 2px;"></div>
						<div class="panel panel-success">
							<div class="panel-body">
								<p class="text-danger" style="word-wrap: break-word; word-break: break-all;">E-HR系统登录网址：（账号：员工工号，初始密码：身份证后六位）</p>
								<p class="text-danger" style="word-wrap: break-word; word-break: break-all;">内网用户（上海总部办公员工）请登录：http://192.168.9.9/newtouch/Loginx.aspx</p>
								<p class="text-danger" style="word-wrap: break-word; word-break: break-all;">外网用户请登录：http://eds.newtouch.cn:8080/newtouch/loginx.aspx</p>
							</div>
						</div>
						<h4 style="margin-bottom: 10px;">一、入职申请</h4>
						<p class="text-muted">1、HRBP登录E-HR系统->快速流程->入职审批->录入新员工信息->发起申请</p>
						<p class="text-muted">2、HRC将收到工作流邮件-入职审批->审核招聘系统操作情况、新员工入职信息并录入新员工工号，【拒绝->流程结束，同意->进入下一审批节点】</p>
						<p class="text-muted">3、事业部总经理收到工作流邮件-入职审批，【拒绝->流程结束，同意->进入下一审批节点】</p>
						<p class="text-muted">4、行业副总裁收到工作流邮件-入职审批，【拒绝->流程结束，同意->进入下一审批节点】</p>
						<p class="text-muted">5、行业执行总裁收到工作流邮件-入职审批，【拒绝->流程结束，同意->进入下一审批节点】</p>
						<p class="text-muted">6、CHO收到工作流邮件-入职审批，【拒绝->流程结束，同意->审批流程完成】</p>
						<p class="text-muted">7、入职审批流程全部完成后，E-HR系统发送新员工报到通知，HRBP发放offer并将新员工入职材料提交至HRC王成娟处（EHR入职审批表、简历、面试评估表、学历验证）</p>

						<h4 style="margin-bottom: 10px;">二、试用转正申请</h4>
						<p class="text-muted">1、转正流程发起</p>
						<p class="text-muted">E-HR系统在每月1号邮件提示行政助理当月试用期结束的员工名单，行政助理需要对本部门试用期员工的考评节点进行维护。</p>
						<p class="text-muted">路径如下：登录E-HR系统->快速流程->试用转正申请->维护员工的转正考评节点人员->发起申请</p>

						<p class="text-muted">考评节点人员说明：</p>
						<ul class="list-unstyled">
							<li class="text-muted">(1) 一级审批人：即直属上级，通常为员工所在项目的项目负责人</p></li>
							<li class="text-muted">(2) 二级审批人：即主管，通常为员工所属部门的事业部总经理</p></li>
							<li class="text-muted">(3) 三级审批人设定规则如下：</p>
								<ul class="list-unstyled">
									<li class="text-muted">1-3级的员工，三级考评节点为事业部总经理</p></li>
									<li class="text-muted">4-6级的员工，三级考评节点为行业副总裁</p></li>
									<li class="text-muted">7级及以上员工，三级考评节点为行业执行总裁</p></li>
								</ul>
							</li>
						</ul>

						<p class="text-muted">2、员工填写转正考评相关信息</p>
						<p class="text-muted">行政助理发起转正流程后，员工收到系统邮件提示，填写相关考评信息。</p>
						<p class="text-muted">路径如下：登录E-HR系统->完成转正考评表填写->提交</p>
						<p class="text-muted">3、考评审批</p>
						<p class="text-muted">员工提交考评信息后，各考评节点审批人依次收到系统邮件提示，审批相关考评内容。</p>
						<p class="text-muted">(1) 一、二级审批人收到邮件提示后，登录E-HR系统，对员工进行评分，并审批是否同意员工转正</p>
						<p class="text-muted">(2) 三级审批人收到邮件提示后，可以邮件快速审批，也可以登录E-HR系统查看员工转正考评信息后进行审批【同意->员工转正考评完成；拒绝->流程结束】</p>

						<h4 style="margin-bottom: 10px;">三、借调申请</h4>
						<p class="text-muted">1、员工借调申请：</p>
						<p class="text-muted">部门行政助理登录E-HR系统->快速流程->借调申请->发起申请</p>
						<p class="text-muted">2、员工借调审批：</p>
						<p class="text-muted">各节点审批人收到邮件提示，并于E-HR系统中进行审批</p>
						<p class="text-muted">3、员工借调执行：</p>
						<p class="text-muted">人力资源中心收到员工借调审批完成的通知后，将于当月10号之后于系统中调整员工所属部门信息，该借调于调动起始日正式生效，调动起始日原则上为每月一号</p>

						<h4 style="margin-bottom: 10px;">四、考勤扣款更改申请</h4>
						<p class="text-muted">该申请用于员工考勤扣款更改，具体操作如下：</p>
						<p class="text-muted">1、员工申请</p>
						<p class="text-muted">员工本人登录E-HR系统->快速流程->考勤扣款更改申请->发起申请</p>
						<p class="text-muted">申请界面需填写以下两项内容：</p>
						<p class="text-muted">（1）申请考勤扣款更改的理由：请写清楚需要更改的日期和原由，超过一天的请逐日填写</p>
						<p class="text-muted">（2）申请考勤扣款更改的金额：请与E-HR系统工资单中的薪酬负责人确认金额后填写</p>
						<p class="text-muted">2、负责人审批</p>
						<p class="text-muted">各节点审批人将收到邮件提示，在E-HR系统或邮件中进行审批（审批流程图见附件一）</p>
						<p class="text-muted">3、HRC执行</p>
						<p class="text-muted">人力资源中心收到审批完成的考勤扣款更改申请表后，将于次月10日进行补款操作</p>

						<h4 style="margin-bottom: 10px;">五、日志扣款撤销申请</h4>
						<p class="text-muted">该申请用于员工日志扣款撤销，具体操作如下：</p>
						<p class="text-muted">1、员工申请</p>
						<p class="text-muted">员工本人登录E-HR系统->快速流程->日志扣款撤销申请->发起申请</p>
						<p class="text-muted">申请界面需填写以下三项内容：</p>
						<p class="text-muted">（1）申请类别：a、日志不及时填写扣款；b、交付人员非项目日志扣款；c、其他（请在说明栏补充）</p>
						<p class="text-muted">（2）申请日志扣款撤销的理由：请根据项目情况如实填写。</p>
						<p class="text-muted">（3）申请日志扣款撤销的金额：请与E-HR系统工资单中的薪酬负责人确认金额后填写</p>
						<p class="text-muted">2、负责人审批</p>
						<p class="text-muted">各节点审批人将收到邮件提示，并于E-HR系统或邮件中进行审批（审批流程图见附件二）</p>
						<p class="text-muted">3、HRC执行</p>
						<p class="text-muted">人力资源中心收到审批完成的日志扣款撤销申请表后，将于次月10日进行补款操作</p>

						<h4 style="margin-bottom: 10px;">六、考勤异常申请</h4>
						<p class="text-muted">1、异常申请（休假、公出、出差、迟到等各类假种的申请）、异常撤销（异常考勤已申请并审批结束的可以取消）    </p>
						<p class="text-muted">1.1 异常申请操作流程：登录E-HR系统->流程申请->申请流程->异常申请->填写相关异常申请信息->发起申请</p>
						<p class="text-muted">1.2 异常撤销操作流程：登录E-HR系统->考勤管理->异常申请撤销->勾选撤销记录后点击【撤销】按钮进行撤销</p>
						<p class="text-muted">2、项目休假申请及审批</p>
						<p class="text-muted">操作流程：登录E-HR系统->考勤管理->项目休假_申请->点击【增加项目休假_申请】按钮->填写相关申请信息->点击【提交】按键进行提交</p>
						<p class="text-muted">3、项目休假、餐补、外派申请由部门行政助理统一申请</p>

						<h4 style="margin-bottom: 10px;">七、离职申请  </h4>
						<p class="text-muted">1、离职申请：</p>
						<p class="text-muted">提出离职申请的员工本人登录E-HR系统->快速流程->离职申请->发起申请</p>
						<p class="text-muted">2、离职审批：</p>
						<p class="text-muted">各节点审批人收到系统邮件提示，并于E-HR系统中进行审批</p>
						<p class="text-muted">3、离职办理：</p>
						<p class="text-muted">离职审批全部完成后，申请离职的员工本人应携带好电脑及相关设备，于最后工作日后前往人力资源中心办理离职手续</p>

					</div>

					<%@ include file="common/copyright.jsp" %>
					
				</div>
			</div>
		</div>
	</div>
		
</div>
</body>
</html>
						