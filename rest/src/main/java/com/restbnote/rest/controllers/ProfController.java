package com.restbnote.rest.controllers;

import com.restbnote.rest.services.ProfService;
import lombok.AllArgsConstructor;
import com.restbnote.rest.models.dto.ProfDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;




@RestController
@AllArgsConstructor
@RequestMapping("/profs")
public class ProfController {

    private ProfService profService;

    @PostMapping("")
    public EntityModel<ProfDto> createProf(@RequestBody ProfDto profDto) {
        ProfDto createdProf = profService.createProf(profDto);
        return EntityModel.of(createdProf,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneProf(createdProf.getId())).withSelfRel());
    }

    @GetMapping("")
    public List<ProfDto> readProf() {
        List<ProfDto> profs = profService.readProf();
        for (ProfDto prof : profs) {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneProf(prof.getId())).withSelfRel();
            prof.add(selfLink);
        }
        return profs;
    }


    @GetMapping("{id}")
    public EntityModel<ProfDto> readOneProf(@PathVariable String id) {
        ProfDto profDto = profService.readOneProf(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneProf(id)).withSelfRel();
        Link allProfsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProf()).withRel("allProfs");
        profDto.add(selfLink, allProfsLink);
        return EntityModel.of(profDto);
    }

    @PutMapping("{id}")
    public EntityModel<ProfDto> updateProf(@PathVariable String id, @RequestBody ProfDto profDto) {
        ProfDto updatedProf = profService.updateProf(id, profDto);
        return EntityModel.of(updatedProf,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneProf(id)).withSelfRel()

        );
    }


    @DeleteMapping("{id}")
    public void deleteProf(@PathVariable String id) {
        profService.deleteProf(id);
    }

}