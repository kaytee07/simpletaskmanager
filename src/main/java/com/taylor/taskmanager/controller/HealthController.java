package com.taylor.taskmanager.controller;

import com.taylor.taskmanager.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, Object>>> healthCheck() {
        Map<String, Object> healthInfo = new HashMap<>();
        healthInfo.put("status", "UP");
        healthInfo.put("application", appName);
        healthInfo.put("profile", activeProfile);
        healthInfo.put("timestamp", java.time.LocalDateTime.now());

        return ResponseEntity.ok(ApiResponse.success("Service is healthy", healthInfo));
    }

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<Map<String, Object>>> appInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", appName);
        info.put("version", "1.0.0");
        info.put("description", "Task Management System");
        info.put("environment", activeProfile);

        return ResponseEntity.ok(ApiResponse.success("Application information", info));
    }
}