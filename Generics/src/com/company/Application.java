package com.company;

public class Application {

    public static void main(String[] args) {

        Car car = new Car();
        Bus bus = new Bus();
        Dog dog = new Dog();

        Vehicle<Car> carVehicle = new Vehicle<>(car);
        Vehicle<Bus> busVehicle = new Vehicle<>(bus);
        //Vehicle<Dog> dogVehicle = new Vehicle<>(dog);

        carVehicle.drive();
        busVehicle.drive();
        //dogVehicle.drive();


        ArrayPrinter arrayPrinter  = new ArrayPrinter();
        Integer[] integers = {1,2,3,4,5};
        String[] strings = {"A","B","C"};

        arrayPrinter.printArray(integers);
        arrayPrinter.printArray(strings);
    }
}
