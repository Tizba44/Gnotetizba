package com.restbnote.rest.controllers;

import com.restbnote.rest.services.ProfService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/Prof")
public class ProfController {

    private ProfService profService;

}
