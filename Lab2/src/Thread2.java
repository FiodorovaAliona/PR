import java.util.concurrent.CountDownLatch;

/**
 * Created by Alionka on 05.04.2017.
 */
public class Thread2 extends Thread {
    private final CountDownLatch latch2;
    private final CountDownLatch latch1;

    public Thread2(CountDownLatch latch1, CountDownLatch latch2) {
        this.latch2 = latch2;
        this.latch1 = latch1;
    }

    @Override
    public void run() {
        System.out.println("Thread 2 begins");
        try{
            System.out.println("Thread 2 awaits");
            latch1.await();
            Thread.sleep(300);
            latch2.countDown();
            System.out.println("Thread 2 finishes");
        }catch (InterruptedException ex){
            return;
        }
    }
}
