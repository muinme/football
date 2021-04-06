package com.example.football.services;

import org.springframework.security.core.Authentication;

public interface AppAuthorizerService {
    boolean authorize(Authentication authentication, String action, Object callerObj);
}

