package com.restbnote.rest.controllers;
import com.restbnote.rest.models.dto.EtudiantDto;
import com.restbnote.rest.services.MatiereService;
import com.restbnote.rest.services.ProfService;
import lombok.AllArgsConstructor;
import com.restbnote.rest.models.dto.ProfDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import com.restbnote.rest.models.dto.MatiereDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.CollectionModel;
import java.util.ArrayList;
import java.util.List;





@RestController
@AllArgsConstructor
@RequestMapping("/profs")
public class ProfController {

    private ProfService profService;
    private MatiereService matiereService;


    @PostMapping("")
    public EntityModel<ProfDto> createProf(@RequestBody ProfDto profDto) {
        ProfDto createdProf = profService.createProf(profDto);
        return EntityModel.of(createdProf,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneProf(createdProf.getId())).withSelfRel());
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



    @GetMapping("")
    public List<ProfDto> readProf() {
        List<ProfDto> profs = profService.readProf();
        for (ProfDto prof : profs) {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneProf(prof.getId())).withSelfRel();
            Link allProfsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProf()).withRel("allProfs");
            //ajouter les lien vers les matieres du prof
            Link allMatieresLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProfMatieres(prof.getId())).withRel("Matieres du prof");
            prof.add(selfLink, allProfsLink, allMatieresLink);
        }
        Link allProfsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProf()).withRel("allProfs");
        profs.forEach(prof -> prof.add(allProfsLink));
        return profs;
    }


    @GetMapping("{id}")
    public EntityModel<ProfDto> readOneProf(@PathVariable String id) {
        ProfDto profDto = profService.readOneProf(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneProf(id)).withSelfRel();
        Link allProfsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProf()).withRel("Toutes les matières du Profs");
        Link allMatieresLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProfMatieres(id)).withRel("Une Matiere du prof");
        profDto.add(selfLink, allProfsLink, allMatieresLink);
        return EntityModel.of(profDto);
    }


    @GetMapping("{profId}/matieres")
    public CollectionModel<EntityModel<MatiereDto>> readProfMatieres(@PathVariable String profId) {
        // Vérifiez d'abord si l'étudiant existe
        ProfDto profDto = profService.readOneProf(profId);
        if (profDto == null) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
        }

        // Ensuite, obtenez tous les contrôles de l'étudiant
        List<MatiereDto> matieres = matiereService.readAllMatieresOfProf(profDto.getMailID());
        if (matieres.isEmpty()) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
        }

        // Si tout va bien, renvoyez les contrôles
        List<EntityModel<MatiereDto>> matiereResources = new ArrayList<>();

        for (MatiereDto matiere : matieres) {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneMatiereOfProf(profId, matiere.getId())).withSelfRel();
            matiere.add(selfLink);
            matiereResources.add(EntityModel.of(matiere));
        }
        Link allMatieresLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProfMatieres(profId)).withSelfRel();
        Link profLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneProf(profId)).withRel("prof");
        Link allProfsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProf()).withRel("Tous les profs");
        return CollectionModel.of(matiereResources, allMatieresLink, profLink, allProfsLink);
    }




    @GetMapping("{profId}/matieres/{matiereId}")
    public EntityModel<MatiereDto> readOneMatiereOfProf(@PathVariable String profId, @PathVariable String matiereId) {
        // Vérifiez d'abord si l'étudiant existe

        ProfDto profDto = profService.readOneProf(profId);
        if (profDto == null) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
        }

        // Ensuite, obtenez le contrôle spécifique
        MatiereDto matiereDto = matiereService.readOneMatiere(matiereId);
        if (matiereDto == null || !matiereDto.getMailProfsID().equals(profDto.getMailID())) {
            // Gérez l'erreur ici (par exemple, renvoyez une réponse 404)
        }
//        lien du controle spécifique ou on est actuellement
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneMatiereOfProf(profId, matiereId)).withSelfRel();
        Link allMatieresLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProfMatieres(profId)).withRel("Matieres du prof");
        Link profLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readOneProf(profId)).withRel("prof");
        Link allProfsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfController.class).readProf()).withRel("Tous les profs");
        matiereDto.add(allMatieresLink, profLink, allProfsLink, selfLink);
        return EntityModel.of(matiereDto);

    }
}