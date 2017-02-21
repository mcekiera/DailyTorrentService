package pl.mcekiera.controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class UpdateController {
    private static Logger log = Logger.getLogger(UpdateController.class);
    private UpdateService updateService;

    /**
     * Initialize execution of updateService.
     */
    /*
    This method was executed with cron and @Scheduled annotation, however as application is deployed
    on Heroku servers, it must be scheduled with its interior solution, due to its free account regulations.
    @Scheduled(cron = "0 0 10,22 * * *")
     */
    public void runService() {
        log.info("Scheduled updateService: run");
        updateService.runService();
        System.exit(0);
    }

    /**
     * Set updateService implementation
     * @param updateService instance
     */
    public void setUpdateService(UpdateService updateService) {
        this.updateService = updateService;
    }


    /**
     * Additional main method for Heroku Scheduler updateService. As Heroku free option put app into sleep
     * if not currently used, the additional updateService is available, to run scheduled task.
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UpdateController controller = context.getBean("updateController",UpdateController.class);

        controller.runService();
    }
}
