import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Vessel } from '../models/vessel.model';

@Injectable({ providedIn: 'root' })
export class VesselService {

  private readonly url = `${environment.apiUrl}/vessels`;

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<Vessel[]> {
    return this.http.get<Vessel[]>(this.url);
  }

  findById(id: number): Observable<Vessel> {
    return this.http.get<Vessel>(`${this.url}/${id}`);
  }

  create(vessel: Vessel): Observable<Vessel> {
    return this.http.post<Vessel>(this.url, vessel);
  }

  update(id: number, vessel: Vessel): Observable<Vessel> {
    return this.http.put<Vessel>(`${this.url}/${id}`, vessel);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

}
