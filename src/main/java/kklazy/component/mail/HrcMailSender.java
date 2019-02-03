/**
 * 
 */
package kklazy.component.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import kklazy.common.constants.ContextConstants;
import kklazy.component.file.model.Attachfile;
import kklazy.hrc.model.Certify;
import kklazy.hrc.model.ComplaintProposal;
import kklazy.hrc.model.Recommend;
import kklazy.hrc.model.YiZhouChat;
import kklazy.newtouch.model.Staff;
import kklazy.persistence.utils.StringUtils;
import kklazy.security.support.dictionary.utils.DictionaryUtils;

/**
 * @author kk
 *
 */
public class HrcMailSender {

	private static String HOST = "mail.newtouch.cn";
	private static String USER_NAME = "hrservice";
	private static String PASS_WORD = "password3!";
	private static String SEND_FROM = "hrservice@newtouch.cn";

	private static String MASTER = "dan.zhou@newtouch.cn";

	static void sender(HrcMailSenderCallback callback) {
		try {

			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			sender.setHost(HOST);
			sender.setUsername(USER_NAME);
			sender.setPassword(PASS_WORD);
			Properties prop = new Properties();
			prop.setProperty("mail.smtp.auth", "true");
			sender.setJavaMailProperties(prop);
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper messageHelp = new MimeMessageHelper(message, true, "GBK");
			messageHelp.setFrom(SEND_FROM);

			callback.sender(messageHelp);

			sender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 发送邮件，投诉与建议
	 * 
	 * @param proposal
	 */
	public static void send(final ComplaintProposal proposal) { 

		HrcMailSender.sender(new HrcMailSenderCallback() {
			@Override
			public void sender(MimeMessageHelper messageHelp) {
				try {

					String[] to = null;
					if (StringUtils.inCollection(proposal.getStaff().getArea(), "北京新致")) {
						to = new String[]{"congcong.qu@newtouch.cn", MASTER};
					}
					if (StringUtils.inCollection(proposal.getStaff().getArea(), "大连新致")) {
						to = new String[]{"ying.liu5@newtouch.cn", MASTER};
					}
					if (StringUtils.inCollection(proposal.getStaff().getArea(), "西安新致")) {
						to = new String[]{"zhongxing.qu@newtouch.cn", MASTER};
					}
					if (StringUtils.inCollection(proposal.getStaff().getArea(), "贵州新致", "重庆新致")) {
						// 贵州、重庆 的无法区分归属分公司,各个分公司都发送邮件
						to = new String[]{"tao.tao@newtouch.cn", "congcong.qu@newtouch.cn", "ying.liu5@newtouch.cn", "zhongxing.qu@newtouch.cn", MASTER};
					}
					if (StringUtils.inCollection(proposal.getStaff().getArea(), "上海新致", "新致信息", "新致百果")) {
						to = new String[]{MASTER};
					}
					if (to == null) {
						to = new String[]{MASTER};
					}
					messageHelp.setTo(to);

					if (proposal.getAnonymous()) {
						messageHelp.setSubject("[ 匿名， 部门: " + proposal.getStaff().getDepartment() + " ] 投诉与建议");
					} else {
						messageHelp.setSubject("[ 工号: " + proposal.getStaff().getEmpno() + "， 姓名: " + proposal.getStaff().getName() + "， 部门: " + proposal.getStaff().getDepartment() + " ] 投诉与建议");
					}

					StringBuilder builder = new StringBuilder();
					builder.append("部门：");
					builder.append(proposal.getStaff().getDepartment());
					builder.append("<br />");
					builder.append("类型：");
					builder.append(DictionaryUtils.getDictionaryByCode(ContextConstants.GROUP.COMPLAINT_PROPOSAL_TYPE, proposal.getType()).getName());
					builder.append("<br />");
					builder.append("内容：");
					builder.append(proposal.getDescription());
					builder.append("<br />");

					builder.append("<br />");
					builder.append("<br />");
					builder.append("<br />");
					builder.append("本邮件由系统自动发出，请勿回复！");

					messageHelp.setText(builder.toString(), true);

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 发送邮件，我要推荐
	 * 
	 * @param recommend
	 */
	public static void send(final Recommend recommend) {

		HrcMailSender.sender(new HrcMailSenderCallback() {
			@Override
			public void sender(MimeMessageHelper messageHelp) {
				try {

					if (recommend.getStaff() == null) {
						return ;
					}

//					messageHelp.setTo("yihui.deng@newtouch.cn");

					String[] to = null;
					if (StringUtils.inCollection(recommend.getStaff().getArea(), "北京新致")) {
						to = new String[]{"wei.wei@newtouch.cn", "qin.mu@newtouch.cn"};
					}
					if (StringUtils.inCollection(recommend.getStaff().getArea(), "大连新致")) {
						to = new String[]{"dan.wang@newtouch.cn", "guodong.wan@newtouch.cn"};
					}
					if (StringUtils.inCollection(recommend.getStaff().getArea(), "西安新致")) {
						to = new String[]{"zhongxing.qu@newtouch.cn"};
					}
					if (StringUtils.inCollection(recommend.getStaff().getArea(), "贵州新致", "重庆新致")) {
						// 贵州、重庆 的无法区分归属分公司,各个分公司都发送邮件
						to = new String[]{"tao.tao@newtouch.cn", "wei.wei@newtouch.cn", "qin.mu@newtouch.cn", "dan.wang@newtouch.cn", "guodong.wan@newtouch.cn", "zhongxing.qu@newtouch.cn"};
					}
					if (StringUtils.inCollection(recommend.getStaff().getArea(), "上海新致", "新致信息", "新致百果")) {
						to = new String[]{"xiaoqiong.yu@newtouch.cn"};
					}
					if (to == null) {
						to = new String[]{"xiaoqiong.yu@newtouch.cn"};
					}
					messageHelp.setTo(to);

					messageHelp.setSubject("[ 工号: " + recommend.getStaff().getEmpno() + "， 姓名: " + recommend.getStaff().getName() + "， 部门: " + recommend.getStaff().getDepartment() + " ] 我要推荐");

					StringBuilder builder = new StringBuilder();
					builder.append("<b>推荐人信息：</b>");
					builder.append("<br />");
					builder.append("工号：");
					builder.append(recommend.getStaff().getEmpno());
					builder.append("<br />");
					builder.append("姓名：");
					builder.append(recommend.getStaff().getName());
					builder.append("<br />");
					builder.append("部门：");
					builder.append(recommend.getStaff().getDepartment());
					builder.append("<br />");
					builder.append("邮箱：");
					builder.append(recommend.getStaff().getEmail());
					builder.append("<br />");
					builder.append("<br />");
					builder.append("<b style='color: red;'>被推荐人信息：</b>");
					builder.append("<br />");
					builder.append("被推荐人姓名：");
					builder.append(recommend.getName());
					builder.append("<br />");
					builder.append("被推荐职位：");
					builder.append(recommend.getPosition());
					builder.append("<br />");
					builder.append("被推荐人工作年限：");
					builder.append(recommend.getWorkingYears());
					builder.append("<br />");
					builder.append("被推荐人联系电话：");
					builder.append(recommend.getMobile());
					builder.append("<br />");
					builder.append("被推荐人联系邮箱：");
					builder.append(recommend.getEmail());
					builder.append("<br />");
					builder.append("<br />");
					builder.append("<b style='color: red;'>对被推荐人的描述：</b>");
					builder.append("<br />");
					builder.append(recommend.getDescription());
					builder.append("<br />");

					builder.append("<br />");
					builder.append("<br />");
					builder.append("<br />");
					builder.append("本邮件由系统自动发出，请勿回复！");

					messageHelp.setText(builder.toString(), true);

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 发送邮件，我要开证明
	 * 
	 * @param certify
	 */
	public static void send(final Certify certify) {

		HrcMailSender.sender(new HrcMailSenderCallback() {
			@Override
			public void sender(MimeMessageHelper messageHelp) {
				try {

					if (certify.getStaff() == null) {
						return ;
					}

//					messageHelp.setTo("yiping.wang@newtouch.cn");
//					messageHelp.setTo("xiaohong.li@newtouch.cn");
//					messageHelp.setTo("chengjuan.wang@newtouch.cn");
//					messageHelp.setTo("chenghao.wang@newtouch.cn");

					String[] to = null;
					if (StringUtils.inCollection(certify.getStaff().getArea(), "北京新致")) {
						to = new String[]{"na.yang@newtouch.cn"};
					}
					if (StringUtils.inCollection(certify.getStaff().getArea(), "大连新致")) {
						to = new String[]{"xiuping.li@newtouch.cn"};
					}
					if (StringUtils.inCollection(certify.getStaff().getArea(), "西安新致")) {
						to = new String[]{"zhongxing.qu@newtouch.cn"};
					}
					if (StringUtils.inCollection(certify.getStaff().getArea(), "贵州新致", "重庆新致")) {
						// 贵州、重庆 的无法区分归属分公司,各个分公司都发送邮件
						to = new String[]{"tao.tao@newtouch.cn", "na.yang@newtouch.cn", "xiuping.li@newtouch.cn", "zhongxing.qu@newtouch.cn"};
					}
					if (StringUtils.inCollection(certify.getStaff().getArea(), "上海新致", "新致信息", "新致百果")) {
						to = new String[]{"tao.tao@newtouch.cn", "taixue.chen@newtouch.cn", "yajun.chen@newtouch.cn", "juanjuan.wang@newtouch.cn"};
					}
					if (to == null) {
						to = new String[]{"tao.tao@newtouch.cn", "taixue.chen@newtouch.cn", "yajun.chen@newtouch.cn", "juanjuan.wang@newtouch.cn"};
					}
					messageHelp.setTo(to);

					messageHelp.setSubject("[ 工号: " + certify.getStaff().getEmpno() + "， 姓名: " + certify.getStaff().getName() + "， 部门: " + certify.getStaff().getDepartment() + "，区域：" + certify.getStaff().getArea() + " ] 我要开证明");
					StringBuilder builder = new StringBuilder();
					builder.append("<b style='color: red;'>证明类型：</b>");
					if ("1".equals(certify.getProofType())) {
						// 在职证明
						builder.append("在职证明");
					} else {
						// 收入证明
						builder.append("收入证明");
					}

					builder.append("<br />");
					builder.append("<br />");
					builder.append("工号：");
					builder.append(certify.getStaff().getEmpno());
					builder.append("<br />");
					builder.append("姓名：");
					builder.append(certify.getStaff().getName());
					builder.append("<br />");
					builder.append("部门：");
					builder.append(certify.getStaff().getDepartment());
					builder.append("<br />");
					builder.append("邮箱：");
					builder.append(certify.getStaff().getEmail());
					builder.append("<br />");
					builder.append("用途描述：");
					builder.append(certify.getDescription());
					builder.append("<br />");

					builder.append("<b style='color: red;'>接收类型：</b>");
					if ("1".equals(certify.getReceiveType())) {
						// 顺丰快递，快递到付
						builder.append("顺丰快递，快递到付！");
						builder.append("<br />");
						builder.append("手机号码：");
						builder.append(certify.getMobile());
						builder.append("<br />");
						builder.append("寄送地址：");
						builder.append(certify.getAddress());
						builder.append("<br />");
					} else {
						// 公司自取
						// 401-402办公室找李晓红、王成娟、王诚皓
						// 401-402办公室找王祎萍、陶桃
						builder.append("公司自取！");
						builder.append("<br />");
					}

					builder.append("<br />");
					builder.append("<br />");
					builder.append("<br />");
					builder.append("本邮件由系统自动发出，请勿回复！");

					messageHelp.setText(builder.toString(), true);

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 发送需要的文档给员工
	 * 
	 * @param attachfile
	 * @param staff
	 */
	public static void send(final Attachfile attachfile, final Staff staff) {
		HrcMailSender.sender(new HrcMailSenderCallback() {
			@Override
			public void sender(MimeMessageHelper messageHelp) {
				try {

					messageHelp.setTo(staff.getEmail());
					messageHelp.setSubject(attachfile.getName());
					messageHelp.addAttachment(attachfile.getName(), new File(attachfile.getPath()));

					StringBuilder builder = new StringBuilder();
					builder.append("请查收您需要的文档！");
					builder.append("<br />");

					builder.append("<br />");
					builder.append("<br />");
					builder.append("<br />");
					builder.append("本邮件由系统自动发出，请勿回复！");
					messageHelp.setText(builder.toString(), true);

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		});
		HrcMailSender.sender(new HrcMailSenderCallback() {
			
			@Override
			public void sender(MimeMessageHelper messageHelp) {
				try {

					if (staff != null && "全端网络".equals(staff.getArea())) {
						messageHelp.setTo("yiping.wang@newtouch.cn");
						messageHelp.setCc(new String[]{"tingting.lu1@newtouch.cn", "lingyun.shi@newtouch.cn"});
					} else {
						messageHelp.setTo("dan.zhou@newtouch.cn");
					}
					messageHelp.setSubject("[ 工号: " + staff.getEmpno() + "， 姓名: " + staff.getName() + "， 部门: " + staff.getDepartment() + " ] 预约离职");
	
					StringBuilder builder = new StringBuilder();
	
					builder.append("这个人想预约离职，你们看着办吧！");
					builder.append("<br />");
					builder.append("工号: " + staff.getEmpno());
					builder.append("<br />");
					builder.append("姓名: " + staff.getName());
					builder.append("<br />");
					builder.append("部门: " + staff.getDepartment());
					builder.append("<br />");
					builder.append("所属子公司: " + staff.getArea());
					builder.append("<br />");
					
					if ("FBF".equals(staff.getDepartment())) {
						builder.append("<br />");
						builder.append("<b style='color: red;'>系统检测到这个人是FBF的，不许帮他办手续！^o^</b>");
						builder.append("<br />");
					}

					builder.append("<br />");
					builder.append("<br />");
					builder.append("<br />");
					builder.append("本邮件由系统自动发出，请勿回复！别来联系我！");
					messageHelp.setText(builder.toString(), true);

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 伊周聊邮件
	 * 
	 * @param chat
	 */
	public static void send(final YiZhouChat chat) {

		HrcMailSender.sender(new HrcMailSenderCallback() {
			@Override
			public void sender(MimeMessageHelper messageHelp) {
				try {
					String[] to = null;
					to = new String[]{"dan.zhou@newtouch.cn", "yihan.zeng@newtouch.cn"};
					messageHelp.setTo(to);
					messageHelp.setSubject("[ 工号: " + chat.getStaff().getEmpno() + "， 姓名: " + chat.getStaff().getName() + "， 部门: " + chat.getStaff().getDepartment() + " ] 预约伊周聊");
					StringBuilder builder = new StringBuilder();
					builder.append("部门：");
					builder.append(chat.getStaff().getDepartment());
					builder.append("<br />");
					builder.append("预约时间：");
					String time = chat.getChattime();
					if (StringUtils.isNotBlank(time)) {
						if ("1".equals(time)) {
							builder.append("周三 下午 3:00 — 4:00");
						}
						if ("2".equals(time)) {
							builder.append("周四 下午 3:00 — 4:00");
						}
					}
					builder.append("<br />");
					builder.append("沟通形式：");
					String type = chat.getChattype();
					if (StringUtils.isNotBlank(type)) {
						if ("1".equals(type)) {
							builder.append("面对面交流");
						}
						if ("2".equals(type)) {
							builder.append("电话");
						}
					}
					builder.append("<br />");

					builder.append("<br />");
					builder.append("<br />");
					builder.append("<br />");
					builder.append("本邮件由系统自动发出，请勿回复！");

					messageHelp.setText(builder.toString(), true);

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
