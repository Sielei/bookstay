package com.bstay.reservation.container;

import com.bstay.reservation.domain.service.ReservationDomainService;
import com.bstay.reservation.domain.service.ReservationDomainServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.bstay.data.access")
@EntityScan(basePackages = "com.bstay.data.access")
@SpringBootApplication(scanBasePackages = "com.bstay")
public class ReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Bean
    ReservationDomainService reservationDomainService(){
        return new ReservationDomainServiceImpl();
    }
}
