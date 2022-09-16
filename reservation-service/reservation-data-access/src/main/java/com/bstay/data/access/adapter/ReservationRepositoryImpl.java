package com.bstay.data.access.adapter;

import com.bstay.domain.valueobject.ReservationId;
import com.bstay.reservation.app.service.ports.output.repository.ReservationRepository;
import com.bstay.reservation.domain.entity.Reservation;

import java.util.Optional;

public class ReservationRepositoryImpl implements ReservationRepository {
    @Override
    public Reservation save(Reservation reservation) {
        return null;
    }

    @Override
    public Optional<Reservation> findById(ReservationId reservationId) {
        return Optional.empty();
    }
}
