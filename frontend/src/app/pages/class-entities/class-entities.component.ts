import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ClassEntity } from '../../core/models/class-entity.model';
import { ClassEntityService } from '../../core/services/class-entity.service';

@Component({
  selector: 'app-class-entities',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './class-entities.component.html'
})
export class ClassEntitiesComponent implements OnInit {

  classEntities: ClassEntity[] = [];
  form: ClassEntity = this.empty();

  constructor(private classEntityService: ClassEntityService) {
  }

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.classEntityService.findAll().subscribe(items => this.classEntities = items);
  }

  save(): void {
    const request = this.form.id ? this.classEntityService.update(this.form.id, this.form) : this.classEntityService.create(this.form);
    request.subscribe(() => {
      this.clear();
      this.load();
    });
  }

  edit(classEntity: ClassEntity): void {
    this.form = { ...classEntity };
  }

  remove(classEntity: ClassEntity): void {
    this.classEntityService.delete(classEntity.id!).subscribe(() => this.load());
  }

  clear(): void {
    this.form = this.empty();
  }

  private empty(): ClassEntity {
    return { name: '', taxId: '' };
  }

}
