package ru.vsu.bw.ledenev.labspring.service;

import org.springframework.stereotype.Service;
import ru.vsu.bw.ledenev.labspring.dto.pet.PetCreateRequest;
import ru.vsu.bw.ledenev.labspring.dto.pet.PetCreateResponse;
import ru.vsu.bw.ledenev.labspring.dto.pet.PetResponse;
import ru.vsu.bw.ledenev.labspring.entity.Pet;
import ru.vsu.bw.ledenev.labspring.repository.PetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {

    private final PetRepository petsRepository;

    public PetService(PetRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    public PetCreateResponse createPet(PetCreateRequest request, UUID id_profile) {
        Pet pet = new Pet();
        pet.setId(UUID.randomUUID());
        pet.setName(request.getName());
        pet.setStrength(request.getStrength());
        pet.setProfileId(id_profile);
        petsRepository.createPet(pet);
        return new PetCreateResponse(pet.getId());
    }

    public Optional<PetResponse> getPetById(UUID id) {
        return petsRepository.findById(id).map(pet -> new PetResponse(pet.getName(), pet.getStrength(), pet.getProfileId()));
    }

    public List<PetResponse> getPetsByName(String namePart) {
        return petsRepository.findByName(namePart)
                .stream()
                .map(pet -> new PetResponse(pet.getName(), pet.getStrength(), pet.getProfileId()))
                .toList();
    }

    public PetResponse updatePet(UUID id, PetCreateRequest request, UUID profileId) {
        Optional<Pet> petOpt = petsRepository.findById(id);
        if (petOpt.isEmpty()) {
            return null;
        }
        Pet pet = petOpt.get();
        pet.setName(request.getName());
        pet.setStrength(request.getStrength());
        pet.setProfileId(profileId);
        petsRepository.updatePet(pet);
        return new PetResponse(pet.getName(), pet.getStrength(), pet.getProfileId());
    }

    public PetResponse deletePet(UUID id) {
        Optional<Pet> petOpt = petsRepository.findById(id);
        if (petOpt.isPresent()) {
            Pet pet = petOpt.get();
            petsRepository.deletePet(id);
            return new PetResponse(pet.getName(), pet.getStrength(), pet.getProfileId());
        }
        return null;
    }
}
