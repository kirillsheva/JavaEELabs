package com.example.demo.Repository;

import com.example.demo.Entity.PermissionEntity;
import com.example.demo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
PermissionEntity findByPermission(Permission permission);


}
