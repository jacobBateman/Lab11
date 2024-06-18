package org.example;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private Server server;
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;
    String login;


    public Client(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
    }
    private void parseMessage(String message){
        if(message.equals("/online")){
            send(server.serverUsersLogins().toString());
        }
        else {
            server.broadcast(message);
        }
    }



    @Override
    public void run(){
        try {
            String message;
            while((message = reader.readLine())!=null){
                //this.writer.println(message);
                parseMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String message){
        this.writer.println(message);
    }

    private void authenticate() throws IOException {
        login = reader.readLine();
        server.clientLogged(this);
    }

    public String getLogin() {
        return login;
    }

    private void leave() throws IOException {
        socket.close();
        server.clientLeft(this);
    }
}
