package com.example.football.services.Impl;

import com.example.football.repositories.PermissionRepository;
import com.example.football.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public String getPermissionByUsername(String username) {
        return permissionRepository.findPermissionByUsername(username);
    }
}
