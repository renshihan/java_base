package com.renshihan.settlement.exception;


public class RpcException extends ServerException {
    public RpcException(String message, Exception e) {
        super(message, e);
    }
}
