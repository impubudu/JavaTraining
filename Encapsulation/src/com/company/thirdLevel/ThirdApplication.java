package com.company.thirdLevel;

public class ThirdApplication {

    public static void main(String[] args) {

        TrainStation trainStation = new TrainStation();
        TrainStation.Train train1 = trainStation.new Train();
        TrainStation.Train train2 = new TrainStation().new Train();

        trainStation.addTrain("Green");
        trainStation.addTrain(trainStation.new Train("Red"));
        trainStation.addTrain(new TrainStation().new Train("Blue"));

        trainStation.getTrainsPrint();
    }
}
