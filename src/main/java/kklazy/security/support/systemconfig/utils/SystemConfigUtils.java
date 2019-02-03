package kklazy.security.support.systemconfig.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kklazy.persistence.utils.StringUtils;
import kklazy.security.model.SystemConfig;
import kklazy.security.service.SystemConfigService;
import kklazy.utils.ApplicationContextUtils;

/**
 * @author kk
 */
public class SystemConfigUtils {

	/**
	 * 
	 */
	private static Map<String, String> systemconfigs;
	
	/**
	 * @param key
	 * @return
	 */
	public static String getSystemConfig(String key) {
		if (systemconfigs == null || systemconfigs.isEmpty() || StringUtils.isBlank(systemconfigs.get(key))) {
			systemconfigs = new LinkedHashMap<String, String>();
			SystemConfigService service = (SystemConfigService) ApplicationContextUtils.getBean("systemConfigService");
			List<SystemConfig> configs = service.findAll();
			for (SystemConfig config : configs) {
				systemconfigs.put(config.getKey(), config.getValue());
			}
		}
		return systemconfigs.get(key);
	}

	/**
	 * 
	 */
	public static void clean() {
		setSystemconfigs(null);
	}

	/**
	 * @return the systemconfigs
	 */
	public static Map<String, String> getSystemconfigs() {
		return systemconfigs;
	}

	/**
	 * @param systemconfigs the systemconfigs to set
	 */
	public static void setSystemconfigs(Map<String, String> systemconfigs) {
		SystemConfigUtils.systemconfigs = systemconfigs;
	}
	
}
