package com.paradise.interview.io.scoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Paradise
 */
public class SocketServerDemo {

    static void run() throws IOException {
        // init
        ServerSocket serverSocket = new ServerSocket(1025);
        // create listen
        Socket socket = serverSocket.accept();
        // reader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // writer
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        String s = bufferedReader.readLine();
        printWriter.println(s);
        print(s);

        bufferedReader.close();
        printWriter.close();
    }

    private static void print(String string) {
        System.out.println("Server Accept: " + string);
    }

    public static void main(String[] args) {
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
