package kklazy.component.mail;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.MimeMessageHelper;

public class Test {
public static void main(String[] args) {

		HrcMailSender.sender(new HrcMailSenderCallback() {
			@Override
			public void sender(MimeMessageHelper messageHelp) {
				try {

//					messageHelp.setTo("yiping.wang@newtouch.cn");
//					messageHelp.setTo("xiaohong.li@newtouch.cn");
//					messageHelp.setTo("chengjuan.wang@newtouch.cn");
//					messageHelp.setTo("chenghao.wang@newtouch.cn");

					String[] to = null;
						// 贵州、重庆 的无法区分归属分公司,各个分公司都发送邮件
						to = new String[]{"kui.jiang@newtouch.cn", "tao.tao@newtouch.cn", "dan.zhou@newtouch.cn", "kui.jiang1@newtouch.cn"};
					messageHelp.setTo(to);

					messageHelp.setSubject("TEST 我要开证明");
					StringBuilder builder = new StringBuilder();

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
