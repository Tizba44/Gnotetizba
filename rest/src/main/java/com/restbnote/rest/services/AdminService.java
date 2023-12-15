package com.restbnote.rest.services;

import com.restbnote.rest.models.dto.AdminDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface AdminService {
    AdminDto createAdmin(final AdminDto adminDto);





    List<AdminDto> readAdmin();

    AdminDto readOneAdmin(final String id);

    AdminDto updateAdmin(final String id, final AdminDto  adminDto);
    String deleteAdmin(String id);
}


