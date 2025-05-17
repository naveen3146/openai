package com.ngs.openai.service;

import org.springframework.stereotype.Service;

@Service
public class RunbookService implements ReportService {
    @Override
    public String generateReport() {
        return "Run book Service Report";
    }
}
