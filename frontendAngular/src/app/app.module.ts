import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListarComponent} from './components/listar-component/listar-component.component';
import { provideHttpClient } from '@angular/common/http';
import { ConsultarComponent } from './components/consultar-component/consultar-component.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { CriarComponent } from './components/criar-component/criar-component.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { EditarComponentComponent } from './components/editar-component/editar-component.component';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';




@NgModule({
  declarations: [
    AppComponent,
    ListarComponent,
    ConsultarComponent,
    HomeComponent,
    CriarComponent,
    EditarComponentComponent,
    ConfirmDialogComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatIconModule,
    MatTooltipModule,
    MatDialogModule
        ],
  providers: [
    provideHttpClient(),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent],
  })
export class AppModule { }
