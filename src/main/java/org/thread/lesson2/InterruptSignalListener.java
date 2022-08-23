package org.thread.lesson2;

import java.math.BigInteger;

public class InterruptSignalListener implements Runnable {
    private BigInteger base;
    private BigInteger power;

    public InterruptSignalListener(BigInteger base, BigInteger power) {
        this.base = base;
        this.power = power;
    }

    public void run() {
        System.out.println(base + "^^" + power + " = " + calculatePow(base, power));
    }

    /*
     * this method takes long time to compute and
     * there isn't try catch block to handle thread.interupt()
     * so we need to listen whether outside function called to interrupt this calculation
     * * */
    private BigInteger calculatePow(BigInteger base, BigInteger power) {
        BigInteger result = BigInteger.ONE;

        for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {

            // below line will listen to thread.interrupt signal
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("prematurely interrupted computation");
                return BigInteger.ZERO;
            }

            result = result.multiply(base);
        }
        return result;
    }
}
