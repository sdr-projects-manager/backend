package sdrprojectsmanager.sdr.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorDetailsArray {
    private Date timestamp;
    private String message;
    private Integer status;
    private List<String> details;


    public ErrorDetailsArray(Date timestamp, String message, List<String> detailsError, Integer status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = detailsError;
        this.status = status;

    }
}
