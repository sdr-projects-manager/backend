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
    private Integer code;

    public ErrorDetails(Date timestamp, String message, String details, Integer code) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.code = code;
    }
}