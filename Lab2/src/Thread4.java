import java.util.concurrent.CountDownLatch;

/**
 * Created by Alionka on 05.04.2017.
 */
public class Thread4 extends Thread {
    private final CountDownLatch latch3;

    Thread4(CountDownLatch latch3) {
        this.latch3 = latch3;
    }
    @Override
    public void run() {
        System.out.println("Thread 4 begins");
        try {
            System.out.println("Tread 4 awaits");
            latch3.await();
            Thread.sleep(300);
            System.out.println("Thread 4 finishes");
        } catch (InterruptedException ex) {
            return;
        }
    }
}
