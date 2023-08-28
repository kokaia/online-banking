package ge.softlab.lessons.onlinebanking.test;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestRun implements Runnable {

    public static int num = 0;
    public AtomicBoolean isRunning = new AtomicBoolean(false);

    private final String name;
    private static final Object lock =  new Object();

    public TestRun(String name) {
        this.name = name;

    }

    public void execute(){
        System.out.println( name + ", execute");

    }

    @Override
    public void run() {
        for (int i=1; i<= 100; ++i){
            synchronized (lock){
                num = num + 1;
            }

//            if (i%100 == 0) {
//                System.out.println(name + ", num=" + num);
//            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignore) {
            }
        }

        System.out.println( name + ", final num is " + num);
    }

}
