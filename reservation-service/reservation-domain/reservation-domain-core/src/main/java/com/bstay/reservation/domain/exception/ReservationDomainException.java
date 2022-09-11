package com.bstay.reservation.domain.exception;

import com.bstay.domain.exception.DomainException;

public class ReservationDomainException extends DomainException {
    public ReservationDomainException(String message) {
        super(message);
    }

    public ReservationDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
