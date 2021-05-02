package com.example.football.services;

import com.example.football.models.PostMatchTeam;
import com.example.football.models.RequestMatch;
import com.example.football.models.RequestPitch;

public interface MailService {
    String sendEmail(String username, PostMatchTeam postMatchTeam);

    String sendEmailBatDoi(String username, Integer wait_match_team_id);

    String sendEmailDatSan(String username, RequestPitch requestPitch);
}

