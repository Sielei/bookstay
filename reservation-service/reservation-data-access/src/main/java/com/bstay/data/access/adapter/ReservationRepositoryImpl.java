package com.bstay.data.access.adapter;

import com.bstay.data.access.entity.ReservationEntity;
import com.bstay.data.access.mapper.ReservationMapper;
import com.bstay.data.access.repository.ReservationJpaRepository;
import com.bstay.domain.valueobject.ReservationId;
import com.bstay.reservation.app.service.ports.output.repository.ReservationRepository;
import com.bstay.reservation.domain.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationMapper reservationMapper;

    private final ReservationJpaRepository reservationJpaRepository;
    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity newReservation = reservationMapper.reservationToReservationEntity(reservation);
        ReservationEntity savedReservation = reservationJpaRepository.save(newReservation);
        return reservationMapper.reservationEntityToReservation(savedReservation);
    }

    @Override
    public Optional<Reservation> findById(ReservationId reservationId) {
        Optional<ReservationEntity> reservationEntity = reservationJpaRepository.findById(reservationId.getValue());
        return reservationEntity.map(reservationMapper::reservationEntityToReservation);
    }
}
