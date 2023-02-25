package com.mca.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.project.entity.Pet;
import com.mca.project.exceptions.ResourceNotFoundException;
import com.mca.project.repo.PetRepo;

@RestController
@RequestMapping("/pets")
@CrossOrigin("*")
public class PetController {
    
    @Autowired
    private PetRepo petRepository;
    
    @GetMapping("/getall")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable(value = "id") Long petId) {
        Pet pet = petRepository.findById(petId)
            .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));
        return ResponseEntity.ok().body(pet);
    }
    
    @PostMapping("/addpet")
    public Pet createPet( @RequestBody Pet pet) {
        return petRepository.save(pet);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable(value = "id") Long petId, @RequestBody Pet petDetails) {
        Pet pet = petRepository.findById(petId)
            .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));
        pet.setName(petDetails.getName());
        pet.setAge(petDetails.getAge());
        pet.setSpecies(petDetails.getSpecies());
        pet.setAdopted(petDetails.isAdopted());
        final Pet updatedPet = petRepository.save(pet);
        return ResponseEntity.ok(updatedPet);
    }
    
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletePet(@PathVariable(value = "id") Long petId) {
        Pet pet = petRepository.findById(petId)
            .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));
        petRepository.delete(pet);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
}

