package com.oniusoft.spring.demo.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    @PostConstruct
    private void init() {
        runs.add(new Run(1,"prima corsa", LocalDateTime.now(),LocalDateTime.now().plus(3, ChronoUnit.HOURS), Location.INDOOR));
        runs.add(new Run(2,"seconda corsa", LocalDateTime.now(),LocalDateTime.now().plus(3, ChronoUnit.HOURS), Location.INDOOR));
        runs.add(new Run(3,"terza corsa", LocalDateTime.now(),LocalDateTime.now().plus(3, ChronoUnit.HOURS), Location.OUTDOOR));
    }

    public Optional<Run> findById(int id) {
        return runs.stream().filter(run -> run.id() == id ).findFirst();
    }

    List<Run> findAll() {
        return runs;
    }

    public void create(Run run) {
        runs.add(run);
    }
}
