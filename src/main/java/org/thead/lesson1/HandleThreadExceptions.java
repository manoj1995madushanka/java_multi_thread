package org.thead.lesson1;

public class HandleThreadExceptions {
    public void handleExceptions() {

        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        // code that runs in new thread
                        throw new RuntimeException("Intentional Exception");
                    }
                }
        );

        // handle thread exception
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread "
                        + t.getName() + " the error is " + e.getMessage());
            }
        });

        thread.start();
    }
}
