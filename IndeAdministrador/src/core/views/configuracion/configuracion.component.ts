import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Constants } from 'src/utils/constants';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-configuracion',
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent implements OnInit {

  constructor(    
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private route: ActivatedRoute
    ) { }

  ngOnInit() {
    this.listarConfiguraciones();
    this.obtenerIp();
    this.dataMail.usuario_link = this.route.snapshot.paramMap.get("usuario");
    console.log("thi",this.dataMail);
  }

  listaConfiguraciones;
  filaSeleccionada;
  seleccionadaConfiguracion;
  dataTerminos;
  host;

  dataMail={
    "template" : "",
    "asunto" : "",
    "servidor" : "",
    "puerto" : "",
    "remitente" : "",
    "protocolo" : "",
    "usuario" : "",
    "password" :"",
    "usuario_link": ""
  }
  async obtenerIp(){
    await this.httpClient.get("http://api.ipify.org/?format=json").subscribe(async res_data => { this.host = await (res_data["ip"]).toString()});
  }

  async listarConfiguraciones(){
    await this.httpClient.get(Constants.SERVICIOS_CONFIGURACION.obtenerconfiguraciones, {headers: this.getHeaders()}).subscribe(async res_detalle => {
      this.listaConfiguraciones = res_detalle;
      console.log("this.listaConfiguraciones",this.listaConfiguraciones);
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

  async abrirModalConfiguracion(modalTerminos,modalEmail){
    if (this.seleccionadaConfiguracion === undefined) {
      return null;
    }else{
      if (this.seleccionadaConfiguracion[0] == "XD001" && this.seleccionadaConfiguracion[0] !== undefined) {
        await this.httpClient.get(Constants.SERVICIOS_CONFIGURACION.obtenerterminos, {headers: this.getHeaders(), responseType: 'text'}).subscribe(async res_detalle => {
          this.dataTerminos = res_detalle;
          this.abrirModal(modalTerminos);
        });
      } else if(this.seleccionadaConfiguracion[0] == "XD002" && this.seleccionadaConfiguracion[0] !== undefined){
        await this.httpClient.get(Constants.SERVICIOS_CONFIGURACION.obtenercorreo, {headers: this.getHeaders()}).subscribe(async res_detalle => {
          console.log("res_detalle",res_detalle);
          this.dataMail.template =  res_detalle[0][1];
          this.dataMail.asunto =  res_detalle[0][2];
          this.dataMail.servidor =  res_detalle[0][3];
          this.dataMail.puerto =  res_detalle[0][4];
          this.dataMail.remitente =  res_detalle[0][5];
          this.dataMail.protocolo =  res_detalle[0][9];
          this.dataMail.usuario =  res_detalle[0][10];
          this.dataMail.password =  res_detalle[0][11];
          this.abrirModal(modalEmail);
        });
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
    console.log("this",this.dataMail);
    let body = "PIN_NU_CODPAR="+this.listaConfiguraciones[1][0] + 
               "&PIN_VC_TEMPLATE="+this.dataMail.template+
               "&PIN_VC_ASUNTO="+this.dataMail.asunto+
               "&PIN_VC_SERSMTP="+this.dataMail.servidor+
               "&PIN_VC_PUERTO="+this.dataMail.puerto+
               "&PIN_VC_REMITENTE="+this.dataMail.remitente+
               "&PIN_VC_USUARIO_MODIFICACION=root"+
               "&PIN_VC_HOSTNAME_MODIFICACION="+this.host+
               "&PIN_VC_PROTOCOLO="+this.dataMail.protocolo+
               "&PIN_VC_USUARIO="+this.dataMail.usuario+
               "&PIN_VC_PASSWORD="+this.dataMail.password;
    await this.httpClient.put(Constants.SERVICIOS_CONFIGURACION.modificarcorreo, body, {headers: this.getHeaders()}).subscribe(async res_detalle => {
      if (res_detalle) {
        console.log("Exito al modificar");
      }else{
        console.log("Error al modificar");
      }
      this.dataTerminos = "";
    });
  }
 
}
