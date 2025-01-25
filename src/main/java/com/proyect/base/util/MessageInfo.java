package com.proyect.base.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageInfo {

    private String message;
    private int status;
    private boolean success;
    private int value;


    public MessageInfo(String message, int status, boolean success, int value) {
        this.message = message;
        this.status = status;
        this.success = success;
        this.value = value;
    }

    public  static   MessageInfo accionCompleta(String message){
        return new MessageInfo(message , 200 , true, 0);
    };

    public static MessageInfo accionFallida(String message, int status , boolean success){
        return new MessageInfo(message , status , false, 0);
    };
}
