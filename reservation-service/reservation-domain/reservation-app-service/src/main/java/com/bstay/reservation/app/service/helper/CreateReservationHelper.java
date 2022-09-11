package com.bstay.reservation.app.service.helper;

import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.mapper.ReservationDataMapper;
import com.bstay.reservation.app.service.ports.output.repository.ReservationRepository;
import com.bstay.reservation.domain.entity.Reservation;
import com.bstay.reservation.domain.event.ReservationCreatedEvent;
import com.bstay.reservation.domain.exception.ReservationDomainException;
import com.bstay.reservation.domain.service.ReservationDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class CreateReservationHelper {

    private final ReservationDataMapper reservationDataMapper;
    private final ReservationDomainService reservationDomainService;

    private final ReservationRepository reservationRepository;

    public CreateReservationHelper(ReservationDataMapper reservationDataMapper, ReservationDomainService reservationDomainService,
                                   ReservationRepository reservationRepository) {
        this.reservationDataMapper = reservationDataMapper;
        this.reservationDomainService = reservationDomainService;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public ReservationCreatedEvent persistReservation(CreateReservationCommand createReservationCommand) {
        Reservation newReservation = reservationDataMapper.createReservationCommandToReservation(createReservationCommand);
        ReservationCreatedEvent reservationCreatedEvent = reservationDomainService.createReservation(newReservation);
        log.info("New reservation created with id: {}", reservationCreatedEvent.getReservation().getId().getValue());
        saveReservation(newReservation);
        return reservationCreatedEvent;
    }

    private void saveReservation(Reservation reservation) {
        Reservation reservationSaveResult = reservationRepository.save(reservation);
        if (reservationSaveResult == null){
            log.error("Could not save Reservation!");
            throw new ReservationDomainException("Could not save reservation!");
        }
        log.info("Reservation saved with id: {}", reservationSaveResult.getId().getValue());
    }
}
