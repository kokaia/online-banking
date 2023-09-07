package ge.softlab.lessons.onlinebanking;

import ge.softlab.lessons.onlinebanking.test.TestStream;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(
    prePostEnabled = true
)
public class OnlineBankingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OnlineBankingApplication.class);
	}

	@PostConstruct
	public void init(){
        TestStream.run();
	}


}
