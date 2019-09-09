package com.company;

public class Application {

    public static void main(String[] args) {
	    int number = 123445;
	    int reverse=0;
	    while (number!=0){
	        reverse = number%10 + reverse*10;
	        number = number/10;
        }
        System.out.println(reverse);
    }
}
