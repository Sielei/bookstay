package com.bstay.reservation.app.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ReservationItemDto(UUID roomId, UUID roomOption, Integer quantity, BigDecimal cost) {
}
