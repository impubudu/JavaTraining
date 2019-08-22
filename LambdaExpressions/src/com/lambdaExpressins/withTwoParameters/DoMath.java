package com.lambdaExpressins.withTwoParameters;

public class DoMath {
    public static void main(String[] args) {
        Math m = (x,y)->x+y;
        System.out.println(m.sum(5,10));
    }
}
