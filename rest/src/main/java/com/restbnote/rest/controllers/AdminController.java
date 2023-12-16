
package com.restbnote.rest.controllers;

import com.restbnote.rest.models.dto.AdminDto;
import com.restbnote.rest.models.dto.MatiereDto;
import com.restbnote.rest.services.AdminService;
import com.restbnote.rest.services.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import java.util.List;



import java.util.List;





@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    @PostMapping("")
    public EntityModel<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
        AdminDto createdAdmin = adminService.createAdmin(adminDto);
        return EntityModel.of(createdAdmin,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminController.class).readOneAdmin(createdAdmin.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(AdminController.class).slash(createdAdmin.getId()).withRel("update"),
                WebMvcLinkBuilder.linkTo(AdminController.class).slash(createdAdmin.getId()).withRel("delete"));
    }

    @GetMapping("")
    public List<AdminDto> readAdmin() {
        List<AdminDto> admins = adminService.readAdmin();
        for (AdminDto admin : admins) {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminController.class).readOneAdmin(admin.getId())).withSelfRel();
            admin.add(selfLink);
        }
        return admins;
    }

    @GetMapping("{id}")
    public EntityModel<AdminDto> readOneAdmin(@PathVariable String id) {
        AdminDto adminDto = adminService.readOneAdmin(id);
        return EntityModel.of(adminDto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminController.class).readOneAdmin(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(AdminController.class).slash(id).withRel("update"),
                WebMvcLinkBuilder.linkTo(AdminController.class).slash(id).withRel("delete"));
    }

    @PutMapping("{id}")
    public EntityModel<AdminDto> updateAdmin(@PathVariable String id, @RequestBody AdminDto adminDto) {
        AdminDto updatedAdmin = adminService.updateAdmin(id, adminDto);
        return EntityModel.of(updatedAdmin,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminController.class).readOneAdmin(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(AdminController.class).slash(id).withRel("update"),
                WebMvcLinkBuilder.linkTo(AdminController.class).slash(id).withRel("delete"));
    }

    @DeleteMapping("{id}")
    public void deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);
    }
}
