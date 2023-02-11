package com.test.superhero.superhero.controller.exceptionhandler;

import com.test.superhero.superhero.api.Exception.EntityNotFoundException;
import com.test.superhero.superhero.api.Exception.InputValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SuperheroRestExceptionHandler {

    private static com.superhero.models.ErrorDTO getErrorDTO(Exception ex, HttpStatus status) {
        com.superhero.models.ErrorDTO errorDTO = new com.superhero.models.ErrorDTO();
        errorDTO.setCode(status.value());
        errorDTO.setDescriptionError(ex.getMessage());
        return errorDTO;
    }

    @ExceptionHandler({EntityNotFoundException.class, InputValidationException.class})
    public ResponseEntity<com.superhero.models.ErrorDTO> handleException(Exception ex) {

        HttpStatus status = ex instanceof EntityNotFoundException ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        
        return ResponseEntity.status(status).body(getErrorDTO(ex, status));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.superhero.models.ErrorDTO> handleUnexpectedException(Exception ex) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        com.superhero.models.ErrorDTO errorDTO = new com.superhero.models.ErrorDTO();
        errorDTO.setCode(status.value());
        errorDTO.setDescriptionError("Unexpected Error");

        return ResponseEntity.status(status).body(errorDTO);
    }


}
