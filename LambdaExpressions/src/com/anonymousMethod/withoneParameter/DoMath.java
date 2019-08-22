package com.anonymousMethod.withoneParameter;

public class DoMath {
    public static void main(String[] args) {
        Math m = new Math() {
            @Override
            public int power(int x) {
                return x*x;
            }
        };
        m.power(5);
    }
}
