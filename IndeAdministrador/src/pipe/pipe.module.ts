import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormatofechaPipe } from './formatofecha.pipe';
import { FormatofechaConfiguracionPipe } from './formatofecha-configuracion.pipe';



@NgModule({
  declarations: [FormatofechaPipe, FormatofechaConfiguracionPipe],
  imports: [
    CommonModule
  ]
})
export class PipeModule { }
