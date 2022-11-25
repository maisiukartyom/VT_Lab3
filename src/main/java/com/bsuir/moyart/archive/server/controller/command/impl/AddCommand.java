package com.bsuir.moyart.archive.server.controller.command.impl;

import com.bsuir.moyart.archive.server.bean.Account;
import com.bsuir.moyart.archive.server.bean.Role;
import com.bsuir.moyart.archive.server.bean.Student;
import com.bsuir.moyart.archive.server.controller.command.ServerCommand;
import com.bsuir.moyart.archive.server.service.ServerUniversityService;
import com.bsuir.moyart.archive.server.service.ServerServiceException;
import com.bsuir.moyart.archive.server.service.ServerServiceFactory;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class AddCommand implements ServerCommand {
    @Override
    public String execute(String request, Account account) {
        if (account.getRole() == Role.GUEST) {
            return "Not authorized";
        } else if (account.getRole() != Role.ADMIN) {
            return "Not enough rights";
        }

        ServerServiceFactory serviceFactory = ServerServiceFactory.getInstance();
        ServerUniversityService serverService = serviceFactory.getServerServiceUniversityService();

        Map<String, String> params = new HashMap<>();
        for (String param : request.split("\\s+")) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1].replaceAll("\"", ""));
            }
        }

        StringBuilder response = new StringBuilder();

        try {
            serverService.add(new Student(params));
            return "Student added\n";
        } catch (ServerServiceException | ParseException e) {
            response.append(e.getMessage()).append("\n");
        }

        return response.toString();
    }
}
