import java.util.concurrent.CountDownLatch;

/**
 * Created by Alionka on 05.04.2017.
 */
public class Thread3 extends Thread {
    private final CountDownLatch latch1;
    private final CountDownLatch latch3;

    Thread3(CountDownLatch latch1, CountDownLatch latch3) {
        this.latch3 = latch3;
        this.latch1 = latch1;
    }

    @Override
    public void run() {
        System.out.println("Thread 3 begins");
        try{
            System.out.println("Thread 3 awaits");
            latch1.await();
            Thread.sleep(300);
            latch3.countDown();
            System.out.println("Thread 3 finishes");
        }catch (InterruptedException ex){
            return;
        }
    }
}
