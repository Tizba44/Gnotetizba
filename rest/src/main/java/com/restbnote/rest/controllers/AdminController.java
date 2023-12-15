
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

    @PostMapping("")
    public AdminDto createAdmin(@RequestBody AdminDto adminDto) {
        return adminService.createAdmin(adminDto);
    }

    @GetMapping("")
    public List<AdminDto> readAdmin() {
        return adminService.readAdmin();
    }

    @GetMapping("{id}")
    public AdminDto readOneAdmin(@PathVariable String id) {
        return adminService.readOneAdmin(id);
    }

    @PutMapping("{id}")
    public AdminDto updateAdmin(@PathVariable String id, @RequestBody AdminDto adminDto) {
        return adminService.updateAdmin(id, adminDto);
    }
    @DeleteMapping("{id}")
    public void deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);
    }
}
