package com.restbnote.rest.controllers;

import com.restbnote.rest.services.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.restbnote.rest.models.dto.MatiereDto;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/matieres")
public class MatiereController {

    private MatiereService matiereService;

    @PostMapping("")
    public MatiereDto createMatiere(@RequestBody MatiereDto matiereDto) {
        return matiereService.createMatiere(matiereDto);
    }
    @GetMapping("")
    public List<MatiereDto> readMatiere() {
        return matiereService.readMatiere();
    }

    @GetMapping("{id}")
    public MatiereDto readOneMatiere(@PathVariable String id) {
        return matiereService.readOneMatiere(id);
    }

    @PutMapping("{id}")
    public MatiereDto updateMatiere(@PathVariable String id, @RequestBody MatiereDto matiereDto) {
        return matiereService.updateMatiere(id, matiereDto);
    }
    @DeleteMapping("{id}")
    public void deleteMatiere(@PathVariable String id) {
        matiereService.deleteMatiere(id);
    }

}
