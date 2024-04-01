import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tasks, TasksFields } from './task';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTasks(): Observable<Tasks[]> {
    return this.http.get<Tasks[]>(`${this.apiServerUrl}/api/v1/tasks/all`);
  }

  public getTasksById(taskId: number): Observable<Tasks> {
    return this.http.get<Tasks>(`${this.apiServerUrl}/api/v1/tasks/${taskId}`);
  }

  public postTask(task: TasksFields): Observable<TasksFields> {
    return this.http.post<TasksFields>(`${this.apiServerUrl}/api/v1/tasks/add`, task);
  }

  public updateTask(task: TasksFields, taskId: number): Observable<Tasks> {
    return this.http.put<Tasks>(`${this.apiServerUrl}/api/v1/tasks/update/${taskId}`, task);
  }

  public deleteTask(taskId: number): Observable<string> {
    return this.http.delete<string>(`${this.apiServerUrl}/api/v1/tasks/delete/${taskId}`);
  }

  public updateTaskStatus(taskId: number): Observable<Tasks> {
    return this.http.put<Tasks>(`${this.apiServerUrl}/api/v1/tasks/update_status/${taskId}`, this.getTasksById(taskId));
  }

}