package pl.mcekiera.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.mcekiera.service.DailyTorrentService;

@Controller
public class UpdateController {
    DailyTorrentService service = new DailyTorrentService();


    @Scheduled(fixedRate = 10000)
    public void runService() {
        service.runService();
    }
}
