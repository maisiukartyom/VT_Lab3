package com.bsuir.moyart.archive.server.service;

import com.bsuir.moyart.archive.server.bean.Account;

public interface ServerAccountService {
    Account login(String login, String password) throws ServerServiceException;
}
