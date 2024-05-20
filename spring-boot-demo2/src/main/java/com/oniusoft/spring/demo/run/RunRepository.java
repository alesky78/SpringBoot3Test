package com.oniusoft.spring.demo.run;

import java.util.List;
import java.util.Optional;

public interface RunRepository {

    List<Run> findAll();

    Optional<Run> findById(Long id);

    Run save(Run entity);

    void deleteById(Long id);

    long count();

    List<Run> findByLocation(String location);
}
