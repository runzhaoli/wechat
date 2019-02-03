/**
 * 
 */
package kklazy.security.support.resource.provider;

import java.util.Collection;

import kklazy.security.model.Resource;
import kklazy.security.support.resource.callback.ResourceCallBack;

/**
 * @author Kui
 */
public interface Provider {

	/**
	 * @param container
	 * @param callback
	 * 
	 * @author Kui
	 */
	public void merge(Resource container, ResourceCallBack callback);

	/**
	 * @param container
	 * @param id
	 * @param callback
	 * 
	 * @author Kui
	 */
	public void merge(Resource container, Long id, ResourceCallBack callback);

	/**
	 * @param container
	 * @param resources
	 * @param callback
	 * 
	 * @author Kui
	 */
	public void merge(Resource container, Collection<Resource> resources, ResourceCallBack callback);
	
}
