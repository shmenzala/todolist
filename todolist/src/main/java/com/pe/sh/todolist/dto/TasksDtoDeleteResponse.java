/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.todolist.dto;

import java.time.LocalDateTime;

/**
 *
 * @author shmen
 */
public class TasksDtoDeleteResponse {

    private int id;
    private String title;
    private String description;
    private LocalDateTime created_at;
    private String status;
    private String deleteMessage;

    public TasksDtoDeleteResponse() {
    }

    public TasksDtoDeleteResponse(int id, String title, String description, LocalDateTime created_at, String status, String deleteMessage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.status = status;
        this.deleteMessage = deleteMessage;
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

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(String deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

}
