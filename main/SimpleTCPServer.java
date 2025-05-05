package main;

import java.io.*;
import java.net.*;

public class SimpleTCPServer {

    public static void main(String[] args) {
        int port = 1729;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Handle client in a new thread (like `go do(conn)` in Go)
                new Thread(() -> handleClient(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream()
        ) {
            byte[] request = new byte[1024];
            input.read(request);  // Read the HTTP request (but not parsed here)

            System.out.println("Processing the request...");
            Thread.sleep(5000); // Simulate long-running task
            System.out.println("Processing complete...");

            String response = "HTTP/1.1 200 OK\r\n\r\nHello, World!\r\n";
            output.write(response.getBytes());
            output.flush();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }
}
