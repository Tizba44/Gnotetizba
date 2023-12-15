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
    public MatiereDto readOneMatiere(String id) {
        MatiereEntity matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("matiere non trouvé")
                                .build()
                ));
        return MatiereDto.builder()
                .id(matiere.getId())
                .nomMatiereID(matiere.getNomMatiereID())
                .mailProfsID(matiere.getMailProfsID())
                .build();
    }





    @Override
    public MatiereDto updateMatiere(String id, MatiereDto matiereDto) {
        return matiereRepository.findById(id)
                .map(existingMatiere -> {
                    if (matiereDto.getNomMatiereID() != null && !matiereDto.getNomMatiereID().isEmpty()) {
                        existingMatiere.setNomMatiereID(matiereDto.getNomMatiereID());
                    }
                    if (matiereDto.getMailProfsID() != null && !matiereDto.getMailProfsID().isEmpty()) {
                        existingMatiere.setMailProfsID(matiereDto.getMailProfsID());
                    }


                    matiereRepository.save(existingMatiere);
                    matiereDto.setId(existingMatiere.getId());
                    return matiereDto;
                })
                .orElseGet(() -> {
                    // If the matiere doesn't exist, create a new one
                    MatiereEntity newMatiere = new MatiereEntity();
                    newMatiere.setNomMatiereID(matiereDto.getNomMatiereID());
                    newMatiere.setMailProfsID(matiereDto.getMailProfsID());
                    newMatiere = matiereRepository.save(newMatiere);
                    return MatiereDto.builder()
                            .id(newMatiere.getId())
                            .nomMatiereID(newMatiere.getNomMatiereID())
                            .mailProfsID(newMatiere.getMailProfsID())
                            .build();
                });
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
