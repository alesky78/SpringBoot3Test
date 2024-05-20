package com.oniusoft.spring.demo.run;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface RunRepositoryJdbcData extends RunRepository, ListCrudRepository<Run,Long>  {

}
