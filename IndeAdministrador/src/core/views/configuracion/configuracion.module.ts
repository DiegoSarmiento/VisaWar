import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfiguracionComponent } from './configuracion.component';
import { FormatofechaConfiguracionPipe } from 'src/pipe/formatofecha-configuracion.pipe';



@NgModule({
  declarations: [ConfiguracionComponent, FormatofechaConfiguracionPipe],
  imports: [
    CommonModule
  ]
})
export class ConfiguracionModule { }
