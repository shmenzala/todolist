import { Component, OnInit } from '@angular/core';
import { Tasks, TasksFields } from './task';
import { TaskService } from './task.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public taskActual: Tasks = {} as Tasks;
  public tasksfields!: TasksFields;
  public tasks: Tasks[] = [];
  public inputTaskName: string = '';
  public inputTaskDescription: string = ''
  public error: boolean = false;
  public taskbarValue: number = 0;

  constructor(private taskService: TaskService) { }

  ngOnInit() {
    this.getTasks();
  }

  public getTasks(): void {
    this.taskService.getTasks().subscribe(
      {
        next: (response: Tasks[]) => {
          this.tasks = response;
          console.log("app.component.ts");
          console.log(this.tasks);
        },
        error: (error: HttpErrorResponse) => {
          alert(`Probando frontend, Error: ${error.message}`);
        }
      }
    );
  }

  public onPressNewTask(): void {
    const alertContent = document.getElementById("alertContent") as HTMLElement;

    if (this.inputTaskName.trim() == '') {
      alertContent.innerHTML = `<div class="alert alert-danger alert-dismissible fade show" role="alert">
         La tarea debe llevar un Título. <a href="#inputTask" class="alert-link">Task field</a>.
         <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
       </div>`
    } else {
      alertContent.innerHTML = ``;

      const titleTask: string = this.inputTaskName.trim();
      const descriptionTask: string = this.inputTaskDescription.trim();

      const taskToAdd: TasksFields = {
        title: titleTask,
        description: descriptionTask,
      }
      console.log(taskToAdd);

      this.taskService.postTask(taskToAdd).subscribe(
        {
          next: (response: TasksFields) => {
            console.log(response);
            this.getTasks();
          },
          error: (error: HttpErrorResponse) => {
            alert(`Error al crear la Task - ${error.message}`);
          }
        }
      );

      this.inputTaskName = '';
      this.inputTaskDescription = '';
    }
  }

  public taskbarPercentVal(): void {
    let completeCount = 0;
    let taskbarUnitPercentValue = (1 / this.tasks.length) * 100;

    this.taskService.getTasks().subscribe(
      {
        next: (tasks: Tasks[]) => {
          tasks.forEach(element => {
            if (element.status === "complete") {
              console.log("task complete counted");
              completeCount++;
            }
          });

          console.log("Tareas completas: " + completeCount);
          console.log(`${this.tasks.length - completeCount} Tareas incompletas.`)

          this.taskbarValue = Math.round((taskbarUnitPercentValue) * completeCount);
        }
      }
    );
  }

  public currentTaskEdit(currentTask: Tasks): void {
    this.taskActual = currentTask
  }

  public pressEditCurrentTask(taskId: number): void {
    const alertContentEdit = document.getElementById("alertContentEdit") as HTMLElement;

    if (this.taskActual.title.trim() == '') {
      alertContentEdit.innerHTML = `<div class="alert alert-danger alert-dismissible fade show" role="alert">
         La tarea debe llevar un Título. <a href="#inputTask" class="alert-link">Task field</a>.
         <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
       </div>`
    } else {

      alertContentEdit.innerHTML = ``;

      const taskToEdit: TasksFields = {
        title: this.taskActual.title.trim(),
        description: this.taskActual.description.trim(),
      }

      this.taskService.updateTask(taskToEdit, taskId).subscribe(
        {
          next: (response: Tasks) => {
            this.taskActual = response;
            this.getTasks();
            console.log(`Se actualizó la Task Nro. ${this.taskActual.id}`);
          },
          error: (error: HttpErrorResponse) => {
            alert(`Error al actualizar la Task nro.${taskId}: ${error.message}`);
          }
        }
      );
    }
  }

  public pressDeleteCurrentTask(currentTask: Tasks): void {
    this.taskActual = currentTask
    const taskId: number = this.taskActual.id;

    this.taskService.deleteTask(taskId).subscribe(
      {
        next: (response: string) => {
          console.log("app.component.ts - pressDeleteCurrentTask()");
          console.log(response);
          this.getTasks();
        },
        error: (error: HttpErrorResponse) => {
          alert(`Error al eliminar la tarea: ${error.message}`);
        }
      }
    );
  }

}
