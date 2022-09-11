package com.bstay.reservation.app.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record CreateReservationCommand(Date checkinDate, Date checkoutDate,
                                       List<ReservationItemDto> reservationItemDtos, BigDecimal reservationTotal) {

}
