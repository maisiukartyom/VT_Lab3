package com.bsuir.moyart.archive.server.controller;

import com.bsuir.moyart.archive.server.bean.Account;

public interface ServerController {
    String executeRequest(String request, Account account);
}
