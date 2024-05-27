package ru.kpfu.itis.kirillakhmetov.work.track;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Car car = new Car();
            cars.add(car);
            Car.cars.add(car);
            car.getThread().start();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Car car : cars) {
            car.getThread().join();
        }

        System.out.println((System.currentTimeMillis() - Car.firstCar) / 1000 / 15);
    }
}