package com.paradise.interview.io.scoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Paradise
 */
public class SocketClientDemo {

    static void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            Socket socket = new Socket("localhost", 1025);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Hello...");
            String s = reader(reader);
            System.out.println("Client Accept: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String reader(BufferedReader reader) throws IOException {
        String s = null;
        while (s == null) {
            s = reader.readLine();
        }
        return s;
    }
}
