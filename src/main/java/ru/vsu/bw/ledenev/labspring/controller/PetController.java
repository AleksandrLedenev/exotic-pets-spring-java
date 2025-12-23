package ru.vsu.bw.ledenev.labspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.bw.ledenev.labspring.dto.pet.PetCreateRequest;
import ru.vsu.bw.ledenev.labspring.dto.pet.PetCreateResponse;
import ru.vsu.bw.ledenev.labspring.dto.pet.PetResponse;
import ru.vsu.bw.ledenev.labspring.service.PetService;

import java.util.List;
import java.util.UUID;

@RestController
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("profiles/{id_profile}/pets")
    public ResponseEntity<PetCreateResponse> createPet(@RequestBody PetCreateRequest request, @PathVariable UUID id_profile) {
        PetCreateResponse petCreateResponse = petService.createPet(request, id_profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(petCreateResponse);
    }

    @GetMapping("pets/{id}")
    public ResponseEntity<?> getPetById(@PathVariable UUID id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("pets/search/{name}")
    public ResponseEntity<List<PetResponse>> getPetsByName(@PathVariable String name) {
        List<PetResponse> pets = petService.getPetsByName(name);
        if (pets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pets);
    }

    @PutMapping("profiles/{id_profile}/pets/{id}")
    public ResponseEntity<PetResponse> updatePet(@PathVariable UUID id, @RequestBody PetCreateRequest request, @PathVariable UUID id_profile) {
        PetResponse petResponse = petService.updatePet(id, request, id_profile);
        if (petResponse == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(petResponse);
        }

    }

    @DeleteMapping("pets/{id}")
    public ResponseEntity<?> deletePet(@PathVariable UUID id) {
        PetResponse petResponse = petService.deletePet(id);
        if (petResponse == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(petResponse);
        }

    }
}
