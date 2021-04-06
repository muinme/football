package com.example.football.repositories;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.football.models.Permission;

public interface AppAuthorizerRepository extends JpaRepository<Permission, Integer> {

    @Query(value = "SELECT p.name" +
            " FROM permissions p\n" +
            " INNER JOIN users u ON p.id = u.permission_id\n" +
            " Where u.username = :username" , nativeQuery = true)
    String  findNameRole(@Param("username") String username);
}
