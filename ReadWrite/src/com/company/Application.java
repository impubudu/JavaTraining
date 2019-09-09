package com.company;

import java.io.*;

public class Application {

    public static void main(String[] args) {
       write();
       read();
       System.out.println("-------------------------------------------");
       writeWithResources();
       readWithResources();
    }

    public static void read(){
        try {
            FileReader fr = new FileReader("/home/user/Desktop/write.txt");
            BufferedReader br = new BufferedReader(fr);

            Object strCurrentLine;
            while ((strCurrentLine = br.readLine())!=null){
                System.out.println(strCurrentLine);
            }
            br.close();
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    public static void write(){
        try{
            FileWriter fw = new FileWriter("/home/user/Desktop/write.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i<=10; i++){
                bw.write(""+i);
                bw.newLine();
            }
            bw.close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public static void readWithResources(){
        try (   FileReader fr = new FileReader("/home/user/Desktop/writeWithResources.txt");
                BufferedReader br = new BufferedReader(fr);) {
            Object strCurrentLine;
            while ((strCurrentLine = br.readLine())!=null){
                System.out.println(strCurrentLine);
            }
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public static void writeWithResources(){
        try (   FileWriter fw = new FileWriter("/home/user/Desktop/writeWithResources.txt");
                BufferedWriter bw = new BufferedWriter(fw);) {
            for(int i = 0; i<=10; i++){
                bw.write(""+i);
                bw.newLine();
            }
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
