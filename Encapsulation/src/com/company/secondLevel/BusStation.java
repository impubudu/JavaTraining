package com.company.secondLevel;

import java.util.ArrayList;

public class BusStation {
    private ArrayList<Bus> buses = new ArrayList<>();

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void getBusesPrint() {
        System.out.println(buses);
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public BusStation() {
    }

    public BusStation(String color) {
        buses.add(new Bus(color));
    }

    public void addBus(Bus bus){
        buses.add(bus);
    }

    public void addBus(String color){
        buses.add(new Bus(color));
    }

    class Bus {
        private String color;

        public Bus() {

        }

        public Bus(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Bus{" +
                    "color='" + color + '\'' +
                    '}';
        }
    }
}
