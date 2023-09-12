package ge.softlab.lessons.onlinebanking.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
@Component
public class AppConfiguration {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMinutes(1))
            .setConnectTimeout(Duration.ofMinutes(2))
            .build();
    }

}
