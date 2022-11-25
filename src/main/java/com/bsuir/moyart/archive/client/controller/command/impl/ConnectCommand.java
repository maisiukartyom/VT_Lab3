package com.bsuir.moyart.archive.client.controller.command.impl;

import com.bsuir.moyart.archive.client.controller.command.ClientCommand;
import com.bsuir.moyart.archive.client.controller.constant.ClientControllerConstant;
import com.bsuir.moyart.archive.client.service.ClientService;
import com.bsuir.moyart.archive.client.service.ServiceException;
import com.bsuir.moyart.archive.client.service.ClientServiceFactory;

public class ConnectCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        ClientServiceFactory serviceFactory = ClientServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        String response;

        try {
            response = clientService.connect(request) ? ClientControllerConstant.SUCCESS_CONNECT : ClientControllerConstant.BAD_CONNECT;
        } catch (ServiceException e) {
            response = e.getMessage();
        }

        return response;
    }
}
