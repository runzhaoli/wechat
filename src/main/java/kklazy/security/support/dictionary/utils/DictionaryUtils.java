/**
 * 
 */
package kklazy.security.support.dictionary.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.security.model.Dictionary;
import kklazy.security.service.DictionaryService;
import kklazy.utils.ApplicationContextUtils;

/**
 * @author Kui
 */
public class DictionaryUtils {
	
	/**
	 * 
	 */
	private static Map<String, List<Dictionary>> dictionaries;

	/**
	 * @param group
	 * @return
	 * 
	 * @author Kui
	 */
	public static List<Dictionary> getDictionaries(String group) {
		
		if (dictionaries == null || dictionaries.isEmpty() || dictionaries.get(group) == null) {
			dictionaries = new HashMap<String, List<Dictionary>>();
			
			DictionaryService service = (DictionaryService) ApplicationContextUtils.getBean("dictionaryService");
			List<Dictionary> temps = service.findBy(new AssembleCriteriaParamsCallBack() {
				
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					
					return criteria;
				}
			}, new Sort(new Sort.Order(Direction.ASC, "sort")));
			
			for (Dictionary dictionary : temps) {
				List<Dictionary> sub = dictionaries.get(dictionary.getGroup());
				if (sub == null || sub.isEmpty()) {
					List<Dictionary> g = new ArrayList<Dictionary>();
					g.add(dictionary);
					dictionaries.put(dictionary.getGroup(), g);
				} else {
					dictionaries.get(dictionary.getGroup()).add(dictionary);
				}
			}
		}
		
		return dictionaries.get(group);
	}
	
	/**
	 * @param group
	 * @param Value
	 * @return
	 * 
	 * @author Kui
	 */
	public static Dictionary getDictionaryByValue(String group, String Value) {
		List<Dictionary> temp = getDictionaries(group);
		if (temp != null && !temp.isEmpty()) {
			for (Dictionary dictionary : temp) {
				if (dictionary.getValue().equals(Value)) {
					return dictionary;
				}
			}
		}
		return null;
	}
	
	/**
	 * @param group
	 * @param Value
	 * @return
	 * 
	 * @author Kui
	 */
	public static Dictionary getNextDictionaryByValue(String group, String Value) {
		List<Dictionary> temp = getDictionaries(group);
		if (temp != null && !temp.isEmpty()) {
			boolean flag = false;
			for (Dictionary dictionary : temp) {
				if (flag) {
					return dictionary;
				}
				if (dictionary.getValue().equals(Value)) {
					flag = true;
				}
			}
		}
		return null;
	}
	
	/**
	 * @param group
	 * @param key
	 * @return
	 * 
	 * @author Kui
	 */
	public static Dictionary getDictionaryByCode(String group, String code) {
		List<Dictionary> temp = getDictionaries(group);
		if (temp != null && !temp.isEmpty()) {
			for (Dictionary dictionary : temp) {
				if (dictionary.getCode().equals(code)) {
					return dictionary;
				}
			}
		}
		return null;
	}
	
	/**
	 * @author Kui
	 */
	public static void clean() {
		setDictionaries(null);
	}

	/**
	 * @return the dictionaries
	 */
	public static Map<String, List<Dictionary>> getDictionaries() {
		return dictionaries;
	}

	/**
	 * @param dictionaries the dictionaries to set
	 */
	public static void setDictionaries(Map<String, List<Dictionary>> dictionaries) {
		DictionaryUtils.dictionaries = dictionaries;
	}

}
