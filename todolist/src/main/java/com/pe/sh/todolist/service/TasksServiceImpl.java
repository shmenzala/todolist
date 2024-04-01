package com.pe.sh.todolist.service;

import com.pe.sh.todolist.dto.TasksDto;
import com.pe.sh.todolist.dto.TasksDtoDeleteResponse;
import com.pe.sh.todolist.dto.TasksDtoResponse;
import com.pe.sh.todolist.model.Tasks;
import com.pe.sh.todolist.repository.TasksRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;

    private final ModelMapper modelMapper;

    public TasksServiceImpl(TasksRepository tasksRepository, ModelMapper modelMapper) {
        this.tasksRepository = tasksRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TasksDtoResponse createTask(TasksDto dto) {
        Tasks task = toEntity(dto);

        task.setCreated_at(LocalDateTime.now());
        task.setStatus("incomplete");

        Tasks nuevoTask = tasksRepository.save(task);

        return toDtoResponse(nuevoTask);
    }

    @Override
    public List<TasksDtoResponse> findAllTasks() {
        List<Tasks> tasks = tasksRepository.findAll();
        return tasks.stream().map(task -> toDtoResponse(task)).collect(Collectors.toList());
    }

    @Override
    public TasksDtoResponse findTaskById(int id) {
        Tasks task = tasksRepository.findById(id).orElseThrow(null);
        return toDtoResponse(task);
    }

    @Override
    public TasksDtoResponse updateTask(TasksDto dto, int id) {
        Tasks task = tasksRepository.findById(id).orElseThrow(null);

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        Tasks actualizarTask = tasksRepository.save(task);

        return toDtoResponse(actualizarTask);
    }

    @Override
    public TasksDtoDeleteResponse deleteTask(int id) {
        Tasks task = tasksRepository.findById(id).orElseThrow(null);
        tasksRepository.delete(task);
        
        TasksDtoDeleteResponse tddr = toDtoDeleteResponse(task);
        tddr.setDeleteMessage("Task eliminado con éxito");
        
        return tddr;
    }

    @Override
    public TasksDtoResponse updateTaskStatus(int id) {
        Tasks task = tasksRepository.findById(id).orElseThrow(null);
        
        if (task.getStatus().equals("incomplete")) {
            task.setStatus("complete");
        } else {
            task.setStatus("incomplete");
        }
        
        Tasks actualizarTaskStatus = tasksRepository.save(task);

        return toDtoResponse(actualizarTaskStatus);
    }

    //ModelMapper configuration
    private Tasks toEntity(TasksDto dto) {
        Tasks task = modelMapper.map(dto, Tasks.class);
        return task;
    }

    private TasksDtoResponse toDtoResponse(Tasks task) {
        TasksDtoResponse dto = modelMapper.map(task, TasksDtoResponse.class);
        return dto;
    }
    
    private TasksDtoDeleteResponse toDtoDeleteResponse(Tasks task) {
        TasksDtoDeleteResponse dto = modelMapper.map(task, TasksDtoDeleteResponse.class);
        return dto;
    }

}
