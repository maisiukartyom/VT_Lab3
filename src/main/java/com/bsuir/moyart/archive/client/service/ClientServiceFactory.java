package com.bsuir.moyart.archive.client.service;

import com.bsuir.moyart.archive.client.service.impl.ClientServiceImpl;

public final class ClientServiceFactory {
    private static final ClientServiceFactory instance = new ClientServiceFactory();

    private final ClientService clientService = new ClientServiceImpl();

    private ClientServiceFactory() {
    }

    public ClientService getClientService() {
        return clientService;
    }

    public static ClientServiceFactory getInstance() {
        return instance;
    }
}
