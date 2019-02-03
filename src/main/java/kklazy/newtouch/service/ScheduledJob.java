package kklazy.newtouch.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kklazy.persistence.utils.DateUtils;

/**
 * 
 * @since manager 1.0
 * @author <a href="mailto:kklazy@live.cn">kk</a>
 */
@Component
public class ScheduledJob {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SynchronizeHrcDataService synchronizeHrcDataService;

	/**
	 * 
	 */
	@Scheduled(cron="0 30 23 * * ?")
//	@Scheduled(cron="0 * * * * ?")
	public void synchronizeHrcData() {
		System.out.println("1");
		logger.info(DateUtils.currentString());
		logger.info("sync start.");
		try {
			synchronizeHrcDataService.syncEmployeesAndAccounts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("sync end.");
	}

}
