package com.restbnote.rest.services.impl;
import com.restbnote.rest.configs.exceptions.MyException;
import com.restbnote.rest.configs.exceptions.MyExceptionPayLoad;
import com.restbnote.rest.models.dto.EtudiantDto;
import com.restbnote.rest.models.entities.EtudiantEntity;
import com.restbnote.rest.repositories.EtudiantRepository;
import com.restbnote.rest.services.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;

    @Override
    public EtudiantDto createEtudiant(EtudiantDto etudiantDto) {
        EtudiantEntity etudiant = new EtudiantEntity();
        etudiant.setMailID(etudiantDto.getMailID());
        etudiant.setNom(etudiantDto.getNom());
        etudiant.setPrenom(etudiantDto.getPrenom());
        etudiant.setTelephone(etudiantDto.getTelephone());

        etudiant = etudiantRepository.save(etudiant);
        return EtudiantDto.builder()
                .id(etudiant.getId())
                .mailID(etudiant.getMailID())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .telephone(etudiant.getTelephone())
                .build();
    }

    @Override
    public List<EtudiantDto> readEtudiant() {
        List<EtudiantEntity> etudiants = etudiantRepository.findAll();
        List<EtudiantDto> etudiantDtos = new ArrayList<>();
        etudiants.stream()
                .map(p -> EtudiantDto.builder()
                        .id(p.getId())
                        .mailID(p.getMailID())
                        .nom(p.getNom())
                        .prenom(p.getPrenom())
                        .telephone(p.getTelephone())
                        .build()).forEach(etudiantDtos::add);
        return etudiantDtos;
    }

    @Override
    public EtudiantDto updateEtudiant(String id, EtudiantDto etudiantDto) {
        return etudiantRepository.findById(id)
                .map(p-> {
                    p.setMailID(etudiantDto.getMailID());
                    p.setNom(etudiantDto.getNom());
                    p.setPrenom(etudiantDto.getPrenom());
                    p.setTelephone(etudiantDto.getTelephone());
                    etudiantRepository.save(p);
                    etudiantDto.setId(p.getId());
                    return etudiantDto;
                }).orElse(null);
    }

    @Override
    public String deleteEtudiant(String id) {
        EtudiantEntity p = etudiantRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("produit non trouvé")
                                .build()
                ));
        etudiantRepository.delete(p);
        return "etudiant supprimé";
    }


}
