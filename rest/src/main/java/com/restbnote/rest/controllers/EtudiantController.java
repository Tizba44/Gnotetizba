package com.restbnote.rest.controllers;

import com.restbnote.rest.services.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.restbnote.rest.models.dto.EtudiantDto;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantController {

    private EtudiantService etudiantService;

    @PostMapping("/create")
    public EtudiantDto createEtudiant(@RequestBody EtudiantDto etudiantDto) {
        return etudiantService.createEtudiant(etudiantDto);
    }
    @GetMapping("/read")
    public List<EtudiantDto> readEtudiant() {
        return etudiantService.readEtudiant();
    }
    @PutMapping("/update/{id}")
    public EtudiantDto updateEtudiant(@PathVariable String id, @RequestBody EtudiantDto etudiantDto) {
        return etudiantService.updateEtudiant(id, etudiantDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteEtudiant(@PathVariable String id) {
        etudiantService.deleteEtudiant(id);
    }

}