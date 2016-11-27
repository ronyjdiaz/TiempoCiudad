package com.a3vam.tiempociudad.services.callbacks;

import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * Created by rony_2 on 27/11/2016.
 */

public interface Callbackpronostico extends CallbackBase {


        void onSuccess(JsonObject value);


}
