import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Tasks } from '../task';
import { TaskService } from '../task.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent {

  @Input() public task !: Tasks;
  @Input() public tasks: Tasks[] = [];
  @Output() public taskbarPercent = new EventEmitter<void>();
  @Output() public taskToUpdate = new EventEmitter<Tasks>();
  @Output() public taskToDelete = new EventEmitter<Tasks>();

  constructor(private taskService: TaskService) { }

  public onPressCompleteTask(taskId: number): void {
    this.taskService.updateTaskStatus(taskId).subscribe(
      {
        next: (response: Tasks) => {
          this.task = response;
          this.taskbarPercent.emit();
        },
        error: (error: HttpErrorResponse) => {
          alert(`Error al actualizar estado de la tarea: ${error.message}`);
        }
      }
    );
  }

  public onPressEditTask(taskId: number): void {
    this.taskService.getTasksById(taskId).subscribe(
      {
        next: (response: Tasks) => {
          this.taskToUpdate.emit(response);
        },
        error: (error: HttpErrorResponse) => {
          alert(`No se encontró la tarea a editar: ${error.message}`);
        }
      }
    );
  }

  public onPressDeleteTask(taskId: number): void {
    this.taskService.getTasksById(taskId).subscribe(
      {
        next: (response: Tasks) => {
          this.taskToDelete.emit(response);
        },
        error: (error: HttpErrorResponse) => {
          alert(`No se encontró la tarea a eliminar: ${error.message}`);
        }
      }
    );
  }

}
