package com.bstay.reservation.domain.entity;


import com.bstay.domain.entity.BaseEntity;
import com.bstay.domain.valueobject.*;

public class ReservationItem extends BaseEntity<ReservationItemId> {

    private ReservationId reservationId;
    private final RoomId roomId;
    private final RoomOptionId roomOptionId;
    private final int quantity;
    private final Money cost;

    void initialize(ReservationId reservationId, ReservationItemId reservationItemId){
        this.reservationId = reservationId;
        super.setId(reservationItemId);
    }

    private ReservationItem(Builder builder) {
        super.setId(builder.reservationItemId);
        reservationId = builder.reservationId;
        roomId = builder.roomId;
        roomOptionId = builder.roomOptionId;
        quantity = builder.quantity;
        cost = builder.cost;
    }

    public static Builder builder() {
        return new Builder();
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public RoomOptionId getRoomOptionId() {
        return roomOptionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getCost() {
        return cost;
    }

    public ReservationId getReservationId() {
        return reservationId;
    }

    public static final class Builder {
        public ReservationId reservationId;
        private ReservationItemId reservationItemId;
        private RoomId roomId;
        private RoomOptionId roomOptionId;
        private int quantity;
        private Money cost;

        private Builder() {
        }

        public Builder reservationItemId(ReservationItemId val) {
            reservationItemId = val;
            return this;
        }

        public Builder reservationId(ReservationId val){
            reservationId = val;
            return this;
        }

        public Builder roomId(RoomId val) {
            roomId = val;
            return this;
        }

        public Builder roomOptionId(RoomOptionId val) {
            roomOptionId = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder cost(Money val) {
            cost = val;
            return this;
        }

        public ReservationItem build() {
            return new ReservationItem(this);
        }
    }
}
