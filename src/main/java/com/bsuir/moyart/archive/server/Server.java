package com.bsuir.moyart.archive.server;

import com.bsuir.moyart.archive.server.bean.Account;
import com.bsuir.moyart.archive.server.controller.ServerController;
import com.bsuir.moyart.archive.server.controller.ServerControllerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    @Override
    public void run() {
        final int PORT = 8008;
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            ServerThread thread = new ServerThread(socket);
            thread.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static class ServerThread extends Thread implements Runnable {
        private boolean isActive;
        private final DataInputStream inputStream;
        private final DataOutputStream outputStream;
        private final Account account = new Account();

        public ServerThread(Socket socket) throws IOException {
            this.isActive = true;
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        }

        protected void disable() {
            isActive = false;
        }

        public void run() {
            ServerControllerFactory serverControllerFactory = ServerControllerFactory.getInstance();
            ServerController serverController = serverControllerFactory.getServerController();

            while (isActive) {
                try {
                    String request = inputStream.readUTF();
                    outputStream.writeUTF(serverController.executeRequest(request, account));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    break;
                } finally {
                    disable();
                }
            }
        }
    }

}
