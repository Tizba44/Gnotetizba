package com.restbnote.rest.controllers;

import com.restbnote.rest.models.dto.AdminDto;
import com.restbnote.rest.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;


}


