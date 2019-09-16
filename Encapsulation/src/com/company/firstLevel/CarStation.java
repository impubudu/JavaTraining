package com.company.firstLevel;

import java.util.ArrayList;

public class CarStation {
    public static ArrayList<Car> cars;

    static {

        cars = new ArrayList<>();
        cars.add(new Car("Red"));
        cars.add(new Car("Green"));
        cars.add(new Car("White"));
        cars.add(new Car("Blue"));
        cars.add(new Car("Black"));
    }

}

