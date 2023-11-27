package com.restbnote.rest.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restbnote.rest.models.dto.*;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;



import java.nio.file.Paths;


@Service
public class DatabaseInitializer {

    private final ControleService controleService;
    private final ProfService profService;
    private final EtudiantService etudiantService;
    private final MatiereService matiereService;

    private final AdminService adminService;


    private final ObjectMapper objectMapper;

    public DatabaseInitializer(ControleService controleService, ObjectMapper objectMapper, ProfService profService, EtudiantService etudiantService, MatiereService matiereService , AdminService adminService) {
        this.controleService = controleService;
        this.profService = profService;
        this.etudiantService = etudiantService;
        this.matiereService = matiereService;
        this.objectMapper = objectMapper;
        this.adminService = adminService;

    }

    @PostConstruct
    public void initData() throws IOException {
        File file = Paths.get("src", "main", "resources", "data.json").toFile();
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }

        // Read the entire JSON file into a JsonNode
        JsonNode rootNode = objectMapper.readTree(file);


        // Get the "controles" array from the JSON
        JsonNode controlesNode = rootNode.get("controles");

        // Check if "controles" node exists
        if (controlesNode != null && controlesNode.isArray()) {
            // Iterate over the "controles" array
            for (JsonNode controleNode : controlesNode) {
                // Map each "controle" node to ControleDto
                ControleDto controleDto = objectMapper.treeToValue(controleNode, ControleDto.class);

                // Process the ControleDto, for example, save it using ControleService
                controleService.createControle(controleDto);
            }
        } else {
            // Handle the case where "controles" node is missing or not an array
            throw new IOException("Invalid or missing 'controles' data in the JSON file");
        }



        // Get the "profs" array from the JSON
        JsonNode profsNode = rootNode.get("profs");

        // Check if "profs" node exists
        if (profsNode != null && profsNode.isArray()) {
            // Iterate over the "profs" array
            for (JsonNode profNode : profsNode) {
                // Map each "prof" node to ProfDto
                ProfDto profDto = objectMapper.treeToValue(profNode, ProfDto.class);

                // Process the ProfDto, for example, save it using ProfService
                profService.createProf(profDto);
            }
        } else {
            // Handle the case where "profs" node is missing or not an array
            throw new IOException("Invalid or missing 'profs' data in the JSON file");
        }

        // Get the "etudiants" array from the JSON
        JsonNode etudiantsNode = rootNode.get("etudiants");

        // Check if "etudiants" node exists
        if (etudiantsNode != null && etudiantsNode.isArray()) {
            // Iterate over the "etudiants" array
            for (JsonNode etudiantNode : etudiantsNode) {
                // Map each "etudiant" node to EtudiantDto
                EtudiantDto etudiantDto = objectMapper.treeToValue(etudiantNode, EtudiantDto.class);

                // Process the EtudiantDto, for example, save it using EtudiantService
                etudiantService.createEtudiant(etudiantDto);
            }
        } else {
            // Handle the case where "etudiants" node is missing or not an array
            throw new IOException("Invalid or missing 'etudiants' data in the JSON file");
        }

        // Get the "matieres" array from the JSON
        JsonNode matieresNode = rootNode.get("matieres");

        // Check if "matieres" node exists
        if (matieresNode != null && matieresNode.isArray()) {
            // Iterate over the "matieres" array
            for (JsonNode matiereNode : matieresNode) {
                // Map each "matiere" node to MatiereDto
                MatiereDto matiereDto = objectMapper.treeToValue(matiereNode, MatiereDto.class);

                // Process the MatiereDto, for example, save it using MatiereService
                matiereService.createMatiere(matiereDto);
            }
        } else {
            // Handle the case where "matieres" node is missing or not an array
            throw new IOException("Invalid or missing 'matieres' data in the JSON file");
        }

        // Get the "admins" array from the JSON
        JsonNode adminsNode = rootNode.get("admins");

        // Check if "admins" node exists
        if (adminsNode != null && adminsNode.isArray()) {
            // Iterate over the "admins" array
            for (JsonNode adminNode : adminsNode) {
                // Map each "admin" node to AdminDto
                AdminDto adminDto = objectMapper.treeToValue(adminNode, AdminDto.class);

                // Process the AdminDto, for example, save it using AdminService
                adminService.createAdmin(adminDto);
            }
        } else {
            // Handle the case where "admins" node is missing or not an array
            throw new IOException("Invalid or missing 'admins' data in the JSON file");
        }
    }

}

