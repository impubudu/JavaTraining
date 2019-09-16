package com.company.secondLevel;

import com.company.firstLevel.Car;
import com.company.firstLevel.CarStation;

public class SecondApplication {

    public static void main(String[] args) {

        BusStation busStation = new BusStation();

        BusStation.Bus bus1 = busStation.new Bus("Black");
        BusStation.Bus bus2 = new BusStation().new Bus();

        busStation.addBus("Green");
        busStation.addBus(busStation.new Bus("Red"));
        busStation.addBus(new BusStation().new Bus("Blue"));

        System.out.println(busStation.getBuses());
        busStation.getBusesPrint();
    }
}
