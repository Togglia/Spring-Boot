package com.example.testproject.common.exception;

import com.example.testproject.common.Constant;
import org.springframework.http.HttpStatus;

public class TestProjectException extends Exception{

    private static final long serialVersionUID = 4663380430591151674L;//이게뭐지? 직렬화?

    private Constant.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public TestProjectException(Constant.ExceptionClass exceptionClass, HttpStatus httpStatus, String message){
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constant.ExceptionClass getExceptionClass(){
        return exceptionClass;
    }
    public int getHttpStatusCode(){
        return httpStatus.value();
    }
    public String getHttpStatusType(){
        return httpStatus.getReasonPhrase();
    }
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
