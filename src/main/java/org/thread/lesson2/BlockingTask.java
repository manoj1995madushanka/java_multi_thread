package org.thread.lesson2;

/**
 *this thread sleeping 50000 seconds
 * so main thread is terminated the application is still running
 * then we need to call thread.interrupt method
 *
 * */
public class BlockingTask implements Runnable{
    public void run() {
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            System.out.println("Existing blocking thread");
        }
    }
}
