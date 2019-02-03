/**
 * 
 */
package kklazy.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.service.impl.DefaultService;
import kklazy.security.model.Role;

/**
 * @author Kui
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService extends DefaultService<Role, String> {

}
