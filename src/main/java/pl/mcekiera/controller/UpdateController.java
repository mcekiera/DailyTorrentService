package pl.mcekiera.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.mcekiera.service.DailyTorrentUpdate;

@Controller
public class UpdateController {
    DailyTorrentUpdate service = new DailyTorrentUpdate();


    @Scheduled(cron = "0 0 8-10 * * *")
    public void runService() {
        service.runService();
    }
}
