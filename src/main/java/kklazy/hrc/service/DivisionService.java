/**
 * 
 */
package kklazy.hrc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.hrc.model.Division;
import kklazy.persistence.service.impl.DefaultService;

/**
 * @author kk
 *
 */
@Service("divisionService")
@Transactional(rollbackFor = Exception.class)
public class DivisionService extends DefaultService<Division, String> {


}
