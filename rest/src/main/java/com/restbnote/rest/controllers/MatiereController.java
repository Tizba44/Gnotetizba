package com.restbnote.rest.controllers;

import com.restbnote.rest.services.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.restbnote.rest.models.dto.MatiereDto;
import java.util.List;



@RestController
@AllArgsConstructor
@RequestMapping("/matiere")
public class MatiereController {

    private MatiereService matiereService;

    @PostMapping("/create")
    public MatiereDto createMatiere(@RequestBody MatiereDto matiereDto) {
        return matiereService.createMatiere(matiereDto);
    }
    @GetMapping("/read")
    public List<MatiereDto> readMatiere() {
        return matiereService.readMatiere();
    }
    @PutMapping("/update/{id}")
    public MatiereDto updateMatiere(@PathVariable String id, @RequestBody MatiereDto matiereDto) {
        return matiereService.updateMatiere(id, matiereDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteMatiere(@PathVariable String id) {
        matiereService.deleteMatiere(id);
    }

}
