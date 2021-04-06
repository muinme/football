package com.example.football.repositories;

import com.example.football.models.PostMatchTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMatchTeamRepository extends JpaRepository<PostMatchTeam, Integer> {
}
