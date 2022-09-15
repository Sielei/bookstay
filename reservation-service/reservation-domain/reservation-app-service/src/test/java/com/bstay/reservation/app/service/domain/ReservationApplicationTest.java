package com.bstay.reservation.app.service.domain;

import com.bstay.domain.valueobject.ReservationId;
import com.bstay.domain.valueobject.ReservationStatus;
import com.bstay.reservation.app.service.dto.CreateReservationCommand;
import com.bstay.reservation.app.service.dto.CreateReservationResponse;
import com.bstay.reservation.app.service.dto.ReservationItemDto;
import com.bstay.reservation.app.service.mapper.ReservationDataMapper;
import com.bstay.reservation.app.service.ports.input.service.ReservationApplicationService;
import com.bstay.reservation.app.service.ports.output.repository.ReservationRepository;
import com.bstay.reservation.domain.entity.Reservation;
import com.bstay.reservation.domain.exception.ReservationDomainException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = ReservationTestConfiguration.class)
public class ReservationApplicationTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationApplicationService reservationApplicationService;

    @Autowired
    private ReservationDataMapper reservationDataMapper;

    private CreateReservationCommand createReservationCommand;

    private CreateReservationCommand createReservationWithWrongReservationTotalCommand;

    @BeforeAll
    void init(){
        createReservationCommand = CreateReservationCommand.builder()
                .checkinDate(new Date(2022, Calendar.SEPTEMBER, 15))
                .checkoutDate(new Date(2022, Calendar.SEPTEMBER, 22))
                .reservationItemDtos(List.of(
                        ReservationItemDto.builder()
                                .roomId(UUID.fromString("a91cde7b-54e9-4c08-bf51-f344b92bb7e0"))
                                .roomOption(UUID.fromString("0da64ad5-fe6c-4539-9da7-a64be5404eeb"))
                                .quantity(1)
                                .cost(new BigDecimal("50.00"))
                                .build(),
                        ReservationItemDto.builder()
                                .roomId(UUID.fromString("3658454c-c798-41cf-a554-665bf65af448"))
                                .roomOption(UUID.fromString("b0515b6b-1079-4693-9ad7-744e6a282e26"))
                                .quantity(3)
                                .cost(new BigDecimal("240.00"))
                                .build()
                ))
                .reservationTotal(new BigDecimal("290.00"))
                .build();

        createReservationWithWrongReservationTotalCommand = CreateReservationCommand.builder()
                .checkinDate(new Date(2022, Calendar.SEPTEMBER, 15))
                .checkoutDate(new Date(2022, Calendar.SEPTEMBER, 22))
                .reservationItemDtos(List.of(
                        ReservationItemDto.builder()
                                .roomId(UUID.fromString("a91cde7b-54e9-4c08-bf51-f344b92bb7e0"))
                                .roomOption(UUID.fromString("0da64ad5-fe6c-4539-9da7-a64be5404eeb"))
                                .quantity(1)
                                .cost(new BigDecimal("50.00"))
                                .build(),
                        ReservationItemDto.builder()
                                .roomId(UUID.fromString("3658454c-c798-41cf-a554-665bf65af448"))
                                .roomOption(UUID.fromString("b0515b6b-1079-4693-9ad7-744e6a282e26"))
                                .quantity(3)
                                .cost(new BigDecimal("80.00"))
                                .build()
                ))
                .reservationTotal(new BigDecimal("200.00"))
                .build();

        Reservation reservation = reservationDataMapper.createReservationCommandToReservation(createReservationCommand);
        reservation.setId(new ReservationId(UUID.fromString("158a17aa-7d93-4348-b72b-c4ab6f9f56db")));

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
    }

    @Test
    @DisplayName("Test Create Reservation")
    void testCreateReservation(){
        CreateReservationResponse createReservationResponse = reservationApplicationService.createReservation(
                createReservationCommand);
        System.out.println(createReservationResponse);
        assertEquals(createReservationResponse.reservationStatus(), ReservationStatus.INITIAL,
                "Created reservation should be in INITIAL state");
        assertEquals(createReservationResponse.message(), "Reservation created successfully.");
    }

    @Test
    @DisplayName("Test creation of a Reservation with wrong reservation total")
    void testReservationWithWrongReservationTotal(){
        assertThrows(ReservationDomainException.class, () -> {
            reservationApplicationService.createReservation(createReservationWithWrongReservationTotalCommand);
        });
    }
}
