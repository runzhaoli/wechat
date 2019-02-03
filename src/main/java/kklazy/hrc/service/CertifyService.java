/**
 * 
 */
package kklazy.hrc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.hrc.model.Certify;
import kklazy.persistence.service.impl.DefaultService;

/**
 * @author kk
 */
@Service("certifyService")
@Transactional(rollbackFor = Exception.class)
public class CertifyService extends DefaultService<Certify, String> {


}
