package com.bsuir.moyart.archive.client.controller.command;

import com.bsuir.moyart.archive.client.controller.command.impl.*;
import com.bsuir.moyart.archive.client.controller.constant.ClientControllerConstant;

import java.util.HashMap;
import java.util.Map;

public class ClientCommandProvider {
    private static final Map<String, ClientCommand> clientCommands = new HashMap<>() {
        {
            put(ClientControllerConstant.ADD_COMMAND, new AddCommand());
            put(ClientControllerConstant.CONNECT_COMMAND, new ConnectCommand());
            put(ClientControllerConstant.EDIT_COMMAND, new EditCommand());
            put(ClientControllerConstant.LOGIN_COMMAND, new LoginCommand());
            put(ClientControllerConstant.GET_COMMAND, new GetUserByIdCommand());
        }
    };

    private ClientCommandProvider() {
    }

    public static ClientCommand getCommandByName(String name) {
        return clientCommands.get(name);
    }
}
