package Hackathon.standUp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry){
        String frontUrl = "http://localhost:5173";

        registry.addMapping("/**")
            .allowedOrigins("http://stan-dup.duckdns.org:8008", frontUrl)
            .allowCredentials(true)
            .exposedHeaders("authorization");
    }
}
