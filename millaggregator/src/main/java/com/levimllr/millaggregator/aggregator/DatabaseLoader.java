package com.levimllr.millaggregator.aggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final AggregatorRepository repository;

    @Autowired
    public DatabaseLoader(AggregatorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Aggregator("Millaggregator"));
        this.repository.save(new Aggregator("Parkerour"));
        this.repository.save(new Aggregator("Jennyhax"));
    }
}
