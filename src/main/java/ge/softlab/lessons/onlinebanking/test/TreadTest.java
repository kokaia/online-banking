package ge.softlab.lessons.onlinebanking.test;

import java.time.Duration;
import java.time.LocalDateTime;

public class TreadTest {

    public void run(){
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
