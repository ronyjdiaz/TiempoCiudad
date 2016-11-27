package com.a3vam.tiempociudad.services.responses;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */

public class RespLogin extends RespBase {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
