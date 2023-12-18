package com.restbnote.rest.controllers;

import com.restbnote.rest.services.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import com.restbnote.rest.models.dto.MatiereDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/matieres")
public class MatiereController  {

    private MatiereService matiereService;

    @PostMapping("")
    public EntityModel<MatiereDto> createMatiere(@RequestBody MatiereDto matiereDto) {
        MatiereDto createdMatiere = matiereService.createMatiere(matiereDto);
        return EntityModel.of(createdMatiere,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatiereController.class).readOneMatiere(createdMatiere.getId())).withSelfRel());
    }

    @GetMapping("")
    public List<MatiereDto> readMatiere() {
        List<MatiereDto> matieres = matiereService.readMatiere();
        for (MatiereDto matiere : matieres) {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatiereController.class).readOneMatiere(matiere.getId())).withSelfRel();
            matiere.add(selfLink);
        }
        return matieres;
    }

    @GetMapping("{id}")
    public EntityModel<MatiereDto> readOneMatiere(@PathVariable String id) {
        MatiereDto matiereDto = matiereService.readOneMatiere(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatiereController.class).readOneMatiere(id)).withSelfRel();
        Link allMatieresLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatiereController.class).readMatiere()).withRel("allMatieres");
        matiereDto.add(selfLink, allMatieresLink);
        return EntityModel.of(matiereDto);
    }

    @PutMapping("{id}")
    public EntityModel<MatiereDto> updateMatiere(@PathVariable String id, @RequestBody MatiereDto matiereDto) {
        MatiereDto updatedMatiere = matiereService.updateMatiere(id, matiereDto);
        return EntityModel.of(updatedMatiere,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatiereController.class).readOneMatiere(id)).withSelfRel()

        );
    }

    @DeleteMapping("{id}")
    public void deleteMatiere(@PathVariable String id) {
        matiereService.deleteMatiere(id);
    }
}

