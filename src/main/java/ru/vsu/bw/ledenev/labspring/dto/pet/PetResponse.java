package ru.vsu.bw.ledenev.labspring.dto.pet;

import java.util.UUID;

public class PetResponse {
    private String name;
    private int strength;
    private UUID profileId;

    public PetResponse(String name, int strength, UUID profileId) {
        this.name = name;
        this.strength = strength;
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }
}
