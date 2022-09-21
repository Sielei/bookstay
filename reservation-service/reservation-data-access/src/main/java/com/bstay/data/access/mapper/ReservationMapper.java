package com.bstay.data.access.mapper;

import com.bstay.data.access.entity.ReservationEntity;
import com.bstay.data.access.entity.ReservationItemEntity;
import com.bstay.domain.valueobject.Money;
import com.bstay.domain.valueobject.ReservationId;
import com.bstay.domain.valueobject.RoomId;
import com.bstay.domain.valueobject.RoomOptionId;
import com.bstay.reservation.domain.entity.Reservation;
import com.bstay.reservation.domain.entity.ReservationItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
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
                        .roomId(reservationItem.getRoomId().getValue())
                        .roomOption(reservationItem.getRoomOptionId().getValue())
                        .quantity(reservationItem.getQuantity())
                        .cost(reservationItem.getCost().getAmount())
                        .build())
                .collect(Collectors.toList());
    }

    public Reservation reservationEntityToReservation(ReservationEntity reservationEntity) {
        return Reservation.builder()
                .reservationId(new ReservationId(reservationEntity.getId()))
                .checkInDate(reservationEntity.getCheckinDate())
                .checkoutDate(reservationEntity.getCheckoutDate())
                .reservationItems(reservationItemsEntityToReservationItems(reservationEntity.getReservationItems()))
                .reservationTotal(new Money(reservationEntity.getReservationTotal()))
                .build();
    }

    private List<ReservationItem> reservationItemsEntityToReservationItems(List<ReservationItemEntity> reservationItems) {
        return reservationItems.stream()
                .map(reservationItemEntity -> ReservationItem.builder()
                        .roomId(new RoomId(reservationItemEntity.getRoomId()))
                        .roomOptionId(new RoomOptionId(reservationItemEntity.getRoomOption()))
                        .quantity(reservationItemEntity.getQuantity())
                        .cost(new Money(reservationItemEntity.getCost()))
                        .build())
                .collect(Collectors.toList());
    }
}
