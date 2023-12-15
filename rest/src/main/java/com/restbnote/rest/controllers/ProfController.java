package com.restbnote.rest.controllers;

import com.restbnote.rest.services.ProfService;
import lombok.AllArgsConstructor;
import com.restbnote.rest.models.dto.ProfDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@AllArgsConstructor
@RequestMapping("/Profs")
public class ProfController {

    private ProfService profService;

    @PostMapping("")
    public ProfDto createProf(@RequestBody ProfDto profDto) {
        return profService.createProf(profDto);
    }

    @GetMapping("")
    public List<ProfDto> readProf() {
        return profService.readProf();
    }

    @GetMapping("/{id}")
    public ProfDto readOneProf(@PathVariable String id) {
        return profService.readOneProf(id);
    }

    @PutMapping("/{id}")
    public ProfDto updateProf(@PathVariable String id, @RequestBody ProfDto profDto) {
        return profService.updateProf(id, profDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProf(@PathVariable String id) {
        profService.deleteProf(id);
    }

}