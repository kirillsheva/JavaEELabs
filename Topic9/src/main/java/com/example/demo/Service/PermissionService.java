package com.example.demo.Service;


import com.example.demo.Entity.PermissionEntity;
import com.example.demo.Permission;
import com.example.demo.Repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    @Transactional
    public void savePermissions()
    {
        permissionRepository.save(PermissionEntity.builder().permission(Permission.USER).build());
        permissionRepository.save(PermissionEntity.builder().permission(Permission.ADMIN).build());
    }

}
