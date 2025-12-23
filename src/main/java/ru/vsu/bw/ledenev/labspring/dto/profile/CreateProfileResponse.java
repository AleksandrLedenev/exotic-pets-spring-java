package ru.vsu.bw.ledenev.labspring.dto.profile;

public class CreateProfileResponse {
    private String id;
    private String login;

    public CreateProfileResponse(String id, String login) {
        this.id = id;
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
