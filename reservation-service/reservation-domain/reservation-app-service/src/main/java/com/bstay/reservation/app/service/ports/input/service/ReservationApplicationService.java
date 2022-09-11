package com.bstay.reservation.app.service.ports.input.service;

import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.dto.CreateReservationResponse;

import javax.validation.Valid;

public interface ReservationApplicationService {

    CreateReservationResponse createReservation(@Valid CreateReservationCommand createReservationCommand);
}
