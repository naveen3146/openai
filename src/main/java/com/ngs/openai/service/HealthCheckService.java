package com.ngs.openai.service;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckService implements ReportService {
    @Override
    public String generateReport() {
        return "Health Check Service Report";
    }
}
