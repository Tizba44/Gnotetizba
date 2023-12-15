package com.restbnote.rest.controllers;

import com.restbnote.rest.services.ControleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.restbnote.rest.models.dto.ControleDto;
import java.util.List;





@RestController
@AllArgsConstructor
@RequestMapping("/controles")
    public class ControleController {
            private ControleService controleService;
            @PostMapping("")
            public ControleDto createControle(@RequestBody ControleDto controleDto) {
                return controleService.createControle(controleDto);
            }




            @GetMapping("")
            public List<ControleDto> readControle() {
                return controleService.readControle();
            }


            @GetMapping("{id}")
            public ControleDto readOneControle(@PathVariable String id) {
                return controleService.readOneControle(id);
            }


            @PutMapping("{id}")
            public ControleDto updateControle(@PathVariable String id, @RequestBody ControleDto controleDto) {
                return controleService.updateControle(id, controleDto);
            }
            @DeleteMapping("{id}")
            public void deleteControle(@PathVariable String id) {
                controleService.deleteControle(id);
            }


    }




