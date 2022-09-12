package com.bstay.data.access.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter @Setter @ToString @RequiredArgsConstructor
@AllArgsConstructor @NoArgsConstructor @Builder
public class ReservationEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;


}