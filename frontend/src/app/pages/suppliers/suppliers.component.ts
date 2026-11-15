import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { STATES } from '../../core/constants/states';
import { Supplier } from '../../core/models/supplier.model';
import { SupplierService } from '../../core/services/supplier.service';

@Component({
  selector: 'app-suppliers',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './suppliers.component.html'
})
export class SuppliersComponent implements OnInit {

  readonly states = STATES;
  suppliers: Supplier[] = [];
  form: Supplier = this.empty();

  constructor(private supplierService: SupplierService) {
  }

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.supplierService.findAll().subscribe(items => this.suppliers = items);
  }

  save(): void {
    const request = this.form.id ? this.supplierService.update(this.form.id, this.form) : this.supplierService.create(this.form);
    request.subscribe(() => {
      this.clear();
      this.load();
    });
  }

  edit(supplier: Supplier): void {
    this.form = { ...supplier };
  }

  remove(supplier: Supplier): void {
    this.supplierService.delete(supplier.id!).subscribe(() => this.load());
  }

  clear(): void {
    this.form = this.empty();
  }

  private empty(): Supplier {
    return { name: '', taxId: '', state: '' };
  }

}
