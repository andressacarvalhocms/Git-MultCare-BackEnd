package br.edu.ufersa.multcare.web.errors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e) {
        List<String> errors = Collections.singletonList(e.getMessage());
        ErrorResponse error = new ErrorResponse("Server Error", errors, 500L);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleAllExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = getErrors(ex);
        ErrorResponse error = new ErrorResponse("Server Error", errors, 500L);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<String> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
    }

}