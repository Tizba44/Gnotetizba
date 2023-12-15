package com.restbnote.rest.services.impl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.restbnote.rest.configs.exceptions.MyException;
import com.restbnote.rest.configs.exceptions.MyExceptionPayLoad;
import com.restbnote.rest.models.dto.AdminDto;
import com.restbnote.rest.models.entities.AdminEntity;
import com.restbnote.rest.repositories.AdminRepository;
import com.restbnote.rest.services.AdminService;

import java.util.ArrayList;
import java.util.List;





@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        AdminEntity admin = new AdminEntity();
        admin.setAdminMailID(adminDto.getAdminMailID());
        admin.setAdminPassword(adminDto.getAdminPassword());
        admin = adminRepository.save(admin);
        return AdminDto.builder()
                .id(admin.getId())
                .adminMailID(admin.getAdminMailID())
                .adminPassword(admin.getAdminPassword())
                .build();
    }

    @Override
    public List<AdminDto> readAdmin() {
        List<AdminEntity> admins = adminRepository.findAll();
        List<AdminDto> adminDtos = new ArrayList<>();
        admins.stream()
                .map(p -> AdminDto.builder()
                        .id(p.getId())
                        .adminMailID(p.getAdminMailID())
                        .adminPassword(p.getAdminPassword())
                        .build()).forEach(adminDtos::add);
        return adminDtos;
    }





    @Override
    public AdminDto readOneAdmin(String id) {
        AdminEntity admin = adminRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("admin non trouvé")
                                .build()
                ));
        return AdminDto.builder()
                .id(admin.getId())
                .adminMailID(admin.getAdminMailID())
                .adminPassword(admin.getAdminPassword())
                .build();
    }







    @Override
    public AdminDto updateAdmin(String id, AdminDto adminDto) {
        return adminRepository.findById(id)
                .map(existingAdmin -> {
                    if (adminDto.getAdminMailID() != null && !adminDto.getAdminMailID().isEmpty()) {
                        existingAdmin.setAdminMailID(adminDto.getAdminMailID());
                    }
                    if (adminDto.getAdminPassword() != null && !adminDto.getAdminPassword().isEmpty()) {
                        existingAdmin.setAdminPassword(adminDto.getAdminPassword());
                    }

                    adminRepository.save(existingAdmin);
                    adminDto.setId(existingAdmin.getId());
                    return adminDto;
                })
                .orElseGet(() -> {
                    // Si l'admin n'existe pas, créez un nouvel admin
                    AdminEntity newAdmin = new AdminEntity();
                    newAdmin.setAdminMailID(adminDto.getAdminMailID());
                    newAdmin.setAdminPassword(adminDto.getAdminPassword());
                    newAdmin = adminRepository.save(newAdmin);
                    return AdminDto.builder()
                            .id(newAdmin.getId())
                            .adminMailID(newAdmin.getAdminMailID())
                            .adminPassword(newAdmin.getAdminPassword())
                            .build();
                });
    }


    @Override
    public String deleteAdmin(String id) {
        AdminEntity p = adminRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                            .httpCode(404)
                            .message("produit non trouvé")
                            .build()
                ));
        adminRepository.delete(p);
        return "admin supprimé";
    }
}

