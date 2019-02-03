/**
 * 
 */
package kklazy.hrc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.hrc.model.ComplaintProposal;
import kklazy.persistence.service.impl.DefaultService;

/**
 * @author kk
 *
 */
@Service("complaintProposalService")
@Transactional(rollbackFor = Exception.class)
public class ComplaintProposalService extends DefaultService<ComplaintProposal, String> {

}
