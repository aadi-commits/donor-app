package com.donor.donorapp.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponseDto {

    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private Map<String, String> errors;
    private String path;

    public ErrorResponseDto(LocalDateTime timeStamp, int status, String error,
                            String message, String path){
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public ErrorResponseDto(LocalDateTime timeStamp, int status, String error,
                            String message, Map<String, String> errors, String path){
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.errors = errors;
        this.path = path;
    }

    public LocalDateTime getTimeStamp(){return timeStamp;}

    public int getStatus(){return status;}

    public String getError(){return error;}

    public String getMessage(){return message;}

    public Map<String, String> getErrors(){return errors;}

    public String getPath(){return path;}

}
