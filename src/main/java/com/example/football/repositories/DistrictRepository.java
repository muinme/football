package com.example.football.repositories;

import com.example.football.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query(value = "SELECT * FROM district d \n" +
            "WHERE d.province_code=:province_code ", nativeQuery = true)
    List<District>findByProvinceCode(@Param("province_code") Integer province_code);
}
