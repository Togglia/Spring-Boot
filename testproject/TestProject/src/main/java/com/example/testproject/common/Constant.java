package com.example.testproject.common;

public class Constant {
    public enum ExceptionClass{
        PRODUCT("product"), ORDER("order"), PROVIDER("provider");

        private String exceptionClass;
        ExceptionClass(String exceptionClass){
            this.exceptionClass=exceptionClass;
        }
        public String getExceptionClass() {
            return exceptionClass;
        }
        @Override
        public String toString(){
            return getExceptionClass() + "Exception. ";
        }
    }
}
