package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;


    public Client( ) throws IOException {

        this.socket = new Socket("localhost",2137);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
    }





    @Override
    public void run(){
        try {
            String message;
            
            while((message = reader.readLine())!=null){
                //this.writer.println(message);
                System.out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String message){
        this.writer.println(message);
    }
}
