package com.bstay.reservation.app.exception.handler;

import com.bstay.common.app.handler.ErrorDto;
import com.bstay.common.app.handler.GlobalExceptionHandler;
import com.bstay.reservation.domain.exception.ReservationDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalReservationExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ReservationDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(ReservationDomainException reservationDomainException) {
        return ErrorDto.builder()
                .errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorMessage(reservationDomainException.getMessage())
                .build();
    }
}
