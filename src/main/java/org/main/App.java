package org.main;

import org.thead.lesson1.HackerVsPoliceGame;
import org.thead.lesson1.ThreadCreate;
import org.thread.lesson2.BlockingTask;

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
}
