package com.renshihan.settlement.exception;

import lombok.Data;

@Data
public class ServerException extends RuntimeException {

    private String message;
    public ServerException(String message) {
        super(message);
        this.message = message;
    }

    public ServerException(String message, Throwable e) {
        super(message,e);
        this.message = message;
    }

    public ServerException() {
        super("系统内部错误");
    }
}
