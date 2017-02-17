package pl.mcekiera;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyTorrentService {
    private static Logger log = Logger.getLogger(DailyTorrentService.class);

    public static void main(String[] args) {
        log.info("Application: main method boot");
        SpringApplication.run(DailyTorrentService.class, args);
    }

}
