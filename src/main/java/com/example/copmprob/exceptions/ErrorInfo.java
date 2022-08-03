package com.example.copmprob.exceptions;

public class ErrorInfo {
    private final String url;
    private final String exception;

    public ErrorInfo(String url, String ex) {
        this.url = url;
        this.exception = ex;
    }
}
