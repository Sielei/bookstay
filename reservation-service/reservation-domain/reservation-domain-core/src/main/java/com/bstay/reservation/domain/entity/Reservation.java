package com.bstay.reservation.domain.entity;

import com.bstay.domain.entity.AggregateRoot;
import com.bstay.domain.valueobject.Money;
import com.bstay.domain.valueobject.ReservationId;
import com.bstay.domain.valueobject.ReservationItemId;
import com.bstay.domain.valueobject.ReservationStatus;
import com.bstay.reservation.domain.exception.ReservationDomainException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Reservation extends AggregateRoot<ReservationId> {

    private final Date checkInDate;
    private final Date checkoutDate;
    private final List<ReservationItem> reservationItems;
    private final Money reservationTotal;
    private ReservationStatus reservationStatus;

    private Reservation(Builder builder) {
        super.setId(builder.reservationId);
        checkInDate = builder.checkInDate;
        checkoutDate = builder.checkoutDate;
        reservationItems = builder.reservationItems;
        reservationTotal = builder.reservationTotal;
        reservationStatus = builder.reservationStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public List<ReservationItem> getReservationItems() {
        return reservationItems;
    }

    public Money getReservationTotal() {
        return reservationTotal;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void makeReservation(){
        setId(new ReservationId(UUID.randomUUID()));
        reservationStatus = ReservationStatus.INITIAL;
        initializeReservationItems();
    }

    private void initializeReservationItems() {
        long id = 1;
        for(ReservationItem reservationItem: reservationItems){
            reservationItem.initialize(super.getId(), new ReservationItemId(id++));
        }
    }

    public void cancelReservation(){
        if (reservationStatus == ReservationStatus.RESERVED){
            throw new ReservationDomainException("Reservation cannot be cancelled at this stage.");
        }
        reservationStatus = ReservationStatus.CANCELLED;
    }
    public void payForReservation(){
        if (reservationStatus != ReservationStatus.PENDING){
            throw new ReservationDomainException("Cannot pay for reservation at this stage.");
        }
        reservationStatus = ReservationStatus.PAID;
    }

    public void approveReservation(){
        if (reservationStatus != ReservationStatus.PAID){
            throw new ReservationDomainException("Cannot Approve reservation at this stage.");
        }
        reservationStatus = ReservationStatus.APPROVED;
    }

    public static final class Builder {
        private ReservationId reservationId;
        private Date checkInDate;
        private Date checkoutDate;
        private List<ReservationItem> reservationItems;
        private Money reservationTotal;
        private ReservationStatus reservationStatus;

        private Builder() {
        }

        public Builder reservationId(ReservationId val) {
            reservationId = val;
            return this;
        }

        public Builder checkInDate(Date val) {
            checkInDate = val;
            return this;
        }

        public Builder checkoutDate(Date val) {
            checkoutDate = val;
            return this;
        }

        public Builder reservationItems(List<ReservationItem> val) {
            reservationItems = val;
            return this;
        }

        public Builder reservationTotal(Money val) {
            reservationTotal = val;
            return this;
        }

        public Builder reservationStatus(ReservationStatus val) {
            reservationStatus = val;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }
}
