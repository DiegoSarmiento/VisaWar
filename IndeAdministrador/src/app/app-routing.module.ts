import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfiguracionComponent } from 'src/core/views/configuracion/configuracion.component';
import { AplicacionComponent } from 'src/core/views/aplicacion/aplicacion.component';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'configuracion/:usuario', component: ConfiguracionComponent},
  { path: 'aplicacion/:usuario', component: AplicacionComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
