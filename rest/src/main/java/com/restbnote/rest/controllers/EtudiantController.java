package com.restbnote.rest.controllers;

import com.restbnote.rest.services.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.restbnote.rest.models.dto.EtudiantDto;
import java.util.List;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;



@RestController
@AllArgsConstructor
@RequestMapping("/etudiants")
public class EtudiantController {

    private EtudiantService etudiantService;

    @PostMapping("")
    public EntityModel<EtudiantDto> createEtudiant(@RequestBody EtudiantDto etudiantDto) {
        EtudiantDto createdEtudiant = etudiantService.createEtudiant(etudiantDto);
        return EntityModel.of(createdEtudiant,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(createdEtudiant.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(EtudiantController.class).slash(createdEtudiant.getId()).withRel("update"),
                WebMvcLinkBuilder.linkTo(EtudiantController.class).slash(createdEtudiant.getId()).withRel("delete"));
    }

    @GetMapping("")

    public List<EtudiantDto> readEtudiant() {
        List<EtudiantDto> etudiants = etudiantService.readEtudiant();
        for (EtudiantDto etudiant : etudiants) {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(etudiant.getId())).withSelfRel();
            etudiant.add(selfLink);
        }
        return etudiants;
    }

    @GetMapping("{id}")
    public EntityModel<EtudiantDto> readOneEtudiant(@PathVariable String id) {
        EtudiantDto etudiantDto = etudiantService.readOneEtudiant(id);
        return EntityModel.of(etudiantDto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(EtudiantController.class).slash(id).withRel("update"),
                WebMvcLinkBuilder.linkTo(EtudiantController.class).slash(id).withRel("delete"));
    }

    @PutMapping("{id}")
    public EntityModel<EtudiantDto> updateEtudiant(@PathVariable String id, @RequestBody EtudiantDto etudiantDto) {
        EtudiantDto updatedEtudiant = etudiantService.updateEtudiant(id, etudiantDto);
        return EntityModel.of(updatedEtudiant,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(EtudiantController.class).slash(id).withRel("update"),
                WebMvcLinkBuilder.linkTo(EtudiantController.class).slash(id).withRel("delete"));
    }

    @DeleteMapping("{id}")
    public void deleteEtudiant(@PathVariable String id) {
        etudiantService.deleteEtudiant(id);
    }
}
