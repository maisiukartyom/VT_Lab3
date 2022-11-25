package com.bsuir.moyart.archive.server.service;

public class ServerServiceException extends Exception {

    public ServerServiceException() {
        super();
    }

    public ServerServiceException(String message) {
        super(message);
    }

    public ServerServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServerServiceException(Exception e) {
        super(e);
    }
}
