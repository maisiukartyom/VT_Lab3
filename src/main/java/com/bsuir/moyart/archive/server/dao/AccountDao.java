package com.bsuir.moyart.archive.server.dao;

import com.bsuir.moyart.archive.server.bean.Account;

public interface AccountDao {
    Account login(String login, String passwordHash) throws DaoException;
}
