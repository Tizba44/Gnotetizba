package com.restbnote.rest.services;

import com.restbnote.rest.models.dto.AdminDto;

import java.util.List;


public interface AdminService {
    AdminDto createAdmin(final AdminDto adminDto);
    List<AdminDto> readAdmin();
    AdminDto updateAdmin(final String id, final AdminDto  adminDto);
    String deleteAdmin(String id);
}


