package com.impubudu.autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class CarStation {
    @Autowired
    private Car car;

    public Car getCar() {
        return car;
    }

//    @Autowired
//    public void setCar(Car car) {
//        this.car = car;
//    }


//    @Autowired
//    public CarStation(Car car) {
//        this.car=car;
//    }
}
