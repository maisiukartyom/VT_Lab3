package com.bsuir.moyart.archive.server.controller.command;

import com.bsuir.moyart.archive.server.bean.Account;

public interface ServerCommand {
    String execute(String request, Account account);
}
