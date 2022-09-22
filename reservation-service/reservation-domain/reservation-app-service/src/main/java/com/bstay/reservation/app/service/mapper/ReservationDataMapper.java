package com.bstay.reservation.app.service.mapper;

import com.bstay.domain.valueobject.Money;
import com.bstay.domain.valueobject.RoomId;
import com.bstay.domain.valueobject.RoomOptionId;
import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.dto.CreateReservationResponse;
import com.bstay.reservation.app.service.dto.FindReservationResponse;
import com.bstay.reservation.app.service.dto.ReservationItemDto;
import com.bstay.reservation.domain.entity.Reservation;
import com.bstay.reservation.domain.entity.ReservationItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationDataMapper {
    public CreateReservationResponse reservationToCreateReservationResponse(Reservation reservation) {
        return CreateReservationResponse.builder()
                .reservationStatus(reservation.getReservationStatus())
                .message("Reservation created successfully.")
                .build();
    }

    public Reservation createReservationCommandToReservation(CreateReservationCommand createReservationCommand) {
        return Reservation.builder()
                .checkInDate(createReservationCommand.checkinDate())
                .checkoutDate(createReservationCommand.checkoutDate())
                .reservationItems(reservationItemDtosToReservationItems(createReservationCommand.reservationItems()))
                .reservationTotal(new Money(createReservationCommand.reservationTotal()))
                .build();
    }

    private List<ReservationItem> reservationItemDtosToReservationItems(List<ReservationItemDto> reservationItems) {
        return reservationItems.stream()
                .map(reservationItemDto -> ReservationItem.builder()
                        .roomId(new RoomId(reservationItemDto.roomId()))
                        .roomOptionId(new RoomOptionId(reservationItemDto.roomOption()))
                        .quantity(reservationItemDto.quantity())
                        .cost(new Money(reservationItemDto.cost()))
                        .build())
                .collect(Collectors.toList());
    }

    public FindReservationResponse reservationToFindReservationResponse(Reservation reservation) {
        return FindReservationResponse.builder()
                .checkinDate(reservation.getCheckInDate())
                .checkoutDate(reservation.getCheckoutDate())
                .reservationTotal(reservation.getReservationTotal().getAmount())
                .build();
    }
}
