package is.hi.hbv501g.matbjorg.matbjorg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MatbjorgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatbjorgApplication.class, args);
    }

}
