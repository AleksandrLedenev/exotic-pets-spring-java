package ru.vsu.bw.ledenev.labspring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.bw.ledenev.labspring.dto.profile.CreateProfileResponse;
import ru.vsu.bw.ledenev.labspring.dto.profile.CreateProfileRequest;
import ru.vsu.bw.ledenev.labspring.entity.Profile;
import ru.vsu.bw.ledenev.labspring.service.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@Valid @RequestBody CreateProfileRequest createProfileRequest) {
        Profile createdProfile = profileService.createProfile(createProfileRequest);
        if (createdProfile == null) {
            return ResponseEntity.badRequest().body("Пользователь уже существует");
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateProfileResponse(createdProfile.getId().toString(), createdProfile.getLogin()));
    }
}