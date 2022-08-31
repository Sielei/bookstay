package com.bstay.reservation.domain.event;

import com.bstay.reservation.domain.entity.Reservation;

import java.time.ZonedDateTime;

public class ReservationApprovedEvent extends ReservationEvent{
    public ReservationApprovedEvent(Reservation reservation, ZonedDateTime zonedDateTime) {
        super(reservation, zonedDateTime);
    }
}
