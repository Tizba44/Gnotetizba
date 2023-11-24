package com.restbnote.rest.controllers;

import com.restbnote.rest.services.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantController {

    private EtudiantService etudiantService;

}
