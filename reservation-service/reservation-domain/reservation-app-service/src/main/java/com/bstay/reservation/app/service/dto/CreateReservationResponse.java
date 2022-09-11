package com.bstay.reservation.app.service.dto;

import com.bstay.domain.valueobject.ReservationStatus;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
public record CreateReservationResponse(@NotNull ReservationStatus reservationStatus, @NotNull String message) {
}
