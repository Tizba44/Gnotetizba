package com.restbnote.rest.controllers;


import com.restbnote.rest.services.ControleService;

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
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readOneControle(createdControle.getId())).withSelfRel());
        }

        @GetMapping("")
        public List<ControleDto> readControle() {
            List<ControleDto> controles = controleService.readControle();
            for (ControleDto controle : controles) {
                Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readOneControle(controle.getId())).withSelfRel();
                Link allControlesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readControle()).withRel("allControles");
                controle.add(selfLink, allControlesLink);
            }

            return controles;

        }

        @GetMapping("{id}")
        public EntityModel<ControleDto> readOneControle(@PathVariable String id) {
            ControleDto controleDto = controleService.readOneControle(id);
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readOneControle(id)).withSelfRel();
            Link allControlesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readControle()).withRel("allControles");
            controleDto.add(selfLink, allControlesLink);
            return EntityModel.of(controleDto);
        }

        @PutMapping("{id}")
        public EntityModel<ControleDto> updateControle(@PathVariable String id, @RequestBody ControleDto controleDto) {
            ControleDto updatedControle = controleService.updateControle(id, controleDto);
            return EntityModel.of(updatedControle,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleController.class).readOneControle(id)).withSelfRel()

            );
        }

        @DeleteMapping("{id}")
        public void deleteControle(@PathVariable String id) {
            controleService.deleteControle(id);
        }
    }











