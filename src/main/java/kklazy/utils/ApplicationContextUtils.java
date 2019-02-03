package kklazy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Application Context Utilities
 */
@Service("application-context")
public class ApplicationContextUtils implements ApplicationContextAware {

    /**
     * 
     */
    private static ApplicationContext context;

    /**
     * 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        setContext(context);
    }

    /**
     * getter of context
     *
     * @return the context
     */
    public static ApplicationContext getContext() {
    	if (context == null) {
    		context = new ClassPathXmlApplicationContext("classpath*:/beans/**/*.xml");
    	}
        return context;
    }

    /**
     * setter of context
     *
     * @param context the context to set
     */
    private static void setContext(ApplicationContext context) {
        ApplicationContextUtils.context = context;
    }
    
    /**
     * @param beanname
     * @return
     */
    public static Object getBean(String beanname) {
    	return getContext().getBean(beanname);
    }

}
