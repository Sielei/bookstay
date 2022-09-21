package com.bstay.reservation.app.controller.rest;

import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.dto.CreateReservationResponse;
import com.bstay.reservation.app.service.ports.input.service.ReservationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationApplicationService reservationApplicationService;
    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservationResponse(@RequestBody CreateReservationCommand createReservationCommand){
        CreateReservationResponse reservationResponse = reservationApplicationService.createReservation(createReservationCommand);
        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }
}
