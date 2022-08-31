package com.bstay.reservation.domain.event;

import com.bstay.reservation.domain.entity.Reservation;

import java.time.ZonedDateTime;

public class ReservationCancelledEvent extends ReservationEvent{
    public ReservationCancelledEvent(Reservation reservation, ZonedDateTime zonedDateTime) {
        super(reservation, zonedDateTime);
    }
}
