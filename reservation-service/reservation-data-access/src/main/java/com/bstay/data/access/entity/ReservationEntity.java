package com.bstay.data.access.entity;

import com.bstay.domain.valueobject.ReservationStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    private Date checkinDate;
    private Date checkoutDate;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationItemEntity> reservationItems;

    private BigDecimal reservationTotal;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservationEntity that = (ReservationEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}