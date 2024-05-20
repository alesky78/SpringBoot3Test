package com.oniusoft.spring.demo.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * concrete example of a repository that use the JDBC client
 *
 */
@Repository
public class RunRepositoryJdbcClient implements RunRepository {

    private static final Logger logger = LoggerFactory.getLogger(RunRepositoryJdbcClient.class);

    private final JdbcClient jdbcClient;

    public RunRepositoryJdbcClient(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Long id) {
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location,version FROM Run WHERE id = :id" )
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public Run save(Run run){

        var present = findById(run.id());

        if(present.isEmpty()){
            return create(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location());

        }else{
            Run oldRun = present.get();
            Run newRun = new Run(oldRun.id(),
                    run.title(),
                    run.startedOn(),
                    run.completedOn(),
                    run.miles(),
                    run.location(),
                    oldRun.version());
            update(newRun);
            return newRun;
        }
    }

    private Run create(String title, LocalDateTime startedOn, LocalDateTime completedOn, Integer miles, Location location) {
        var id = new GeneratedKeyHolder();
        var version = 1;
        var insert = jdbcClient.sql("INSERT INTO Run(title,started_on,completed_on,miles,location,version) values(?,?,?,?,?,?)")
                                .params(List.of(title,startedOn,completedOn,miles,location.toString(),version)).update(id);

        Assert.state(insert == 1, "Failed to create run " + title);

        return new Run(id.getKeyAs(Long.class), title, startedOn, completedOn, miles, location, version);
    }

    private void update(Run run) {
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ?, version = version + 1 where id = ? and version = ?")
                .params(List.of(run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString(), run.id(), run.version()))
                .update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void deleteById(Long id) {
        var updated = jdbcClient.sql("delete from run where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    public long count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }


    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }

}
