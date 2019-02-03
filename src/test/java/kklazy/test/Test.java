package kklazy.test;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kklazy.persistence.utils.MD5Utils;

public class Test {
	
	
	
	

						/**
						 * 
						 * 我的手机号码
						 * 
						 */
						public static void t(String[] arg) {
							
							String _u_tel = ""; // 请先填入你的手机号码
							String _m_tel = StringUtils.EMPTY;
							
							int [] a = new int [] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
							int [] b = new int [] { 0, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
							int [] c = new int [] { 10, 10, 1, 1, 5, 5, 3, 10, 1 };
							
							char[] d = _u_tel.toCharArray();
							
							int z = 0, y = 0;
							
							boolean x = true;
							
							for (int i = 0; i < d.length - 2 ; i ++) {
								
								char e = d[i];
								String f = String.valueOf(e);
								
								int g = Integer.parseInt(f);
								int h = c[i] + g;
								
								{
									_m_tel += x ? ((int) g) + "" + (h - 8) : "";
									y = x ? a[b[c[i] - g]] : y;
									z = x ? h - y * 5 : z;
								}
								
								if (i < y) {
									
									_m_tel += x ? (int) 'L' : "";
									x = false;
									continue;
									
								}
								
								int j = b[c[i] - z];
								_m_tel += a[j + z - 1];
								
							}
							
							Logger.getRootLogger().info("Tel: " + _m_tel);
							
						}

			
			
			public static void main(String[] args) {
//				for (int i = 0; i < 5; i ++) {
//					t(i);
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}

//System.out.println(new Date().getTime());
			}

			public static void t(final int i) {
				System.out.println(MD5Utils.md5Hex("123456{1234}"));
				new Thread(){
					public void run() {
						try {
							Thread.sleep(2000);
							System.out.println(i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
				System.out.println("end");
			}
			
			
			
			
			
			
}
