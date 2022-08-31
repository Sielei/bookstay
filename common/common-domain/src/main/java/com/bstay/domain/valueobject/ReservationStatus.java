package com.bstay.domain.valueobject;

public enum ReservationStatus {
    INITIAL("Initial Reservation"), PENDING("Pending Payment"), PAID("Paid"),
    APPROVED("Reservation Approved"), RESERVED("Reserved"), CANCELLED("Cancelled");


    private final String value;
    ReservationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
