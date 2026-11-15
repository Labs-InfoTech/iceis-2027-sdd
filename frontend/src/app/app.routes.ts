import { Routes } from '@angular/router';
import { ClassEntitiesComponent } from './pages/class-entities/class-entities.component';
import { SuppliersComponent } from './pages/suppliers/suppliers.component';
import { VesselsComponent } from './pages/vessels/vessels.component';

export const routes: Routes = [
  { path: 'class-entities', component: ClassEntitiesComponent },
  { path: 'vessels', component: VesselsComponent },
  { path: 'suppliers', component: SuppliersComponent },
  { path: '', redirectTo: 'vessels', pathMatch: 'full' }
];
