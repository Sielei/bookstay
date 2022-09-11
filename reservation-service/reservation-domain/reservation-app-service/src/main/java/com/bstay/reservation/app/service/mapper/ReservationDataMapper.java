package com.bstay.reservation.app.service.mapper;

import com.bstay.domain.valueobject.Money;
import com.bstay.domain.valueobject.RoomId;
import com.bstay.domain.valueobject.RoomOptionId;
import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.dto.CreateReservationResponse;
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
                .reservationItems(reservationItemDtosToReservationItems(createReservationCommand.reservationItemDtos()))
                .reservationTotal(new Money(createReservationCommand.reservationTotal()))
                .build();
    }

    private List<ReservationItem> reservationItemDtosToReservationItems(List<ReservationItemDto> reservationItemDtos) {
        return reservationItemDtos.stream()
                .map(reservationItemDto -> ReservationItem.builder()
                        .roomId(new RoomId(reservationItemDto.roomId()))
                        .roomOptionId(new RoomOptionId(reservationItemDto.roomId()))
                        .quantity(reservationItemDto.quantity())
                        .cost(new Money(reservationItemDto.cost()))
                        .build())
                .collect(Collectors.toList());
    }
}
