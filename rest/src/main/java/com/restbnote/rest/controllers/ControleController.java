package com.restbnote.rest.controllers;

import com.restbnote.rest.services.ControleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.restbnote.rest.models.dto.ControleDto;
import java.util.List;





@RestController
@AllArgsConstructor
@RequestMapping("/controle")
    public class ControleController {
            private ControleService controleService;
            @PostMapping("/create")
            public ControleDto createControle(@RequestBody ControleDto controleDto) {
                return controleService.createControle(controleDto);
            }
            @GetMapping("/read")
            public List<ControleDto> readControle() {
                return controleService.readControle();
            }
            @PutMapping("/update/{id}")
            public ControleDto updateControle(@PathVariable String id, @RequestBody ControleDto controleDto) {
                return controleService.updateControle(id, controleDto);
            }
            @DeleteMapping("/delete/{id}")
            public void deleteControle(@PathVariable String id) {
                controleService.deleteControle(id);
            }


    }




