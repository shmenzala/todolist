package com.pe.sh.todolist.dto;

import java.time.LocalDateTime;

/**
 *
 * @author shmen
 */
public class TasksDtoResponse {

    private int id;
    private String title;
    private String description;
    private LocalDateTime created_at;
    private String status;

    public TasksDtoResponse() {
    }

    public TasksDtoResponse(int id, String title, String description, LocalDateTime created_at, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
