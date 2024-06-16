package space.besh.beka_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BekaBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BekaBackApplication.class, args);
    }

}
