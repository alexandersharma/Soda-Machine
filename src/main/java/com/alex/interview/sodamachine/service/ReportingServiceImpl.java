package com.alex.interview.sodamachine.service;

import com.alex.interview.sodamachine.entity.Soda;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
/**
 * ReportingServiceImpl.java - Handles reporting of each soda purchase
 * After each successful soda purchase it is written to a file
 */

@Service
public class ReportingServiceImpl implements ReportingService {

    @Override
    public void doReporting(Soda soda, Path file) throws IOException {
        BufferedWriter writer = null;
        try {
            //Create file if it doesn't exist already
            if(!Files.exists(file)){
                Files.createFile(file);
            }
            //Add new soda purchase to the report
            writer = Files.newBufferedWriter(file, Charset.defaultCharset(), StandardOpenOption.APPEND);
            writer.newLine();
            writer.write(soda.toString(), 0, soda.toString().length());

        }finally {
            if(writer !=null) {
                writer.close();
            }
        }
    }
}
