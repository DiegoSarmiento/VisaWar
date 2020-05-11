import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AplicacionComponent } from './aplicacion.component';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { FormatofechaPipe } from 'src/pipe/formatofecha.pipe';



@NgModule({
  declarations: [AplicacionComponent, FormatofechaPipe],
  imports: [
    CommonModule,
    HttpClientModule,
    NgbModule,
    FormsModule
  ]
})
export class AplicacionModule { }
