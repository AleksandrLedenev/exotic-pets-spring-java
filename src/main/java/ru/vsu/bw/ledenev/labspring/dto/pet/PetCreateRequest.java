package ru.vsu.bw.ledenev.labspring.dto.pet;

public class PetCreateRequest {
    private String name;
    private int strength;

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
}
