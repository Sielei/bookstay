package com.bstay.reservation.domain.event;

import com.bstay.reservation.domain.entity.Reservation;

import java.time.ZonedDateTime;

public class ReservationCreatedEvent extends ReservationEvent{
    public ReservationCreatedEvent(Reservation reservation, ZonedDateTime zonedDateTime) {
        super(reservation, zonedDateTime);
    }
}
