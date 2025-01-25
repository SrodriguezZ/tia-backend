package com.proyect.base.config.advice.globalexception;

import com.proyect.base.config.advice.clasesexception.CustomException;
import com.proyect.base.config.advice.clasesexception.ResourceNotFoundException;
import com.proyect.base.util.MessageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public MessageInfo handleException(Exception ex){
        return  MessageInfo.accionFallida("Error interno del servidor" + ex.getMessage() , 500 , false);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageInfo handleCustomException(CustomException ex) {
        return MessageInfo.accionFallida(ex.getMessage(), ex.getStatusCode(), false);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageInfo handleResourceNotFoundException(ResourceNotFoundException ex) {
        return MessageInfo.accionFallida(ex.getMessage(), ex.getStatusCode(), false);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageInfo handleValidationException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (fieldError != null) {
            String message = String.format("Error de validación en el campo '%s': %s",
                    fieldError.getField(), fieldError.getDefaultMessage());
            return MessageInfo.accionFallida(message, HttpStatus.BAD_REQUEST.value(), false);
        }
        return MessageInfo.accionFallida("Error de validación desconocido", HttpStatus.BAD_REQUEST.value(), false);
    }
}
