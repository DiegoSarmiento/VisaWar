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

  private getHeaders(): HttpHeaders {
    let header = new HttpHeaders();
    header = header.append('Content-Type', 'application/x-www-form-urlencoded');
    return header;
  }
  async inicia_sesion(){
    let body = "usuario="+this.user+"&password="+this.pass;
    await this.httpClient.post(Constants.DATA_LOCAL.loginUsuario, body, {headers: this.getHeaders(), responseType: 'text'}).subscribe(async res_data => {
      console.log('res_data',res_data);
      if (res_data != "0") {
        await localStorage.setItem('data_user',this.user);
        await this.router.navigate(['']);
      }else{
        this.error_login = true;
      }
    });

  }



  //#region ALERT
  cerrar_alert(){
    this.error_login = false;
  }


}
