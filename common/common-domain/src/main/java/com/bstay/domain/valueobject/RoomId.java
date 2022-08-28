package com.bstay.domain.valueobject;

import java.util.UUID;

public class RoomId extends BaseId<UUID> {
    public RoomId(UUID value) {
        super(value);
    }
}
