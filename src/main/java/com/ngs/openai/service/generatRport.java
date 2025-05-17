package com.ngs.openai.service;

import org.springframework.stereotype.Service;

@Service
public class generatRport implements ReportService {
    @Override
    public String generateReport() {
        return "Generate Report Service";
    }
}
