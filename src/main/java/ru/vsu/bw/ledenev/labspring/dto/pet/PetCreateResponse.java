package ru.vsu.bw.ledenev.labspring.dto.pet;

import java.util.UUID;

public class PetCreateResponse {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PetCreateResponse(UUID id) {
        this.id = id;
    }
}

