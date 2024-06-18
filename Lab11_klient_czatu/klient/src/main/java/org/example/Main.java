package org.example;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Client klient = new Client();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            new Thread(klient).start();
            while(true){
                klient.send(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}