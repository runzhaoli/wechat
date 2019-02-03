package kklazy.gala.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * @author kk
 *
 */
@Entity
@Table(name = "GALA_LUCKY")
public class Lucky extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7559633935144520597L;

	private String a;

	private String b;

	private String c;
	
	private String d;

	/**
	 * @return the a
	 */
	@Column(name = "COLUMN_A")
	public String getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(String a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	@Column(name = "COLUMN_B")
	public String getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(String b) {
		this.b = b;
	}

	/**
	 * @return the c
	 */
	@Column(name = "COLUMN_C")
	public String getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(String c) {
		this.c = c;
	}

	/**
	 * @return the d
	 */
	@Column(name = "COLUMN_D")
	public String getD() {
		return d;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(String d) {
		this.d = d;
	}

}
