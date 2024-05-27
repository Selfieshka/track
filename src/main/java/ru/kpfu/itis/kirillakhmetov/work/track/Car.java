package ru.kpfu.itis.kirillakhmetov.work.track;

import java.util.ArrayList;
import java.util.List;

public class Car implements Runnable {
    private static int counter;
    public static long firstCar = -1;
    private final Thread thread;
    private int speed;
    private int coord;
    private final int num;
    public static List<Car> cars = new ArrayList<>();

    public Car() {
        thread = new Thread(this);
        speed = 0;
        this.num = counter++;
    }

    @Override
    public void run() {
        System.out.println("start " + counter);
        while (coord < 5000) {
            if (!checkDistance()) {
                System.out.println("stopping " + num + " " + coord);
                stopping();
                stop();
            } else {
                System.out.println("driving " + num + " " + coord);
                accelerate();
                drive();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (firstCar == -1) {
            firstCar = System.currentTimeMillis();
        }
        System.out.println("stop " + num);
    }

    private synchronized void drive() {
        coord += speed;
    }

    private synchronized void accelerate() {
        if (speed < 70) {
            speed += 3;
        } else {
            speed = 70;
        }
    }

    private synchronized void stop() {
        coord += speed;
    }

    private synchronized void stopping() {
        if (speed > 6) {
            speed -= 6;
        }
    }

    private boolean checkDistance() {
        for (int i = 0; i < counter; i++) {
            if (i != num) {
                if ((cars.get(i).coord > coord) && (cars.get(i).coord - coord) < 5) {
                    return false;
                }
            }
        }
        return true;
    }

    public Thread getThread() {
        return thread;
    }
}
