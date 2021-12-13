package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String header = in.readLine();
                    String[] msg = header.split(" ")[1].split("=");
                    if (msg.length == 2) {
                        if ("Hello".equals(msg[1])) {
                            out.write("Hello!".getBytes());
                        }
                        if ("Any".equals(msg[1])) {
                            out.write("What".getBytes());
                        }
                        if ("Exit".equals(msg[1])) {
                            server.close();
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
