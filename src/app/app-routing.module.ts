import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from 'src/guards/auth.guard';
import { LoginGuard } from 'src/guards/login.guard';


const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
  { path: 'home',  component: HomeComponent, canActivate: [AuthGuard]},
  { path: '', component:LoginComponent, canActivate: [LoginGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
