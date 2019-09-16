package com.company.fouthLevel;

import java.util.ArrayList;

public class VanStation {
    private ArrayList<Van> vans = new ArrayList<>();

    public ArrayList<Van> getVans() {
        return vans;
    }

    public void getVanPrint() {
        for (Van van:vans){
            validate(van.getColor());
            System.out.println(van);
        }
    }

    public void setVans(ArrayList<Van> vans) {
        this.vans = vans;
    }

    public VanStation() {
    }

    public VanStation(String color) {
        vans.add(new Van(color));
    }

    public void addVan(Van van){
        vans.add(van);
    }

    public void addVan(String color){
        vans.add(new Van(color));
    }

    public void validate(String color){
        new Object(){
            public void colorValidate(){
                if("Red".equalsIgnoreCase(color)){
                    System.out.println("valid");
                }else {
                    System.out.println("invalid");
                }
            }
        }.colorValidate();
//        for(int x = 0;x<=10;x++){
//            System.out.println(x);
//        }
        //new TrainValidator().colorValidate();
    }

    class Van {
        private String color;

        public Van() {

        }

        public Van(String color) {
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
            return "Van{" +
                    "color='" + color + '\'' +
                    '}';
        }
    }
}


