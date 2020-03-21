import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public pass: string;
  public user: string;

  constructor(private router: Router) { }

  async inicia_sesion(){
    let body = {
      "user": this.user,
      "pass": this.pass
    }
    console.log('body',body);
    await localStorage.setItem('data_user',JSON.stringify(body));
    await this.router.navigate(['/home']);
  }


}
