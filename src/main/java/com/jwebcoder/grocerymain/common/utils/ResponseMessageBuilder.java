package com.jwebcoder.grocerymain.common.utils;


import com.jwebcoder.grocerymain.common.dto.ResponseMessage;
import com.jwebcoder.grocerymain.common.exception.StatusCode;

/**
 * Created by Jason on 10/10/2017.
 */
public class ResponseMessageBuilder {

    public static ResponseMessage failed (StatusCode statusCode) {
        ResponseMessage message = new ResponseMessage();

        message.setCode(statusCode.getCode());
        message.setMessage(statusCode.getErrorMessage());

        return message;
    }

    public static ResponseMessage success(Object object) {

        ResponseMessage message = new ResponseMessage();

        message.setCode(StatusCode.SUCCESSFUL.getCode());
        message.setMessage(StatusCode.SUCCESSFUL.getErrorMessage());
        message.setData(object);

        return message;
    }

}
