package pl.mcekiera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyTorrentService {

    public static void main(String[] args) {
        SpringApplication.run(DailyTorrentService.class, args);
    }

}
