package org.thread.lesson1;

public class ThreadCreate {

    public void createNewTread() throws InterruptedException {
        // this thread class encapsulate all the thread related operations
        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        // code that runs in new thread
                        System.out.println("We are now in thread " + Thread.currentThread().getName());
                        System.out.println("current thread priority is " + Thread.currentThread().getPriority());
                    }
                }
        );

        thread.setName("new worker thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread : " + Thread.currentThread().getName() +
                " before stating a new thread");

        thread.start();

        System.out.println("We are in thread : " + Thread.currentThread().getName() +
                " after stating a new thread");

        Thread.sleep(10000);
    }
}
