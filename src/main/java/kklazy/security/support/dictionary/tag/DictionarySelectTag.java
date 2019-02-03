/**
 * 
 */
package kklazy.security.support.dictionary.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import kklazy.persistence.utils.StringUtils;
import kklazy.security.model.Dictionary;
import kklazy.security.support.dictionary.utils.DictionaryUtils;

/**
 * @author Kui
 */
public class DictionarySelectTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6160239605788058142L;
	
	private String id;		// id
	private String name;	// name
	private String validation;	// 是否需要校验
	private String dataplacement;	// 校验提示位置
	private String label;	// 默认选项名称
	private String key;		// 默认选项值
	private String group;	// 所属组
	private String code;	// 对应代码
	private String value;	// 对应值
	private String clazz;	// 样式，对应class
	private String style;	// 元素独有样式，对应style
	private String readonly;	// 只读
	private String disabled;	// 禁用
	private String showcode = "true";	// 是否同时显示 name / code
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		outputDictionary();
		return super.doEndTag();
	}
	
	/**
	 * 
	 * 
	 * @author Kui
	 */
	public void outputDictionary() {

		List<Dictionary> dictionaries = DictionaryUtils.getDictionaries(getGroup());
		
		JspWriter out = pageContext.getOut();
		
		try {
			out.print("<select " + getId() + getName() + getClazz() + getValidation() + getDataplacement() + getStyle() +  getReadonly() + getDisabled() + ">");
			
			if (StringUtils.isNotBlank(getLabel())) {
				out.print("<option value='" + getKey() + "'>" + getLabel() + "</option>");
			}
			
			if (dictionaries != null && !dictionaries.isEmpty()) {
				for (Dictionary dictionary : dictionaries) {
					
					if (StringUtils.isNotBlank(getValue()) && dictionary.getValue().equals(getValue())) {
						if (Boolean.valueOf(getShowcode())) {
							out.print("<option value='" + dictionary.getValue() + "' selected='true'>" + dictionary.getName() + " / " + dictionary.getCode() + "</option>"); 
						} else {
							out.print("<option value='" + dictionary.getValue() + "' selected='true'>" + dictionary.getName() + "</option>");
						}
					}
					
					else if (StringUtils.isNotBlank(getCode()) && dictionary.getCode().equals(getCode())) {
						if (Boolean.valueOf(getShowcode())) {
							out.print("<option value='" + dictionary.getValue() + "' selected='true'>" + dictionary.getName() + " / " + dictionary.getCode() + "</option>"); 
						} else {
							out.print("<option value='" + dictionary.getValue() + "' selected='true'>" + dictionary.getName() + "</option>");
						}
					}
					
					else {
						if (Boolean.valueOf(getShowcode())) {
							out.print("<option value='" + dictionary.getValue() + "'>" + dictionary.getName() + " / " + dictionary.getCode() + "</option>");
						} else {
							out.print("<option value='" + dictionary.getValue() + "'>" + dictionary.getName() + "</option>");
						}
					}
					
				}
			}
			
			out.print("</select>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return StringUtils.isNotBlank(id) ? "id='" + id + "' " : StringUtils.EMPTY;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return StringUtils.isNotBlank(name) ? "name='" + name + "' " : StringUtils.EMPTY;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the validation
	 */
	public String getValidation() {
		return StringUtils.isNotBlank(validation) ? "validation='" + validation + "' " : StringUtils.EMPTY;
	}

	/**
	 * @param validation the validation to set
	 */
	public void setValidation(String validation) {
		this.validation = validation;
	}

	/**
	 * @return the dataplacement
	 */
	public String getDataplacement() {
		return StringUtils.isNotBlank(dataplacement) ? "data-placement='" + dataplacement + "' " : StringUtils.EMPTY;
	}

	/**
	 * @param dataplacement the dataplacement to set
	 */
	public void setDataplacement(String dataplacement) {
		this.dataplacement = dataplacement;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label == null ? "" : label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key == null ? "" : key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
		return code == null ? "" : code;
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
		return value == null ? "" : value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return StringUtils.isNotBlank(clazz) ? "class='" + clazz + "' " : StringUtils.EMPTY;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		return StringUtils.isNotBlank(style) ? "style='" + style + "' " : StringUtils.EMPTY;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the readonly
	 */
	public String getReadonly() {
		return StringUtils.isNotBlank(readonly) ? "readonly='" + readonly + "' " : StringUtils.EMPTY;
	}

	/**
	 * @param readonly the readonly to set
	 */
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	/**
	 * @return the disabled
	 */
	public String getDisabled() {
		return StringUtils.isNotBlank(disabled) ? "disabled='" + disabled + "' " : StringUtils.EMPTY;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
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
