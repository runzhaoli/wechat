/**
 * 
 */
package kklazy.security.support.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kklazy.security.model.Resource;
import kklazy.security.service.ResourceService;
import kklazy.security.support.resource.callback.ResourceCallBack;
import kklazy.security.support.resource.provider.Provider;

/**
 * @author Kui
 */
@Service("resourceProvider")
public class ResourceProvider implements Provider {

    @Autowired
    private ResourceService resourceService;

	/**
	 * @see kklazy.security.support.resource.provider.Provider#merge(kklazy.security.model.Resource, kklazy.security.support.resource.callback.ResourceCallBack)
	 */
	@Override
	public void merge(Resource container, ResourceCallBack callback) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see kklazy.security.support.resource.provider.Provider#merge(kklazy.security.model.Resource, java.lang.Long, kklazy.security.support.resource.callback.ResourceCallBack)
	 */
	@Override
	public void merge(Resource container, Long id, ResourceCallBack callback) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see kklazy.security.support.resource.provider.Provider#merge(kklazy.security.model.Resource, java.util.Collection, kklazy.security.support.resource.callback.ResourceCallBack)
	 */
	@Override
	public void merge(Resource container, Collection<Resource> resources, ResourceCallBack callback) {
		for (Resource resource : resources) {
			Resource item = (Resource) callback.doInAssemble(resource);
			if (item != null) {
				if (container.getSublevel() == null) {
					container.setSublevel(new ArrayList<Resource>());
				}
				container.getSublevel().add(item);
				Resource parent = resourceService.findBy(item.getId());
				List<Resource> sub = parent.getSublevel();
				if (sub != null && !sub.isEmpty()) {
					merge(item, sub, callback);
				}
			}
		}
	}
    
    
}
