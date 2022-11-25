package com.bsuir.moyart.archive.client.controller;

import com.bsuir.moyart.archive.client.controller.impl.ClientControllerImpl;

public class ClientControllerFactory {
    private static final ClientControllerFactory instance = new ClientControllerFactory();
    private final ClientController clientController = new ClientControllerImpl();

    private ClientControllerFactory() {
    }

    public static ClientControllerFactory getInstance() {
        return instance;
    }

    public ClientController getClientController() {
        return getClientController();
    }

}
