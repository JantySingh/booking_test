package com.tui.proof.ws;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Author: Janty
 */

@Data
public class BookingServiceException  extends Exception {
    private final HttpStatus httpStatus;
    public BookingServiceException(HttpStatus httpStatus, Throwable cause) {
        super(cause);
        this.httpStatus = httpStatus;
    }
}
