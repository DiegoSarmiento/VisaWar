import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Constants } from 'src/utils/constants';

@Component({
  selector: 'app-configuracion',
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent implements OnInit {

  constructor(    
    private httpClient: HttpClient,
    ) { }

  ngOnInit() {
    this.listarConfiguraciones();
  }

  listaConfiguraciones;
  filaSeleccionada;
  seleccionadaConfiguracion;

  async listarConfiguraciones(){
    await this.httpClient.get(Constants.SERVICIOS_CONFIGURACION.obtenerconfiguraciones, {headers: this.getHeaders()}).subscribe(async res_detalle => {
      this.listaConfiguraciones = res_detalle;
    });
  }

  seleccionarConfiguracion(aplicacion: object, indexfila: number){
    this.filaSeleccionada = indexfila.toString();
    this.seleccionadaConfiguracion = aplicacion;
  }

  private getHeaders(): HttpHeaders {
    let header = new HttpHeaders();
    header = header.append('Content-Type', 'application/x-www-form-urlencoded');
    return header;
  };
 
}
