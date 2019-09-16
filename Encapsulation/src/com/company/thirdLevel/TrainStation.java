package com.company.thirdLevel;

import java.util.ArrayList;

public class TrainStation {
    private ArrayList<Train> trains = new ArrayList<>();

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public void getTrainsPrint() {
        for (Train train:trains){
            validate(train.getColor());
            System.out.println(train);
        }
    }

    public void setTrains(ArrayList<Train> trains) {
        this.trains = trains;
    }

    public TrainStation() {
    }

    public TrainStation(String color) {
        trains.add(new Train(color));
    }

    public void addTrain(Train train){
        trains.add(train);
    }

    public void addTrain(String color){
        trains.add(new Train(color));
    }

    public void validate(String color){
        class TrainValidator{
            public void colorValidate(){
                if("Red".equalsIgnoreCase(color)){
                    System.out.println("valid");
                }else {
                    System.out.println("invalid");
                }
            }
        }
//        for(int x = 0;x<=10;x++){
//            System.out.println(x);
//        }
        new TrainValidator().colorValidate();
    }

    class Train {
        private String color;

        public Train() {

        }

        public Train(String color) {
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
            return "Train{" +
                    "color='" + color + '\'' +
                    '}';
        }
    }
}

