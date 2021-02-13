package sdrprojectsmanager.sdr.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private Integer status;
    private Map detailsError;

    public ErrorDetails(Date timestamp, String message, String details, Integer status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }
    public ErrorDetails(Date timestamp, String message, Map detailsError, Integer status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.detailsError = detailsError;
        this.status = status;

    }
}