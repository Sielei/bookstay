package com.bstay.reservation.app.service.ports.input.service;

import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.dto.CreateReservationResponse;
import com.bstay.reservation.app.service.dto.FindReservationResponse;

import javax.validation.Valid;
import java.util.UUID;

public interface ReservationApplicationService {

    CreateReservationResponse createReservation(@Valid CreateReservationCommand createReservationCommand);

    FindReservationResponse findReservationById(UUID reservationId);
}
