package com.bsuir.moyart.archive.server.dao.impl;

import com.bsuir.moyart.archive.server.bean.Account;
import com.bsuir.moyart.archive.server.dao.AccountDao;
import com.bsuir.moyart.archive.server.dao.DaoException;
import com.bsuir.moyart.archive.server.dao.parser.XMLParser;
import com.bsuir.moyart.archive.server.dao.parser.XMLParserException;
import com.bsuir.moyart.archive.server.dao.parser.impl.XMLUniversityParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AccountDaoImpl implements AccountDao {
    private final File dbFile = new File(
            Objects.requireNonNull(getClass().getClassLoader().getResource("accounts_db.xml")).getFile());

    @Override
    public Account login(String login, String passwordHash) throws DaoException {
        try {
            XMLParser parser = new XMLUniversityParser();
            List<Map<String, String>> profileParams = parser.parse(dbFile);
            for (Map<String, String> params : profileParams) {
                if (params.get("login").equals(login) && params.get("password").equals(passwordHash)) {
                    return new Account(params);
                }
            }
            return new Account();
        } catch (XMLParserException | ParserConfigurationException | IOException | SAXException e) {
            throw new DaoException(e);
        }
    }
}
