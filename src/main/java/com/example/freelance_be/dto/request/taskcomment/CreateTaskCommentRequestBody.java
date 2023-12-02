package com.example.freelance_be.dto.request.taskcomment;

public class CreateTaskCommentRequestBody {
    private Long taskId;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
