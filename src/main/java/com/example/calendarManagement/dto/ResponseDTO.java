package com.example.calendarManagement.dto;

public class ResponseDTO {

    private String message;
    private int code;
    private Object data;
    private Object error;

    public ResponseDTO(String message, int code, Object data, Object error) {
        this.message = message;
        this.code = code;
        this.data = data;
        this.error = error;
    }

    public static ResponseDTO successResponse(String message, int code, Object data){
        return new ResponseDTO(message,code,data,null);
    }

    public static ResponseDTO errorResponse(String message, int code, Object error){
        return new ResponseDTO(message,code,null,error);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

}
