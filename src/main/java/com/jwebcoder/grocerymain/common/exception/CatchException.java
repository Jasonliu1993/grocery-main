package com.jwebcoder.grocerymain.common.exception;

import com.jwebcoder.grocerymain.common.dto.ResponseMessage;
import com.jwebcoder.grocerymain.common.utils.PackingInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jason on 10/10/2017.
 */

@ControllerAdvice
public class CatchException {

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseMessage catchException(RuntimeException runtimeException) {

        if (runtimeException instanceof ErrorException) {
            return PackingInfo.changeException2Message((ErrorException) runtimeException);
        } else {
            runtimeException.printStackTrace();
            return PackingInfo.changeException2Message(new ErrorException(StatusCode.UNKNOWN));
        }

    }

}
