import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AplicacionComponent } from 'src/core/views/aplicacion/aplicacion.component';
import { ConfiguracionComponent } from 'src/core/views/configuracion/configuracion.component';
import { AppRoutingModule } from './app-routing.module';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { HeaderComponent } from 'src/core/views/header/header.component';
import { AplicacionModule } from 'src/core/views/aplicacion/aplicacion.module';
import { ConfiguracionModule } from 'src/core/views/configuracion/configuracion.module';
import { FormatofechaPipe } from 'src/pipe/formatofecha.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AplicacionModule,
    ConfiguracionModule
  ],
  providers: [{    
    provide: LocationStrategy, 
    useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
