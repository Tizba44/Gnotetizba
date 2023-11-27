package com.restbnote.rest.services.impl;
import com.restbnote.rest.configs.exceptions.MyException;
import com.restbnote.rest.configs.exceptions.MyExceptionPayLoad;
import com.restbnote.rest.models.dto.MatiereDto;
import com.restbnote.rest.models.entities.MatiereEntity;
import com.restbnote.rest.repositories.MatiereRepository;
import com.restbnote.rest.services.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class MatiereServiceImpl implements MatiereService {

    private final MatiereRepository matiereRepository;
    @Override
    public MatiereDto createMatiere(MatiereDto matiereDto) {
        MatiereEntity matiere = new MatiereEntity();
        matiere.setNomMatiereID(matiereDto.getNomMatiereID());
        matiere.setMailProfsID(matiereDto.getMailProfsID());
        matiere = matiereRepository.save(matiere);
        return MatiereDto.builder()
                .id(matiere.getId())
                .nomMatiereID(matiere.getNomMatiereID())
                .mailProfsID(matiere.getMailProfsID())
                .build();
    }

    @Override
    public List<MatiereDto> readMatiere() {
        List<MatiereEntity> matieres = matiereRepository.findAll();
        List<MatiereDto> matiereDtos = new ArrayList<>();
        matieres.stream()
                .map(p -> MatiereDto.builder()
                        .id(p.getId())
                        .nomMatiereID(p.getNomMatiereID())
                        .mailProfsID(p.getMailProfsID())
                        .build()).forEach(matiereDtos::add);
        return matiereDtos;
    }

    @Override
    public MatiereDto updateMatiere(String id, MatiereDto matiereDto) {
        return matiereRepository.findById(id)
                .map(p-> {
                    p.setNomMatiereID(matiereDto.getNomMatiereID());
                    p.setMailProfsID(matiereDto.getMailProfsID());
                    matiereRepository.save(p);
                    matiereDto.setId(p.getId());
                    return matiereDto;
                }).orElse(null);
    }

    @Override
    public String deleteMatiere(String id) {
        MatiereEntity p = matiereRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("produit non trouvé")
                                .build()
                ));
        matiereRepository.delete(p);
        return "matiere supprimé";
    }



}
