package com.alex.interview.sodamachine;

import com.alex.interview.sodamachine.entity.Soda;
import com.alex.interview.sodamachine.service.ReportingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingServiceImplTests {

    private static final Path TEST_REPORT = Paths.get("src/main/resources/testReport.txt");

    @Autowired
    ReportingService reportingService;

    @Before
    public void init() throws IOException {
      //Make sure test file location is deleted before each test
        Files.deleteIfExists(TEST_REPORT);
    }

    @Test
    public void shouldCreateTestReportFile() throws IOException {
        Soda soda = new Soda();
        soda.setId(2);
        soda.setName("Sprite");
        reportingService.doReporting(soda, TEST_REPORT);

        assertTrue(Files.exists(TEST_REPORT));
    }

    @Test
    public void shouldAddSodaToReportFile() throws IOException {
        //Create soda
        Soda soda = new Soda();
        soda.setId(1);
        soda.setName("Coke");

        //Do reporting
        reportingService.doReporting(soda, TEST_REPORT);

        //Create list of the lines in the file
        Stream<String> stream = Files.lines(TEST_REPORT);
        List<String> list = stream.collect(Collectors.toList());

        assertEquals(2, list.size());
    }
}
