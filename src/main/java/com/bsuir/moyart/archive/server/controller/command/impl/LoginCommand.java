package com.bsuir.moyart.archive.server.controller.command.impl;

import com.bsuir.moyart.archive.server.bean.Account;
import com.bsuir.moyart.archive.server.controller.command.ServerCommand;
import com.bsuir.moyart.archive.server.service.ServerAccountService;
import com.bsuir.moyart.archive.server.service.ServerServiceException;
import com.bsuir.moyart.archive.server.service.ServerServiceFactory;

public class LoginCommand implements ServerCommand {
    @Override
    public String execute(String request, Account account) {
        ServerServiceFactory serviceFactory = ServerServiceFactory.getInstance();
        ServerAccountService serverAccountService = serviceFactory.getServerAccountService();

        StringBuilder response = new StringBuilder();

        try {
            if (request.split(" ").length < 3) {
                return "Invalid command parameters\n";
            }
            String login = request.split(" ")[1];
            String password = request.split(" ")[2];
            Account newAccount = serverAccountService.login(login, password);
            account.setLogin(newAccount.getLogin());
            account.setPassword(newAccount.getPassword());
            account.setRole(newAccount.getRole());
            return "Login" + login + ", role: " + account.getRole().toString();
        } catch (ServerServiceException e) {
            response.append(e.getMessage()).append("\n");
        }

        return response.toString();
    }
}
