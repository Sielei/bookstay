package com.bstay.reservation.app.service.handler;

import com.bstay.domain.valueobject.ReservationId;
import com.bstay.reservation.app.service.dto.FindReservationResponse;
import com.bstay.reservation.app.service.mapper.ReservationDataMapper;
import com.bstay.reservation.app.service.ports.output.repository.ReservationRepository;
import com.bstay.reservation.domain.entity.Reservation;
import com.bstay.reservation.domain.exception.ReservationDomainException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class FindReservationHandler {

    private final ReservationDataMapper reservationDataMapper;

    private final ReservationRepository reservationRepository;

    public FindReservationHandler(ReservationDataMapper reservationDataMapper, ReservationRepository reservationRepository) {
        this.reservationDataMapper = reservationDataMapper;
        this.reservationRepository = reservationRepository;
    }

    public FindReservationResponse findReservationById(UUID reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(new ReservationId(reservationId));
        if (reservationOptional.isEmpty()){
            throw new ReservationDomainException("Reservation with id: " +reservationId+" does not exist");
        }
        return reservationDataMapper.reservationToFindReservationResponse(reservationOptional.get());
    }
}
