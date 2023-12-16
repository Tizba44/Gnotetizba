package com.restbnote.rest.controllers;

import com.restbnote.rest.models.dto.MatiereDto;
import com.restbnote.rest.services.ControleService;
import com.restbnote.rest.services.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import com.restbnote.rest.models.dto.ControleDto;
import java.util.List;





@RestController
@AllArgsConstructor
@RequestMapping("/controles")
    public class ControleController {
            private ControleService controleService;


            @PostMapping("")
            public EntityModel<ControleDto> createControle(@RequestBody ControleDto controleDto) {
                ControleDto createdControle = controleService.createControle(controleDto);
                return EntityModel.of(createdControle,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readOneControle(createdControle.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(ControleController.class).slash(createdControle.getId()).withRel("update"),
                        WebMvcLinkBuilder.linkTo(ControleController.class).slash(createdControle.getId()).withRel("delete"));
            }

            @GetMapping("")
            public List<ControleDto> readControle() {
                List<ControleDto> controles = controleService.readControle();
                for (ControleDto controle : controles) {
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readOneControle(controle.getId())).withSelfRel();
                    controle.add(selfLink);
                }
                return controles;
            }

            @GetMapping("{id}")
            public EntityModel<ControleDto> readOneControle(@PathVariable String id) {
                ControleDto controleDto = controleService.readOneControle(id);
                return EntityModel.of(controleDto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readOneControle(id)).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(ControleController.class).slash(id).withRel("update"),
                        WebMvcLinkBuilder.linkTo(ControleController.class).slash(id).withRel("delete"));
            }

            @PutMapping("{id}")
            public EntityModel<ControleDto> updateControle(@PathVariable String id, @RequestBody ControleDto controleDto) {
                ControleDto updatedControle = controleService.updateControle(id, controleDto);
                return EntityModel.of(updatedControle,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readOneControle(id)).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(ControleController.class).slash(id).withRel("update"),
                        WebMvcLinkBuilder.linkTo(ControleController.class).slash(id).withRel("delete"));
            }

            @DeleteMapping("{id}")
            public void deleteControle(@PathVariable String id) {
                controleService.deleteControle(id);
            }
    }




