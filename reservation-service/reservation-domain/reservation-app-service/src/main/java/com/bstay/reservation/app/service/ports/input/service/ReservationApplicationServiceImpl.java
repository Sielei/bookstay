package com.bstay.reservation.app.service.ports.input.service;

import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.dto.CreateReservationResponse;
import com.bstay.reservation.app.service.dto.FindReservationResponse;
import com.bstay.reservation.app.service.handler.CreateReservationCommandHandler;
import com.bstay.reservation.app.service.handler.FindReservationHandler;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService{

    private final CreateReservationCommandHandler createReservationCommandHandler;
    private final FindReservationHandler findReservationHandler;

    public ReservationApplicationServiceImpl(CreateReservationCommandHandler createReservationCommandHandler, FindReservationHandler findReservationHandler) {
        this.createReservationCommandHandler = createReservationCommandHandler;
        this.findReservationHandler = findReservationHandler;
    }

    @Override
    public CreateReservationResponse createReservation(CreateReservationCommand createReservationCommand) {
        return createReservationCommandHandler.createReservation(createReservationCommand);
    }

    @Override
    public FindReservationResponse findReservationById(UUID reservationId) {
        return findReservationHandler.findReservationById(reservationId);
    }
}
