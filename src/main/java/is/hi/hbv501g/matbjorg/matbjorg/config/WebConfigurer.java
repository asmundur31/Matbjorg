package is.hi.hbv501g.matbjorg.matbjorg.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfigurer implements WebMvcConfigurer {
    public static String uploadDirectory= System.getProperty("user.dir")+"/src/main/resources/static/img/advertisementImages";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/advertisementImages/**", "/css/**", "/img/**", "/js/**")
                .addResourceLocations(
                        "file:" + uploadDirectory + "/",
                        "classpath:/static/css/",
                        "classpath:/static/img/",
                        "classpath:/static/img/advertisementImages",
                        "classpath:/static/js/"
                        );
    }
}