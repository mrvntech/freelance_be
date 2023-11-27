package com.example.freelance_be.domain;

import java.util.Objects;
import java.util.Optional;

public enum JobStatus {
    OPEN("OPEN"),
    DOING("DOING"),
    CLOSE("CLOSE"),
    DONE("DONE")
    ;
    private final String name;

    JobStatus(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public static Optional<JobStatus> getJobStatus(String name){
        for(JobStatus jobStatus: JobStatus.values()){
            if(Objects.equals(name, jobStatus.name))return Optional.of(jobStatus);
        }
        return Optional.empty();
    }
}
