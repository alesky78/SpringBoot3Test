package com.oniusoft.spring.demo.run;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

/**
 * example of CommandLineRunner that load data in DB after start up of the application
 * parsing a json
 *
 */
@Component
public class CommandLineRunnerJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerJsonDataLoader.class);
    private final ObjectMapper objectMapper;
    private final RunRepository runRepository;

    public CommandLineRunnerJsonDataLoader(ObjectMapper objectMapper, @Qualifier("runRepositoryJdbcData") RunRepository runRepository) {
        this.objectMapper = objectMapper;
        this.runRepository = runRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //if(runRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/runs.json")) {
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON data and saving to in-memory collection.", allRuns.runs().size());

                allRuns.runs().stream().forEach(run ->  runRepository.save(run));

            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
//        } else {
//            log.info("Not loading Runs from JSON data because the collection contains data.");
//        }
    }

}
