package com.oniusoft.spring.demo.run;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/runs")
public class RunController {

    //@Autowired --> il field injection non e piu consigliato (ora avviene attraverso il costruttore) per vari motivi:
    //multiple implementazionil support al test
    private final RunRepository repository;

    //@Autowired --> questo e possibile qui, ma esiste un solo cosrtruttore quindi implicito
    public RunController(@Qualifier("runRepositoryJdbcData") RunRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Long id) {
        Optional<Run> run = repository.findById(id);
        if(run.isEmpty()){
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            throw new RunNotFoundException();
        }
        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Run create(@Valid @RequestBody Run run){
        return repository.save(run);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("")
    Run update(@Valid @RequestBody Run run){
        return repository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

}
