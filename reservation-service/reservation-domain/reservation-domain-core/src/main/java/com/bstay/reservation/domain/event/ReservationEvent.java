package com.bstay.reservation.domain.event;

import com.bstay.domain.event.DomainEvent;
import com.bstay.reservation.domain.entity.Reservation;

import java.time.ZonedDateTime;

public abstract class ReservationEvent implements DomainEvent<Reservation> {

    private final Reservation reservation;
    private final ZonedDateTime zonedDateTime;

    public ReservationEvent(Reservation reservation, ZonedDateTime zonedDateTime) {
        this.reservation = reservation;
        this.zonedDateTime = zonedDateTime;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
