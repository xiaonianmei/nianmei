package org.liufeng.test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/5/24.
 */
public class TestTimer {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // task to run goes here
                System.out.println("Hello !!!");
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 1 * 1000;
        // schedules the task to be run in an interval
        timer.schedule(task,intevalPeriod);
    }
}
