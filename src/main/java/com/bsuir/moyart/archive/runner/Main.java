package com.bsuir.moyart.archive.runner;

import com.bsuir.moyart.archive.client.Client;
import com.bsuir.moyart.archive.server.Server;

public class Main {
    public static void main(String[] args) {
        new Server().start();
        new Client().start();
    }
}
