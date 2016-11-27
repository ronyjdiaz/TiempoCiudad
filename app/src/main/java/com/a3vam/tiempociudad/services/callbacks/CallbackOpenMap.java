package com.a3vam.tiempociudad.services.callbacks;

import com.a3vam.tiempociudad.model.RootObject;

/**
 * Created by rony_2 on 27/11/2016.
 */

public interface CallbackOpenMap extends  CallbackBase {
    void onSuccess(RootObject value);
}
