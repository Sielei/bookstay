package com.bstay.reservation.app.service.ports.input.service;

import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.dto.CreateReservationResponse;
import com.bstay.reservation.app.service.handler.CreateReservationCommandHandler;
import org.springframework.stereotype.Service;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService{

    private final CreateReservationCommandHandler createReservationCommandHandler;

    public ReservationApplicationServiceImpl(CreateReservationCommandHandler createReservationCommandHandler) {
        this.createReservationCommandHandler = createReservationCommandHandler;
    }

    @Override
    public CreateReservationResponse createReservation(CreateReservationCommand createReservationCommand) {
        return createReservationCommandHandler.createReservation(createReservationCommand);
    }
}
