package com.bstay.reservation.domain.exception;

import com.bstay.domain.exception.DomainException;

public class ReservationException extends DomainException {
    public ReservationException(String message) {
        super(message);
    }

    public ReservationException(String message, Throwable cause) {
        super(message, cause);
    }
}
