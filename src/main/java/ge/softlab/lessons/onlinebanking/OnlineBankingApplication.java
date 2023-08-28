package ge.softlab.lessons.onlinebanking;

import ge.softlab.lessons.onlinebanking.test.TestRun;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
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

		TestRun r1 = new TestRun("Thread 1");
		TestRun r2 = new TestRun("Thread 2");
		TestRun r3 = new TestRun("Thread 3");

		LocalDateTime started = LocalDateTime.now();
		new Thread(r1).start();
		new Thread(r2).start();
		new Thread(r3).start();

		System.out.printf("execution time: %s\n", Duration.between(started,LocalDateTime.now()));

	}

}
