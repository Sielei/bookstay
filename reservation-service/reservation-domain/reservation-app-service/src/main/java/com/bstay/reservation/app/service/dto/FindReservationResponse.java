package com.bstay.reservation.app.service.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record FindReservationResponse(Date checkinDate, Date checkoutDate, BigDecimal reservationTotal) {
}
