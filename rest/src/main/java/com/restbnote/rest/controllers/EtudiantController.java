package com.restbnote.rest.controllers;

import com.restbnote.rest.models.dto.ControleDto;
import com.restbnote.rest.services.ControleService;
import com.restbnote.rest.services.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import com.restbnote.rest.models.dto.EtudiantDto;
import org.springframework.hateoas.CollectionModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/etudiants")
public class EtudiantController {
    private final EtudiantService etudiantService;
    private final ControleService controleService;


    @PostMapping("")
    public EntityModel<EtudiantDto> createEtudiant(@RequestBody EtudiantDto etudiantDto) {
        EtudiantDto createdEtudiant = etudiantService.createEtudiant(etudiantDto);
        return EntityModel.of(createdEtudiant,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(createdEtudiant.getId())).withSelfRel());
    }

    @PutMapping("{id}")
    public EntityModel<EtudiantDto> updateEtudiant(@PathVariable String id, @RequestBody EtudiantDto etudiantDto) {
        EtudiantDto updatedEtudiant = etudiantService.updateEtudiant(id, etudiantDto);
        return EntityModel.of(updatedEtudiant,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(id)).withSelfRel());
    }

    @DeleteMapping("{id}")
    public void deleteEtudiant(@PathVariable String id) {
        etudiantService.deleteEtudiant(id);
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
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(id)).withSelfRel();
        Link allEtudiantsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readEtudiant()).withRel("allEtudiants");
        Link allControlesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readAllControlesOfEtudiant(id)).withRel("allControles");
        etudiantDto.add(selfLink, allEtudiantsLink, allControlesLink);
        return EntityModel.of(etudiantDto);
    }


    @GetMapping("{etudiantId}/moyenne")
    public double readMoyenneOfEtudiant(@PathVariable String etudiantId) {
        // Vérifiez d'abord si l'étudiant existe
        EtudiantDto etudiantDto = etudiantService.readOneEtudiant(etudiantId);
        if (etudiantDto == null) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
        }
//        crée un lien qui revine vers red one etudiant
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(etudiantId)).withSelfRel();
        etudiantDto.add(selfLink);
        // Ensuite, obtenez la moyenne de l'étudiant
        double moyenne = etudiantService.readMoyenneOfEtudiant(etudiantDto.getMailID());
        return moyenne;
    }

    @GetMapping("/moyenne")
    public Map<String, Double> readMoyenneOfAllEtudiants() {
        // Obtenez la moyenne de tous les étudiants
        Map<String, Double> moyennes = etudiantService.readMoyenneOfAllEtudiants();
        return moyennes;
    }





    @GetMapping("{etudiantId}/controles")
    public CollectionModel<EntityModel<ControleDto>> readAllControlesOfEtudiant(@PathVariable String etudiantId) {
        // Vérifiez d'abord si l'étudiant existe
        EtudiantDto etudiantDto = etudiantService.readOneEtudiant(etudiantId);
        if (etudiantDto == null) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
        }

        // Ensuite, obtenez tous les contrôles de l'étudiant
        List<ControleDto> controles = controleService.readAllControlesOfEtudiant(etudiantDto.getMailID());
        if (controles.isEmpty()) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
        }

        // Si tout va bien, renvoyez les contrôles
        List<EntityModel<ControleDto>> controleResources = new ArrayList<>();

        for (ControleDto controle : controles) {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneControleOfEtudiant(etudiantId, controle.getId())).withSelfRel();
            controle.add(selfLink);
            controleResources.add(EntityModel.of(controle));
        }
        Link allControlesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readAllControlesOfEtudiant(etudiantId)).withSelfRel();
        Link etudiantLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(etudiantId)).withRel("etudiant");
        return CollectionModel.of(controleResources, allControlesLink, etudiantLink);
    }

    @GetMapping("{etudiantId}/controles/{controleId}")
    public EntityModel<ControleDto> readOneControleOfEtudiant(@PathVariable String etudiantId, @PathVariable String controleId) {
        // Vérifiez d'abord si l'étudiant existe

        EtudiantDto etudiantDto = etudiantService.readOneEtudiant(etudiantId);
        if (etudiantDto == null) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
        }

        // Ensuite, obtenez le contrôle spécifique
        ControleDto controleDto = controleService.readOneControle(controleId);
        if (controleDto == null || !controleDto.getMailEtudiantsID().equals(etudiantDto.getMailID())) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
        }

        Link allControlesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readAllControlesOfEtudiant(etudiantId)).withSelfRel();
        Link etudiantLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(etudiantId)).withRel("etudiant");
        controleDto.add(allControlesLink, etudiantLink);
        return EntityModel.of(controleDto);

    }

}




