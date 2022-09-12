package com.bstay.reservation.app.service.domain;

import com.bstay.reservation.app.service.ports.output.repository.ReservationRepository;
import com.bstay.reservation.domain.service.ReservationDomainService;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.bstay")
public class ReservationTestConfiguration {

    @Bean
    ReservationRepository reservationRepository(){
        return Mockito.mock(ReservationRepository.class);
    }

    @Bean
    ReservationDomainService reservationDomainService(){
        return Mockito.mock(ReservationDomainService.class);
    }
}
