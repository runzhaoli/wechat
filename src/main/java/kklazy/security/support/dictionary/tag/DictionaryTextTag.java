/**
 * 
 */
package kklazy.security.support.dictionary.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import kklazy.persistence.utils.StringUtils;
import kklazy.security.model.Dictionary;
import kklazy.security.support.dictionary.utils.DictionaryUtils;

/**
 * @author Kui
 */
public class DictionaryTextTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275491729438505933L;

	private String group;
	private String code;
	private String value;
	private String showcode = "true";	// 是否同时显示 name / code

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {

		Dictionary dictionary = null;
		
		if (StringUtils.isNotBlank(getCode())) {
			dictionary = DictionaryUtils.getDictionaryByCode(getGroup(), getCode());
		}
		if (StringUtils.isNotBlank(getValue())) {
			dictionary = DictionaryUtils.getDictionaryByValue(getGroup(), getValue());
		}
		
		JspWriter out = pageContext.getOut();
		
		try {
			if (dictionary != null) {
				if (Boolean.valueOf(getShowcode())) {
					out.print(dictionary.getName() + " / " + dictionary.getCode());
				} else {
					out.print(dictionary.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
		return super.doEndTag();
	}
	
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}
	
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the showcode
	 */
	public String getShowcode() {
		return showcode;
	}

	/**
	 * @param showcode the showcode to set
	 */
	public void setShowcode(String showcode) {
		this.showcode = showcode;
	}
	
}
