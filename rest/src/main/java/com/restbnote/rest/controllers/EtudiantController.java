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
import java.util.HashMap;



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
            Link ControlesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readAllControlesOfEtudiant(etudiant.getId())).withRel("Controles de l'étudiant");
            Link MoyennesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readMoyenneOfEtudiant(etudiant.getId())).withRel("Moyennes de l'étudiant");
            etudiant.add(selfLink, ControlesLink, MoyennesLink);

        }
        Link allEtudiantsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readEtudiant()).withRel("Tous les étudiants");
        Link allMoyennesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readMoyenneOfAllEtudiants()).withRel("Moyennes de tous les étudiants");
        etudiants.forEach(etudiant -> etudiant.add(allEtudiantsLink, allMoyennesLink));
        return etudiants;
    }

    @GetMapping("{id}")
    public EntityModel<EtudiantDto> readOneEtudiant(@PathVariable String id) {
        EtudiantDto etudiantDto = etudiantService.readOneEtudiant(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(id)).withSelfRel();
        Link allEtudiantsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readEtudiant()).withRel("Tous les étudiants");
        Link allMoyennesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readMoyenneOfAllEtudiants()).withRel("Moyennes de tous les étudiants");
        Link ControlesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readAllControlesOfEtudiant(id)).withRel("Controles de l'étudiant");
        Link MoyennesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readMoyenneOfEtudiant(id)).withRel("Moyennes de l'étudiant");

        etudiantDto.add(selfLink, allEtudiantsLink, ControlesLink, MoyennesLink, allMoyennesLink);
        return EntityModel.of(etudiantDto);
    }

    @GetMapping("/moyenne")
    public Map<String, Object> readMoyenneOfAllEtudiants() {
        // Obtenez la moyenne de tous les étudiants
        Map<String, Double> individualAverages = etudiantService.readMoyenneOfAllEtudiants();

        // Obtenez les meilleures et pires moyennes de la classe
        Map<String, Double> averagesAndExtremes = etudiantService.findBestAndWorstAverageOfAllEtudiants();

        // Combine both sets of information into a single map
        Map<String, Object> result = new HashMap<>();
        result.put("Moyenne générale", individualAverages);
        result.put("Meilleur et pire moyenne", averagesAndExtremes);
        Link allEtudiantsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readEtudiant()).withRel("Tous les étudiants");
        Link SelfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readMoyenneOfAllEtudiants()).withSelfRel();
        result.put("links", List.of(allEtudiantsLink, SelfLink));
        return result;
    }

    @GetMapping("{etudiantId}/moyenne")
    public Map<String, Object> readMoyenneOfEtudiant(@PathVariable String etudiantId) {
        // Vérifiez d'abord si l'étudiant existe
        EtudiantDto etudiantDto = etudiantService.readOneEtudiant(etudiantId);
        if (etudiantDto == null) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
            // ...
            return null; // Handle the error response appropriately
        }
        // Ensuite, obtenez la moyenne de l'étudiant
        double moyenneEtudiant = etudiantService.readMoyenneOfEtudiant(etudiantDto.getMailID());
        // Obtenez les meilleures et pires moyennes de la classe
        Map<String, Double> averagesAndExtremes = etudiantService.findBestAndWorstAverageOfAllEtudiants();
        // Combine both sets of information into a single map
        Map<String, Object> result = new HashMap<>();
        result.put("moyenne individuel", moyenneEtudiant);
        result.put("moyenne de la classe", averagesAndExtremes);
        Link allEtudiantsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readEtudiant()).withRel("Tous les étudiants");
        Link allMoyennesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readMoyenneOfAllEtudiants()).withRel("Moyennes de tous les étudiants");
        Link SelfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readMoyenneOfEtudiant(etudiantId)).withSelfRel();
        result.put("links", List.of(allEtudiantsLink, allMoyennesLink, SelfLink));
        return result;
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
        Link allEtudiantsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readEtudiant()).withRel("Tous les étudiants");
        return CollectionModel.of(controleResources, allControlesLink, etudiantLink, allEtudiantsLink);
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
//        lien du controle spécifique ou on est actuellement
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneControleOfEtudiant(etudiantId, controleId)).withSelfRel();
        Link allControlesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readAllControlesOfEtudiant(etudiantId)).withRel("Controles de l'étudiant");
        Link etudiantLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readOneEtudiant(etudiantId)).withRel("etudiant");
        Link allEtudiantsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EtudiantController.class).readEtudiant()).withRel("Tous les étudiants");
        controleDto.add(allControlesLink, etudiantLink, allEtudiantsLink, selfLink);
        return EntityModel.of(controleDto);
    }
}




