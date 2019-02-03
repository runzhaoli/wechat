/**
 * 
 */
package kklazy.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

/**
 * @author kk
 */
public class BeanMapUtils {

	/**
	 * bean转map
	 * 
	 * @param bean
	 * @return
	 * 
	 */
	public static Map<String, Object> convert(Object bean) {

		Map<String, Object> retval = new HashMap<String, Object>();

		Class<?> clazz = bean.getClass();
		try {

			Field[] fields = clazz.getDeclaredFields();

			for (Field field : fields) {
				if (!field.isAccessible()) {
					field.setAccessible(Boolean.TRUE);
				}
				String fieldName = field.getName();
				if ("serialVersionUID".equals(fieldName)) {
					continue;
				}
				retval.put(fieldName, field.get(bean) == null ? "" : field.get(bean));
			}

		} catch (IllegalArgumentException | IllegalAccessException e) {
			LoggerFactory.getLogger(BeanMapUtils.class).error("JavaBean转Map<String, Object>出错;" + e);
			e.printStackTrace();
		}

		return retval;
	}

	/**
	 * map转bean
	 * 
	 * @param map
	 * @param bean
	 * 
	 */
	public static void convert(Map<String, Object> map, Object bean) {

		try {
			
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				
				String value = StringUtils.trim(String.valueOf(map.get(property.getName())));
				
				if (StringUtils.isNotBlank(value) && !"null".equals(value)) {
					
					Method    setter = property.getWriteMethod();
					Class< ? > clazz = property.getPropertyType();

					try {
						
						if (clazz.equals(BigDecimal.class)) {
							setter.invoke(bean, new BigDecimal(value));
						} else if (clazz.equals(Double.class)) {
							setter.invoke(bean, new Double(value));
						} else if (clazz.equals(double.class)) {
							setter.invoke(bean, new Double(value).doubleValue());
						} else if (clazz.equals(Float.class)) {
							setter.invoke(bean, new Float(value));
						} else if (clazz.equals(float.class)) {
							setter.invoke(bean, new Float(value).floatValue());
						} else if (clazz.equals(Long.class)) {
							setter.invoke(bean, new Long(value));
						} else if (clazz.equals(long.class)) {
							setter.invoke(bean, new Long(value).longValue());
						} else if (clazz.equals(Integer.class)) {
							setter.invoke(bean, new Integer(value));
						} else if (clazz.equals(int.class)) {
							setter.invoke(bean, new Integer(value).intValue());
						} else {
							setter.invoke(bean, value);
						}

					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						LoggerFactory.getLogger(BeanMapUtils.class).error("类型转换出错,[Field: + " + property.getName() + "][value: + " + value +" + ][type: + " + clazz + " + ];" + e);
						e.printStackTrace();
						continue ;
					}
				}
			}
			
		} catch (IntrospectionException e) {
			LoggerFactory.getLogger(BeanMapUtils.class).error("获取实体类型出错;" + e);
			e.printStackTrace();
		}

	}

}
