import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { STATES } from '../../core/constants/states';
import { Vessel } from '../../core/models/vessel.model';
import { VesselService } from '../../core/services/vessel.service';

@Component({
  selector: 'app-vessels',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './vessels.component.html'
})
export class VesselsComponent implements OnInit {

  readonly states = STATES;
  vessels: Vessel[] = [];
  form: Vessel = this.empty();

  constructor(private vesselService: VesselService) {
  }

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.vesselService.findAll().subscribe(items => this.vessels = items);
  }

  save(): void {
    const request = this.form.id ? this.vesselService.update(this.form.id, this.form) : this.vesselService.create(this.form);
    request.subscribe(() => {
      this.clear();
      this.load();
    });
  }

  edit(vessel: Vessel): void {
    this.form = { ...vessel };
  }

  remove(vessel: Vessel): void {
    this.vesselService.delete(vessel.id!).subscribe(() => this.load());
  }

  clear(): void {
    this.form = this.empty();
  }

  private empty(): Vessel {
    return { name: '', registrationNumber: '', state: '', classEntityId: null };
  }

}
