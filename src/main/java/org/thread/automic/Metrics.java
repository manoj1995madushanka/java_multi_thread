package org.thread.automic;

public class Metrics {
    private long count = 0;
    // need to make average variable volatile for make it atomic
    private volatile double average = 0.0;

    /*
     * this method need to be synchronized because multiple threads can't be access same time
     * * */
    public synchronized void addSample(long sample) {
        double currentSum = average * count;
        count++;
        average = (currentSum + sample) / count;

    }

    /*
    * don't need to make this synchronize because average is volatile
    * * */
    public double getAverage() {
        return average;

    }
}
