package com.oniusoft.spring.demo.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/runs/")
public class RunController {

    //@Autowired --> il field injection non e piu consigliato per vari motivi:
    //multiple implementazioni
    //support al test
    private final RunRepository repository;

    //@Autowired --> questo e possibile, ma esiste un solo cosrtruttore quindi implicito
    public RunController(RunRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = repository.findById(id);
        if(run.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return run.get();
    }

    void create(@RequestBody Run run){
        repository.create(run);
    }

}
