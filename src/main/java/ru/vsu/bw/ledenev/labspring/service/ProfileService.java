package ru.vsu.bw.ledenev.labspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.bw.ledenev.labspring.dto.profile.CreateProfileRequest;
import ru.vsu.bw.ledenev.labspring.entity.Profile;
import ru.vsu.bw.ledenev.labspring.repository.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Optional<Profile> findByLogin(String login) {
        return profileRepository.findByLogin(login);
    }

    public Profile createProfile(CreateProfileRequest profileRequest) {
        Optional<Profile> existing = profileRepository.findByLogin(profileRequest.getLogin());
        if (existing.isPresent()) {
            return null;
        }
        Profile profile = new Profile();
        profile.setLogin(profileRequest.getLogin());
        profile.setPassword(profileRequest.getPassword());
        return profileRepository.save(profile);
    }
}
