package com.bsuir.moyart.archive.client.controller.impl;

import com.bsuir.moyart.archive.client.controller.ClientController;
import com.bsuir.moyart.archive.client.controller.command.ClientCommandProvider;
import com.bsuir.moyart.archive.client.controller.command.ClientCommand;
import com.bsuir.moyart.archive.client.controller.constant.ClientControllerConstant;

public class ClientControllerImpl implements ClientController {
    @Override
    public String executeRequest(String request) {
        String commandName;
        commandName = request.split("\\s+")[0];

        ClientCommand command = ClientCommandProvider.getCommandByName(commandName);

        if (command == null) {
            return ClientControllerConstant.INVALID_COMMAND_RESPONSE;
        } else {
            return command.execute(request);
        }
    }
}
