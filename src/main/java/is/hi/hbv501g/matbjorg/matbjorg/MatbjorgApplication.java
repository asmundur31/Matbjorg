package is.hi.hbv501g.matbjorg.matbjorg;

import is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations.AdvertisementServiceImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.io.File;

@SpringBootApplication
@EnableJpaRepositories
public class MatbjorgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatbjorgApplication.class, args);
    }

}
