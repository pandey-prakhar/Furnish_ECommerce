package com.productservice.products.exeptionhandler;

import com.productservice.products.dtos.ExeptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExeptionDto> handleArithmeticException(){
        ExeptionDto exeptionDto = new ExeptionDto();
        exeptionDto.setMessage("Arithmetic exception");
        exeptionDto.setResolutionMessage("Please check your code if there is a division by 0, and debug it.");
        return new ResponseEntity<>(exeptionDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
