import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Constants } from 'src/utils/constants';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-configuracion',
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent implements OnInit {

  constructor(    
    private httpClient: HttpClient,
    private modalService: NgbModal
    ) { }

  ngOnInit() {
    this.listarConfiguraciones();
    this.obtenerIp();
  }

  listaConfiguraciones;
  filaSeleccionada;
  seleccionadaConfiguracion;
  dataTerminos;
  host;

  dataMail={
    "html" : "",
    "asunto" : "",
    "servidor" : "",
    "puerto" : "",
    "remitente" : ""
  }
  async obtenerIp(){
    await this.httpClient.get("http://api.ipify.org/?format=json").subscribe(async res_data => { this.host = await (res_data["ip"]).toString()});
  }

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

  abrirModalConfiguracion(modalTerminos,modalEmail){
    if (this.seleccionadaConfiguracion === undefined) {
      return null;
    }else{
      if (this.seleccionadaConfiguracion[0] == "XD001" && this.seleccionadaConfiguracion[0] !== undefined) {
        this.abrirModal(modalTerminos);
      } else if(this.seleccionadaConfiguracion[0] == "XD002" && this.seleccionadaConfiguracion[0] !== undefined){
        this.abrirModal(modalEmail);
      }
    }
  };

  abrirModal(modal){
    this.modalService.open(modal, { 
      size: 'lg',
      scrollable: true,
      backdrop: false,
      centered: true
    });
  };
  
  cerrarModal(modal){
    this.modalService.dismissAll(modal);
  }

  cerrarModalConfiguracion(modalTerminos){
    this.cerrarModal(modalTerminos);
    this.dataTerminos = "";
  }

  async modificarTerminos(modalDesactivar){
    this.cerrarModal(modalDesactivar);
    let body = "dataterminos="+this.dataTerminos + "&codpar="+this.listaConfiguraciones[0][0]+"&host="+this.host;
    await this.httpClient.put(Constants.SERVICIOS_CONFIGURACION.modificarterminos,body, {headers: this.getHeaders()}).subscribe(async res_detalle => {
      if (res_detalle) {
        console.log("Exito al modificar");
      }else{
        console.log("Error al modificar")
      }
      this.dataTerminos = "";
    });
  }


  async modificarEmail(modalDesactivar){
    this.cerrarModal(modalDesactivar);
    let body = "PIN_NU_CODPAR="+this.listaConfiguraciones[1][0] + 
               "&PIN_CL_HTML="+this.dataMail.html+
               "&PIN_VC_ASUNTO="+this.dataMail.asunto+
               "&PIN_VC_SERSMTP="+this.dataMail.servidor+
               "&PIN_VC_PUERTO="+this.dataMail.puerto+
               "&PIN_VC_REMITENTE="+this.dataMail.remitente+
               "&PIN_VC_HOSTNAME_MODIFICACION="+this.host;
    await this.httpClient.put(Constants.SERVICIOS_CONFIGURACION.modificarcorreo, body, {headers: this.getHeaders()}).subscribe(async res_detalle => {
      if (res_detalle) {
        console.log("Exito al modificar");
      }else{
        console.log("Error al modificar")
      }
      this.dataTerminos = "";
    });
  }
 
}
