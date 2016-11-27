package com.a3vam.tiempociudad.services.callbacks;

import com.a3vam.tiempociudad.services.responses.RespLogin;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */
public interface CallbackLogin extends CallbackBase {
    void onSuccess(RespLogin auth);


}
