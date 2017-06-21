import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CountedCompleter;

/**
 * Created by Alionka on 05.04.2017.
 */
public class Thread1 extends Thread {
    private final CountDownLatch latch1;
    Thread1 (CountDownLatch latch1){
    this.latch1 = latch1;
    }
    @Override
    public void run(){
        System.out.println("Thread 1 begins");
        try{
            Thread.sleep(300);
            latch1.countDown();
            System.out.println("Thread 1 finishes");
        }catch (InterruptedException ex){
            return;
        }
    }

}
