package com.bstay.data.access.mapper;

import com.bstay.data.access.entity.ReservationEntity;
import com.bstay.data.access.entity.ReservationItemEntity;
import com.bstay.reservation.domain.entity.Reservation;
import com.bstay.reservation.domain.entity.ReservationItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {
    public ReservationEntity reservationToReservationEntity(Reservation reservation) {
        return ReservationEntity.builder()
                .id(reservation.getId().getValue())
                .checkinDate(reservation.getCheckInDate())
                .checkoutDate(reservation.getCheckoutDate())
                .reservationItems(reservationItemsToReservationItemsEntity(reservation.getReservationItems()))
                .reservationTotal(reservation.getReservationTotal().getAmount())
                .reservationStatus(reservation.getReservationStatus())
                .build();
    }

    private List<ReservationItemEntity> reservationItemsToReservationItemsEntity(List<ReservationItem> reservationItems) {
        return reservationItems.stream()
                .map(reservationItem -> ReservationItemEntity.builder()
                        .id(reservationItem.getId().getValue())
                        .reservation()
                        .roomId(reservationItem.getRoomId().getValue())
                        .roomOption(reservationItem.getRoomOptionId().getValue())
                        .quantity(reservationItem.getQuantity())
                        .cost(reservationItem.getCost().getAmount())
                        .build())
                .collect(Collectors.toList());
    }
}
