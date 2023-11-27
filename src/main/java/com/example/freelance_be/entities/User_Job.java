package com.example.freelance_be.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class User_Job {
    @Id
    private Long id;
    @ManyToOne
    private User applier;
    @ManyToOne
    private User watcher;
    private String watchAt;
    private String applyAt;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @ManyToOne
    private Job job;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getApplier() {
        return applier;
    }

    public void setApplier(User applier) {
        this.applier = applier;
    }

    public User getWatcher() {
        return watcher;
    }

    public void setWatcher(User watcher) {
        this.watcher = watcher;
    }

    public String getWatchAt() {
        return watchAt;
    }

    public void setWatchAt(String watchAt) {
        this.watchAt = watchAt;
    }

    public String getApplyAt() {
        return applyAt;
    }

    public void setApplyAt(String applyAt) {
        this.applyAt = applyAt;
    }
}
