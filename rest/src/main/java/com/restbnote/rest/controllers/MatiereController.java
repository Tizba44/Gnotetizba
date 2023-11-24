package com.restbnote.rest.controllers;

import com.restbnote.rest.services.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/matiere")
public class MatiereController {

    private MatiereService matiereService;

}
