package com.bstay.reservation.domain.service;

import com.bstay.reservation.domain.entity.Reservation;
import com.bstay.reservation.domain.event.ReservationApprovedEvent;
import com.bstay.reservation.domain.event.ReservationCancelledEvent;
import com.bstay.reservation.domain.event.ReservationCreatedEvent;
import com.bstay.reservation.domain.event.ReservationPaidEvent;

public interface ReservationDomainService {
    ReservationCreatedEvent createReservation(Reservation reservation);
    ReservationPaidEvent payForReservation(Reservation reservation);
    ReservationCancelledEvent cancelReservation(Reservation reservation);
    ReservationApprovedEvent approveReservation(Reservation reservation);
}
