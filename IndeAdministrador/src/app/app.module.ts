import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { AplicacionModule } from 'src/core/views/aplicacion/aplicacion.module';
import { ConfiguracionModule } from 'src/core/views/configuracion/configuracion.module';
import { HeaderModule } from 'src/core/views/header/header.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AplicacionModule,
    ConfiguracionModule,
    HeaderModule
  ],
  providers: [{    
    provide: LocationStrategy, 
    useClass: HashLocationStrategy}],
  bootstrap: [AppComponent],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule { }
