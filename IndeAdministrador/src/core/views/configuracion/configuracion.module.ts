import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfiguracionComponent } from './configuracion.component';
import { FormatofechaConfiguracionPipe } from 'src/pipe/formatofecha-configuracion.pipe';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [ConfiguracionComponent, FormatofechaConfiguracionPipe],
  imports: [
    CommonModule,
    HttpClientModule,
    NgbModule,
    FormsModule
  ]
})
export class ConfiguracionModule { }
