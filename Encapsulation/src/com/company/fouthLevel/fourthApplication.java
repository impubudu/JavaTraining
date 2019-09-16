package com.company.fouthLevel;

public class fourthApplication {

    public static void main(String[] args) {

        VanStation vanStation = new VanStation();
        VanStation.Van van1 = vanStation.new Van();
        VanStation.Van van2 = new VanStation().new Van();

        vanStation.addVan("Green");
        vanStation.addVan(vanStation.new Van("Red"));
        vanStation.addVan(new VanStation().new Van("Blue"));

        vanStation.getVanPrint();
    }
}
