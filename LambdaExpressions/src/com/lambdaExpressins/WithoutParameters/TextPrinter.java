package com.lambdaExpressins.WithoutParameters;

public class TextPrinter {
    public static void main(String[] args) {
        Printer p = ()-> System.out.println("Hi Lambda");
        p.print();
    }
}
