package com.example.freelance_be.dto.response.task;

import java.util.List;

public class GetAllTaskResponseBody {
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static class Task {
        private Long id;
        private String status;
    }
}
