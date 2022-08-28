package com.bstay.domain.valueobject;

import java.util.UUID;

public class ReservationId extends BaseId<UUID> {
    public ReservationId(UUID value) {
        super(value);
    }
}
