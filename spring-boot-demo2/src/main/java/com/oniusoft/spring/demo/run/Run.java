package com.oniusoft.spring.demo.run;

import java.time.LocalDateTime;

public record Run(Integer id,
                  String title,
                  LocalDateTime startedOn,
                  LocalDateTime completeOn,
                  Location location
) {}
