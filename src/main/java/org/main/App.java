package org.main;

import org.thead.lesson1.HackerVsPoliceGame;
import org.thead.lesson1.ThreadCreate;

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
}
