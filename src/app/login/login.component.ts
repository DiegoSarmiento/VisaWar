import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Constants } from 'src/assets/utils/constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public pass: string;
  public user: string;
  public error_login: boolean = false;

  constructor(private router: Router,
              private httpClient: HttpClient,) { }

  async inicia_sesion(){
    let body = "usuario="+this.user+"&password="+this.pass;
    await this.httpClient.post(Constants.DATA_LOCAL.loginUsuario, body, {headers: this.getHeaders()}).subscribe(async res_data => {
      if (res_data == 1) {
        await localStorage.setItem('data_user',this.user);
        await this.router.navigate(['/home']);
      }else{
        this.error_login = true;
      }
    });

  }

  private getHeaders(): HttpHeaders {
    let header = new HttpHeaders();
    header = header.append('Content-Type', 'application/x-www-form-urlencoded');
    return header;
  }

  //#region ALERT
  cerrar_alert(){
    this.error_login = false;
  }


}
