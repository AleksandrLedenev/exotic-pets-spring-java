package ru.vsu.bw.ledenev.labspring.dto.profile;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateProfileRequest {
    @NotBlank
    @Size(min = 6, message = "Логин должен быть длинее 6 символов")
    private String login;
    @Size(min = 6, message = "Пароль должен быть длинее 6 символов")
    private String password;

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
