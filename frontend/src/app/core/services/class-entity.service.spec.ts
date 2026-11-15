import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { environment } from '../../../environments/environment';
import { ClassEntityService } from './class-entity.service';

describe('ClassEntityService', () => {
  let service: ClassEntityService;
  let http: HttpTestingController;
  const url = `${environment.apiUrl}/class-entities`;

  beforeEach(() => {
    TestBed.configureTestingModule({ providers: [provideHttpClient(), provideHttpClientTesting()] });
    service = TestBed.inject(ClassEntityService);
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());

  it('should list all items', () => {
    service.findAll().subscribe(items => expect(items.length).toBe(1));
    const request = http.expectOne(url);
    expect(request.request.method).toBe('GET');
    request.flush([{ id: 1, name: 'Fishers Association', taxId: '12345678000199' }]);
  });

  it('should delete an item by id', () => {
    service.delete(1).subscribe();
    const request = http.expectOne(`${url}/1`);
    expect(request.request.method).toBe('DELETE');
    request.flush(null);
  });

});
