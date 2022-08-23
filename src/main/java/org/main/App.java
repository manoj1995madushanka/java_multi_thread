package org.main;

import org.thead.lesson1.HackerVsPoliceGame;
import org.thead.lesson1.ThreadCreate;
import org.thread.lesson2.BlockingTask;
import org.thread.lesson2.InterruptSignalListener;

import java.math.BigInteger;
import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        /*ThreadCreate threadCreate = new ThreadCreate();
        try {
            threadCreate.createNewTread();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        hackerVsPoliceGameTrigger();
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
}
