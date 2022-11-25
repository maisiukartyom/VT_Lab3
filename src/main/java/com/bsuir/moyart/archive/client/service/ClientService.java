package com.bsuir.moyart.archive.client.service;

public interface ClientService {

    boolean connect(String request) throws ServiceException;

    void sendRequest(String request) throws ServiceException;

    String getResponse() throws ServiceException;
}
