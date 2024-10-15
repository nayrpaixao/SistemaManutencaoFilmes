import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarComponent} from './components/listar-component/listar-component.component';
import { ConsultarComponent } from './components/consultar-component/consultar-component.component';
import { HomeComponent } from './components/home/home.component';
import { CriarComponent } from './components/criar-component/criar-component.component';
import { EditarComponentComponent } from './components/editar-component/editar-component.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' }, 
  { path: 'home', component: HomeComponent },
  { path: 'listar', component: ListarComponent },
  { path: 'consultar', component: ConsultarComponent},
  { path: 'criar', component: CriarComponent},
  {path: 'editar/:id', component: EditarComponentComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

