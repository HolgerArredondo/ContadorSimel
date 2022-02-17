package com.contadorsimel;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public interface IResult {

    public void notifySuccess(String requestType, String response);
    public void notifyError(String requestType, VolleyError error);

}
