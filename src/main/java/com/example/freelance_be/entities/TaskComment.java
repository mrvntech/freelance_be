package com.example.freelance_be.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity(name = "task_comment")
public class TaskComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date createAt;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
}
