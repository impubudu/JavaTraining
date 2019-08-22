package com.anonymousMethod.withTwoParameters;

public class DoMath {
    public static void main(String[] args) {
        Math m = new Math() {
            @Override
            public int sum(int x,int y) {
                return x+y;
            }
        };
        System.out.println(m.sum(5,10));
    }
}
