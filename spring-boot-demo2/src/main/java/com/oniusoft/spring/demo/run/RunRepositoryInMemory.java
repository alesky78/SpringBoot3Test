package com.oniusoft.spring.demo.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RunRepositoryInMemory implements RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepositoryInMemory.class);
    private final List<Run> runs = new ArrayList<>();

    @PostConstruct
    private void init() {
        runs.add(new Run(1L,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR,
                1));

        runs.add(new Run(2L,
                "Wednesday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                Location.INDOOR,
                1));
    }

    public List<Run> findAll() {
        return runs;
    }

    public Optional<Run> findById(Long id) {
        return Optional.ofNullable(runs.stream()
                .filter(run -> run.id() == id)
                .findFirst()
                .orElseThrow(RunNotFoundException::new));
    }

    public Run save(Run run){
        if(run.id()==null){
            Run newRun = new Run(runs.size()+1L,
                    run.title(),
                    run.startedOn(),
                    run.completedOn(),
                    run.miles(),
                    run.location(),
                    1);
            runs.add(newRun);
            return newRun;
        }else{
            Run found = findById(run.id()).get();
            Run newRun = new Run(found.id(),
                    run.title(),
                    run.startedOn(),
                    run.completedOn(),
                    run.miles(),
                    run.location(),
                    found.version()+1);
            runs.set(runs.indexOf(found), newRun);
            return newRun;
        }
    }

    public void deleteById(Long id) {
        log.info("Deleting Run: " + id);
        runs.removeIf(run -> run.id().equals(id));
    }

    public long count() {
        return runs.size();
    }


    public List<Run> findByLocation(String location) {
        return runs.stream()
                .filter(run -> Objects.equals(run.location(), location))
                .toList();
    }


}
