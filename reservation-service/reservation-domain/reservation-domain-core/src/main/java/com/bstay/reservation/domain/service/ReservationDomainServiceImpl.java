package com.bstay.reservation.domain.service;

import com.bstay.reservation.domain.entity.Reservation;
import com.bstay.reservation.domain.event.ReservationApprovedEvent;
import com.bstay.reservation.domain.event.ReservationCancelledEvent;
import com.bstay.reservation.domain.event.ReservationCreatedEvent;
import com.bstay.reservation.domain.event.ReservationPaidEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ReservationDomainServiceImpl implements ReservationDomainService{
    @Override
    public ReservationCreatedEvent createReservation(Reservation reservation) {
        reservation.makeReservation();
        return new ReservationCreatedEvent(reservation, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public ReservationPaidEvent payForReservation(Reservation reservation) {
        reservation.payForReservation();
        return new ReservationPaidEvent(reservation, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public ReservationCancelledEvent cancelReservation(Reservation reservation) {
        reservation.cancelReservation();
        return new ReservationCancelledEvent(reservation, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public ReservationApprovedEvent approveReservation(Reservation reservation) {
        reservation.approveReservation();
        return new ReservationApprovedEvent(reservation, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
