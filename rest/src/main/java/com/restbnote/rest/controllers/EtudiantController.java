package com.restbnote.rest.controllers;

import com.restbnote.rest.services.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.restbnote.rest.models.dto.EtudiantDto;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/etudiants")
public class EtudiantController {

    private EtudiantService etudiantService;

    @PostMapping("")
    public EtudiantDto createEtudiant(@RequestBody EtudiantDto etudiantDto) {
        return etudiantService.createEtudiant(etudiantDto);
    }
    @GetMapping("")
    public List<EtudiantDto> readEtudiant() {
        return etudiantService.readEtudiant();
    }

    @GetMapping("{id}")
    public EtudiantDto readOneEtudiant(@PathVariable String id) {
        return etudiantService.readOneEtudiant(id);
    }

    @PutMapping("{id}")
    public EtudiantDto updateEtudiant(@PathVariable String id, @RequestBody EtudiantDto etudiantDto) {
        return etudiantService.updateEtudiant(id, etudiantDto);
    }
    @DeleteMapping("{id}")
    public void deleteEtudiant(@PathVariable String id) {
        etudiantService.deleteEtudiant(id);
    }

}