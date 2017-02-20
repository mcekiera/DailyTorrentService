package pl.mcekiera.controller;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.mcekiera.service.DailyTorrentUpdate;

@Controller
public class UpdateController {
    private static Logger log = Logger.getLogger(UpdateController.class);
    private DailyTorrentUpdate service = new DailyTorrentUpdate();

    /**
     * Schedule automatic execution of DailyTorrentUpdate service on given time.
     */
    @Scheduled(cron = "0 0 10,22 * * *")
    public void runService() {
        log.info("Scheduled service: run");
        service.runService();
        System.exit(0);
    }


    /**
     * Additional main method for Heroku Scheduler service. As Heroku free option put app into sleep
     * if not currently used, the additional service is available, to run scheduled task.
     * @param args
     */
    public static void main(String[] args) {
        new UpdateController().runService();
    }
}
