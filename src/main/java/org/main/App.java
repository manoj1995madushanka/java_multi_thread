package org.main;

import org.thread.deadlock.DeadLock;
import org.thread.lesson1.HackerVsPoliceGame;
import org.thread.lesson2.BlockingTask;
import org.thread.lesson2.InterruptSignalListener;
import org.thread.join.lesson3.FactorialThread;
import org.thread.latency_performance.ImageProcess;
import org.thread.resource_sharing.CriticalSection;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        /*ThreadCreate threadCreate = new ThreadCreate();
        try {
            threadCreate.createNewTread();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        // hackerVsPoliceGameTrigger();

        /*try {
            calculateFactorialTest();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        /*try {
            ImageProcess.recolorImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        //CriticalSection.testCriticalSection();

        // deadlock test
        DeadLock.Intersection intersection = new DeadLock.Intersection();
        Thread trainAThread = new Thread(new DeadLock.TrainA(intersection));
        Thread trainBThread = new Thread(new DeadLock.TrainB(intersection));

        trainAThread.start();
        trainBThread.start();
    }

    private static void hackerVsPoliceGameTrigger() {
        HackerVsPoliceGame hackerVsPoliceGame = new HackerVsPoliceGame();
        hackerVsPoliceGame.startGame();
    }

    public static void testBlockingTask() {
        Thread thread = new Thread(new BlockingTask());
        thread.start();

        //without below line application is still runs when main thread already stopped
        thread.interrupt();

    }

    public static void interruptSignalParser() {
        Thread thread = new Thread(new InterruptSignalListener(new BigInteger("10000"), new BigInteger("5000000")));
        thread.start();

        // send interrupt signal
        thread.interrupt();
    }

    public static void calculateFactorialTest() throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 2324L, 4656L, 23L, 5556L);
        List<FactorialThread> threads = new ArrayList<FactorialThread>();

        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread t : threads) {
            t.start();

            // without below line application will terminate before done each thread work
            // the 2000 means after 2 seconds thread will start next thread to process
            t.join(2000);
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }

    }
}
