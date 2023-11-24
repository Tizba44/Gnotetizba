package com.restbnote.rest.controllers;

import com.restbnote.rest.services.ControleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/controle")
public class controleController {

    private ControleService controleService;

}
