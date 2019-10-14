package com.alex.interview.sodamachine.service;

import com.alex.interview.sodamachine.entity.Soda;

import java.io.IOException;
import java.nio.file.Path;

public interface ReportingService {

    void doReporting(Soda soda, Path file) throws IOException;
}
