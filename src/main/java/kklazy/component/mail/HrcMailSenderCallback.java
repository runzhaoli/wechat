/**
 * 
 */
package kklazy.component.mail;

import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 发送邮件回调方法
 * 
 * @author kk
 */
public interface HrcMailSenderCallback {

	/**
	 * 发送邮件
	 * 
	 * @param messageHelp
	 */
	public void sender(MimeMessageHelper messageHelp);
	
}
