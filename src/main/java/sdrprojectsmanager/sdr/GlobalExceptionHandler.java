package sdrprojectsmanager.sdr;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import sdrprojectsmanager.sdr.exception.APIException;
import sdrprojectsmanager.sdr.exception.ErrorDetails;
import sdrprojectsmanager.sdr.exception.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {


    //Handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false), status.value());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }
    //Handle Api exceptions
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException(APIException exception,  HttpHeaders headers,
                                                HttpStatus status, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "APIException", request.getDescription(false), status.value());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    //Handle bad request exception
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleBadRequestException(ConstraintViolationException exception,  HttpHeaders headers,
                                                       HttpStatus status, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Request exception", request.getDescription(false), status.value());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //Handle bad request exception
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityException(EntityNotFoundException exception,  HttpHeaders headers,
                                                   HttpStatus status, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Request exception", request.getDescription(false), status.value());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    //Handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception,  HttpHeaders headers,
                                                   HttpStatus status, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal server error", request.getDescription(false), status.value());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Handle HttpMediaTypeNotSupportedException

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e,  HttpHeaders headers,
                                                   HttpStatus status, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Unsupported media type", request.getDescription(false), status.value());
        return new ResponseEntity(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


    // error handle for @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentException(MethodArgumentNotValidException ex,  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("error", errors);

        return new ResponseEntity<>(body, headers, status);
    }
}
