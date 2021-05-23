package com.example.football.repositories;

import com.example.football.models.Permission;
import com.example.football.models.TeamFootBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query(value = "SELECT ug.name FROM user_group ug \n" +
            "INNER JOIN users u ON u.group_id = ug.id \n" +
            "WHERE u.username =:username", nativeQuery = true)
    String findPermissionByUsername(@Param("username") String username);

}
