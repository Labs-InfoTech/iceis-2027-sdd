import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ClassEntity } from '../models/class-entity.model';

@Injectable({ providedIn: 'root' })
export class ClassEntityService {

  private readonly url = `${environment.apiUrl}/class-entities`;

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<ClassEntity[]> {
    return this.http.get<ClassEntity[]>(this.url);
  }

  findById(id: number): Observable<ClassEntity> {
    return this.http.get<ClassEntity>(`${this.url}/${id}`);
  }

  create(classEntity: ClassEntity): Observable<ClassEntity> {
    return this.http.post<ClassEntity>(this.url, classEntity);
  }

  update(id: number, classEntity: ClassEntity): Observable<ClassEntity> {
    return this.http.put<ClassEntity>(`${this.url}/${id}`, classEntity);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

}
