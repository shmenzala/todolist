package com.pe.sh.todolist.controller;

import com.pe.sh.todolist.dto.TasksDto;
import com.pe.sh.todolist.dto.TasksDtoDeleteResponse;
import com.pe.sh.todolist.dto.TasksDtoResponse;
import com.pe.sh.todolist.service.TasksService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shmen
 */
@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TasksController {

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @PostMapping("/add")
    public ResponseEntity<TasksDtoResponse> crearTask(
            @RequestBody TasksDto taskDto) {
        return new ResponseEntity<>(tasksService.createTask(taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<TasksDtoResponse> listarTasks() {
        return tasksService.findAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasksDtoResponse> buscarTaskPorId(
            @PathVariable(value = "id") int id) {
        return ResponseEntity.ok(tasksService.findTaskById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TasksDtoResponse> actualizarTask(
            @RequestBody TasksDto taskDto,
            @PathVariable(value = "id") int id) {
        return new ResponseEntity<>(tasksService.updateTask(taskDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TasksDtoDeleteResponse> eliminarTask(
            @PathVariable(value = "id") int id) {
        return new ResponseEntity<>(tasksService.deleteTask(id), HttpStatus.OK);
    }

    @PutMapping("/update_status/{id}")
    public ResponseEntity<TasksDtoResponse> actualizarTaskStatus(
            @PathVariable(value = "id") int id) {
        return new ResponseEntity<>(tasksService.updateTaskStatus(id), HttpStatus.OK);
    }

}
