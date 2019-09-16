package com.company.firstLevel;

import com.company.secondLevel.BusStation;

public class FirstApplication {

    public static void main(String[] args) {

        CarStation carStation = new CarStation();
        for (Car car : CarStation.cars) {
            System.out.println(car.getColor());
            System.out.println(car);
        }
    }
}
