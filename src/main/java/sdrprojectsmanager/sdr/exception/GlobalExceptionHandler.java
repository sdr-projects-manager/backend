//package sdrprojectsmanager.sdr.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.HttpMediaTypeNotSupportedException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//import javax.persistence.EntityNotFoundException;
//import javax.validation.ConstraintViolationException;
//import java.util.Date;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    // Handle specific exceptions
//
//
//    // Handle Api exceptions
//    @ExceptionHandler(APIException.class)
//    public ResponseEntity<?> handleAPIException(APIException exception, HttpHeaders headers, HttpStatus status,
//            WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), "APIException", request.getDescription(false),
//                status.value());
//        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
//    }
//
//    // Handle bad request exception
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<?> handleBadRequestException(ConstraintViolationException exception, HttpHeaders headers,
//            HttpStatus status, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Request exception", request.getDescription(false),
//                status.value());
//        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
//    }
//
//    // Handle bad request exception
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<?> handleEntityException(EntityNotFoundException exception, HttpHeaders headers,
//            HttpStatus status, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Request exception", request.getDescription(false),
//                status.value());
//        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
//    }
//
//    // Handle global exceptions
////    @ExceptionHandler(Exception.class)
////    public ResponseEntity<?> handleGlobalException(Exception exception, HttpHeaders headers, HttpStatus status,
////            WebRequest request) {
////        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal server error", request.getDescription(false),
////                status.value());
////        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
////    }
//
//    // Handle HttpMediaTypeNotSupportedException
//
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<?> handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e,
//            HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Unsupported media type",
//                request.getDescription(false), status.value());
//        return new ResponseEntity(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//    }
//
//}
