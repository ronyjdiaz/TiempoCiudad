package com.a3vam.tiempociudad.services.responses;

/**
 * Created by rony_2 on 22/11/2016.
 */

public class RespBase {
    int errorCode;
    String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
