import java.util.concurrent.CountDownLatch;

/**
 * Created by Alionka on 05.04.2017.
 */
public class Thread6 extends Thread {
    private final CountDownLatch latch2;

    Thread6(CountDownLatch latch2) {
        this.latch2 = latch2;
    }
    @Override
    public void run() {
        System.out.println("Thread 6 begins");
        try {
            System.out.println("Tread 6 awaits");
            latch2.await();
            Thread.sleep(300);
            System.out.println("Thread 6 finishes");
        } catch (InterruptedException ex) {
            return;
        }
    }
}
