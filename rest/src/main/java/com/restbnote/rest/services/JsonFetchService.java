//package com.restbnote.rest.services;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.restbnote.rest.models.dto.ControleDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.scheduling.annotation.Scheduled;
//import java.util.stream.Collectors;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//public class JsonFetchService {
//
//    @Autowired
//    private ControleService controleService; // Assurez-vous d'avoir ce service pour sauvegarder les données
//
//    // Exécute toutes les 10s
//    @Scheduled(fixedRate = 10000)
//    public void fetchDataFromJson() {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        //...
//        try {
//            File jsonFile = new File("src/main/java/com/restbnote/rest/data.json");
//
//            // Read the entire JSON into a Map
//            Map<String, List<Map<String, String>>> dataMap = objectMapper.readValue(jsonFile, new TypeReference<Map<String, List<Map<String, String>>>>() {});
//
//            // Extract the list of controles from the Map
//            List<Map<String, String>> controlesList = dataMap.get("controles");
//
//            // Convert the List of Map<String, String> to a List<ControleDto>
//            List<ControleDto> controleDtoList = controlesList.stream()
//                    .map(controleMap -> objectMapper.convertValue(controleMap, ControleDto.class))
//                    .collect(Collectors.toList());
//
//            // Save each ControleDto individually using your service
//            for (ControleDto controleDto : controleDtoList) {
//                controleService.saveControle(controleDto);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//    }
//}
