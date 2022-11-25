package com.bsuir.moyart.archive.client.controller.command.impl;

import com.bsuir.moyart.archive.client.controller.command.ClientCommand;
import com.bsuir.moyart.archive.client.service.ClientService;
import com.bsuir.moyart.archive.client.service.ServiceException;
import com.bsuir.moyart.archive.client.service.ClientServiceFactory;

public class AddCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        ClientServiceFactory serviceFactory = ClientServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        String response;

        try {
            clientService.sendRequest(request);
            response = clientService.getResponse();
        } catch (ServiceException e) {
            response = e.getMessage();
        }

        return response;
    }
}
