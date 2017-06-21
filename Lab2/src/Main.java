import java.util.concurrent.CountDownLatch;

/**
 * Created by Alionka on 05.04.2017.
 */
public class Main {
    private void runThreads() {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);
        Thread1 thread1 = new Thread1(latch1);
        Thread2 thread2 = new Thread2(latch1, latch2);
        Thread3 thread3 = new Thread3(latch1, latch3);
        Thread4 thread4 = new Thread4(latch3);
        Thread5 thread5 = new Thread5(latch2);
        Thread6 thread6 = new Thread6(latch2);
        Thread7 thread7 = new Thread7(latch3);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
    }
    public static void main(String[] args){
        Main main = new Main();
        main.runThreads();
    }
}
