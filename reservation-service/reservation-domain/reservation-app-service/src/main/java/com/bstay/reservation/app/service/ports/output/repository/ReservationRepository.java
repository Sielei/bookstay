package com.bstay.reservation.app.service.ports.output.repository;

import com.bstay.domain.valueobject.ReservationId;
import com.bstay.reservation.domain.entity.Reservation;

import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(ReservationId reservationId);
}
