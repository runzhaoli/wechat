/**
 * 
 */
package kklazy.component.file.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.component.file.model.Attachfile;
import kklazy.persistence.service.impl.DefaultService;

/**
 * 附件管理
 */
@Service("attachfileService")
@Transactional(rollbackFor = Exception.class)
public class AttachfileService extends DefaultService<Attachfile, String> {

}
