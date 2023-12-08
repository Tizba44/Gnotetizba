package com.restbnote.rest.services.impl;
import com.restbnote.rest.configs.exceptions.MyException;
import com.restbnote.rest.configs.exceptions.MyExceptionPayLoad;
import com.restbnote.rest.models.dto.ControleDto;
import com.restbnote.rest.models.entities.ControleEntity;
import com.restbnote.rest.repositories.ControleRepository;
import com.restbnote.rest.services.ControleService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
@AllArgsConstructor
public class ControleServiceImpl implements ControleService {
        private final ControleRepository controleRepository;
        @Override
        public ControleDto createControle(ControleDto controleDto) {
            ControleEntity controle = new ControleEntity();
            controle.setDate(controleDto.getDate());
            controle.setNote(controleDto.getNote());
            controle.setCoef(controleDto.getCoef());
            controle.setMatiereID(controleDto.getMatiereID());
            controle.setAppreciation(controleDto.getAppreciation());
            controle.setIntituleID(controleDto.getIntituleID());
            controle.setMailEtudiantsID(controleDto.getMailEtudiantsID());
            controle = controleRepository.save(controle);
            return ControleDto.builder()
                    .id(controle.getId())
                    .date(controle.getDate())
                    .note(controle.getNote())
                    .coef(controle.getCoef())
                    .matiereID(controle.getMatiereID())
                    .appreciation(controle.getAppreciation())
                    .intituleID(controle.getIntituleID())
                    .mailEtudiantsID(controle.getMailEtudiantsID())
                    .build();
        }

        @Override
        public List<ControleDto> readControle() {
            List<ControleEntity> controles = controleRepository.findAll();
            List<ControleDto> controleDtos = new ArrayList<>();
            controles.stream()
                    .map(p -> ControleDto.builder()
                            .id(p.getId())
                            .date(p.getDate())
                            .note(p.getNote())
                            .coef(p.getCoef())
                            .matiereID(p.getMatiereID())
                            .appreciation(p.getAppreciation())
                            .intituleID(p.getIntituleID())
                            .mailEtudiantsID(p.getMailEtudiantsID())
                            .build()).forEach(controleDtos::add);
            return controleDtos;
        }

        @Override
        public ControleDto updateControle(String id, ControleDto controleDto) {
            return controleRepository.findById(id)
                    .map(p-> {
                        p.setDate(controleDto.getDate());
                        p.setNote(controleDto.getNote());
                        p.setCoef(controleDto.getCoef());
                        p.setMatiereID(controleDto.getMatiereID());
                        p.setAppreciation(controleDto.getAppreciation());
                        p.setIntituleID(controleDto.getIntituleID());
                        p.setMailEtudiantsID(controleDto.getMailEtudiantsID());
                        controleRepository.save(p);
                        controleDto.setId(p.getId());
                        return controleDto;
                    }).orElse(null);
        }

        @Override
        public String deleteControle(String id) {
            ControleEntity p = controleRepository.findById(id)
                    .orElseThrow(() -> new MyException(
                            MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("produit non trouvé")
                                .build()
                    ));
            controleRepository.delete(p);
            return "controle supprimé";
        }





    @Scheduled(fixedRate = 15000)
    public void printStudentAverages() {
        try {

            List<Map<String, String>> studentAverages = new ArrayList<>();
            List<Object[]> averages = controleRepository.findWeightedAverageNoteByStudent();
            for (Object[] average : averages) {
                String mailEtudiantsID = (String) average[0];
                Number avgNoteNumber = (Number) average[1];
                Integer avgNote = avgNoteNumber.intValue();
                Map<String, String> studentAverage = new HashMap<>();
                studentAverage.put("note", avgNote.toString());
                studentAverage.put("mailEtudiantsID", mailEtudiantsID);
                studentAverages.add(studentAverage);
            }

            List<Map<String, String>> studentSubjectAverages = new ArrayList<>();
            averages = controleRepository.findWeightedAverageNoteByStudentAndSubject();
            for (Object[] average : averages) {
                String mailEtudiantsID = (String) average[0];
                String subject = (String) average[1];
                Number avgNoteNumber = (Number) average[2];
                Integer avgNote = avgNoteNumber.intValue();
                Map<String, String> studentSubjectAverage = new HashMap<>();
                studentSubjectAverage.put("note", avgNote.toString());
                studentSubjectAverage.put("mailEtudiantsID", mailEtudiantsID);
                studentSubjectAverage.put("matiereID", subject);
                studentSubjectAverages.add(studentSubjectAverage);
            }


            Map<String, Object> studentData = new HashMap<>();
            studentData.put("moyenneGenerale", studentAverages);
            studentData.put("moyenneParMatiere", studentSubjectAverages);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("src/main/resources/moyenne.json"), studentData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

