package com.restbnote.rest.controllers;

import com.restbnote.rest.services.ProfService;
import lombok.AllArgsConstructor;
import com.restbnote.rest.models.dto.ProfDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Prof")
public class ProfController {

    private ProfService profService;

    @PostMapping("/create")
    public ProfDto createProf(@RequestBody ProfDto profDto) {
        return profService.createProf(profDto);
    }

    @GetMapping("/read")
    public List<ProfDto> readProf() {
        return profService.readProf();
    }

    @PutMapping("/update/{id}")
    public ProfDto updateProf(@PathVariable String id, @RequestBody ProfDto profDto) {
        return profService.updateProf(id, profDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProf(@PathVariable String id) {
        profService.deleteProf(id);
    }

}