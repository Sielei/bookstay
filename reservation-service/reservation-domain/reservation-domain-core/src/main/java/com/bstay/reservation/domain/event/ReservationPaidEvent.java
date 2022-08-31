package com.bstay.reservation.domain.event;

import com.bstay.reservation.domain.entity.Reservation;

import java.time.ZonedDateTime;

public class ReservationPaidEvent extends ReservationEvent{
    public ReservationPaidEvent(Reservation reservation, ZonedDateTime zonedDateTime) {
        super(reservation, zonedDateTime);
    }
}
