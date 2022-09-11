package com.bstay.reservation.app.service.handler;

import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.dto.CreateReservationResponse;
import com.bstay.reservation.app.service.helper.CreateReservationHelper;
import com.bstay.reservation.app.service.mapper.ReservationDataMapper;
import com.bstay.reservation.domain.event.ReservationCreatedEvent;

public class CreateReservationCommandHandler {

    private final ReservationDataMapper reservationDataMapper;
    private final CreateReservationHelper createReservationHelper;

    public CreateReservationCommandHandler(ReservationDataMapper reservationDataMapper, CreateReservationHelper createReservationHelper) {
        this.reservationDataMapper = reservationDataMapper;
        this.createReservationHelper = createReservationHelper;
    }

    public CreateReservationResponse createReservation(CreateReservationCommand createReservationCommand) {
        ReservationCreatedEvent reservationCreatedEvent = createReservationHelper.persistReservation(createReservationCommand);
        CreateReservationResponse createReservationResponse = reservationDataMapper
                .reservationToCreateReservationResponse(reservationCreatedEvent.getReservation());
        return createReservationResponse;
    }

}
