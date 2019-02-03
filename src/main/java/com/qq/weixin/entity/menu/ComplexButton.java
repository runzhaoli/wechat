package com.qq.weixin.entity.menu;

/**
 * 父按钮,可以包含3-5个子按钮
 * 
 * @author kk
 */
public class ComplexButton extends BaseButton {

	/**
	 * 
	 */
	private BaseButton[] sub_button;

	/**
	 * @return the sub_button
	 */
	public BaseButton[] getSub_button() {
		return sub_button;
	}

	/**
	 * @param sub_button the sub_button to set
	 */
	public void setSub_button(BaseButton[] sub_button) {
		this.sub_button = sub_button;
	}

}
