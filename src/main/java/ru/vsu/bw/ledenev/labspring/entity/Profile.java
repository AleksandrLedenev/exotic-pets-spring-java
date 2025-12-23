package ru.vsu.bw.ledenev.labspring.entity;

import java.util.UUID;

public class Profile {
    private UUID id;
    private String login;
    private String password;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
