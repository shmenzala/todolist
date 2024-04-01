package com.pe.sh.todolist.service;

import com.pe.sh.todolist.dto.TasksDto;
import com.pe.sh.todolist.dto.TasksDtoDeleteResponse;
import com.pe.sh.todolist.dto.TasksDtoResponse;
import java.util.List;

/**
 *
 * @author shmen
 */
public interface TasksService {

    public TasksDtoResponse createTask(TasksDto dto);

    public List<TasksDtoResponse> findAllTasks();

    public TasksDtoResponse findTaskById(int id);

    public TasksDtoResponse updateTask(TasksDto dto, int id);
    
    public TasksDtoResponse updateTaskStatus(int id);

    public TasksDtoDeleteResponse deleteTask(int id);

}
