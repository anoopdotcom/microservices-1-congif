package com.Payment.RatingService.Exception;

import com.Payment.RatingService.Model.CustomErrorObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorObject> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        CustomErrorObject err = new CustomErrorObject();
        err.setStatusCode(HttpStatus.NOT_FOUND.value());
        err.setMessage(exception.getMessage());

        return new ResponseEntity<CustomErrorObject>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomErrorObject> handleBadRequestException(BadRequestException exception, WebRequest request){
        CustomErrorObject errorObj = new CustomErrorObject();
        errorObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObj.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorObj, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<CustomErrorObject> handleForbiddenException(ForbiddenException exception, WebRequest request){
        CustomErrorObject err = new CustomErrorObject();
        err.setStatusCode(HttpStatus.FORBIDDEN.value());
        err.setMessage(exception.getMessage());
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<CustomErrorObject> handleUnauthorizedException(UnauthorizedException exception, WebRequest request){
        CustomErrorObject err = new CustomErrorObject();
        err.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        err.setMessage(exception.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<CustomErrorObject> handleInternalServerErrorException(InternalServerErrorException exception, WebRequest request) {
        CustomErrorObject err = new CustomErrorObject();
        err.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setMessage(exception.getMessage());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomErrorObject> handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException exception, WebRequest request) {

        CustomErrorObject err = new CustomErrorObject();
        err.setStatusCode(HttpStatus.BAD_REQUEST.value());
        err.setMessage(exception.getMessage());

        return new ResponseEntity<CustomErrorObject>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorObject> handleGeneralException(Exception exception, WebRequest request) {

        CustomErrorObject err = new CustomErrorObject();
        err.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setMessage(exception.getMessage());

        return new ResponseEntity<CustomErrorObject>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> body = new HashMap<String, Object>();

        body.put("statusCode", HttpStatus.BAD_REQUEST.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("messages", errors);

        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }

}
