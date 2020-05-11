import { Component, OnInit } from '@angular/core';
import { Constants } from 'src/utils/constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-aplicacion',
  templateUrl: './aplicacion.component.html',
  styleUrls: ['./aplicacion.component.css']
})
export class AplicacionComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private modalService: NgbModal,
  ) { }

  ngOnInit() {
    this.listarAplicativos();
    this.obtenerIp();
  }
  
  //Variables
  listaAplicaciones;
  filaSeleccionada: string = "-1";
  seleccionadaAplicacion;

  //Variables insert
  codigoValue;
  descripcionValue;
  host;
  switchPasarela = false;
  recordarTarjeta = "Recordar Tarjeta";
  pagarCuotas = "Pagar Cuotas";
  ambasOpciones = "Ambas opciones";
  dataRButton = "Recordar Tarjeta";
  flag;

  async listarAplicativos(){
    await this.httpClient.get(Constants.SERVICIOS_APLICACIONES.obtenerAplicativos, {headers: this.getHeaders()}).subscribe(async res_detalle => {
      this.listaAplicaciones = res_detalle;
      console.log("res_detalle",this.listaAplicaciones);
    });
  }

  async obtenerIp(){
    await this.httpClient.get("http://api.ipify.org/?format=json").subscribe(async res_data => { this.host = await (res_data["ip"]).toString()});
  }
  
  seleccionarAplicacion(aplicacion: object, indexfila: number){
    this.filaSeleccionada = indexfila.toString();
    this.seleccionadaAplicacion = aplicacion;
  }

  nuevoModal(modal){
    this.flag = 0;
    this.abrirModal(modal);
  }

  abrirModal(modal){
    this.modalService.open(modal, { 
      size: 'lg',
      scrollable: true,
      backdrop: false,
      centered: true
    });
  }

  cerrarModal(modal){
    this.modalService.dismissAll(modal);
  }

  async agregarAplicacion(modalAgregar){
    let body = "codigo="+this.codigoValue.toString()+"&descripcion="+this.descripcionValue+"&permiso="+this.switchPasarela+"&tipo="+ this.dataRButton+"&hostname="+this.host;
    if (this.flag != 1) {
      await this.httpClient.post(Constants.SERVICIOS_APLICACIONES.agregarAplicacion,body, {headers: this.getHeaders()}).subscribe(async res_data => {
        if(res_data == true){
          await this.listarAplicativos();
          await this.cerrarModal(modalAgregar);
        } 
      });
    } else {
      await this.httpClient.put(Constants.SERVICIOS_APLICACIONES.actualizaraplicativo,body, {headers: this.getHeaders()}).subscribe(async res_data => {
        if(res_data == true){
          await this.listarAplicativos();
          this.filaSeleccionada = "-1";
          await this.cerrarModal(modalAgregar);
        } 
      });
    }

  }

  async desactivarAplicacion(modalDesactivar){
    let codigo = this.seleccionadaAplicacion[0];
    let body = "codigo="+codigo.toString();
    await this.httpClient.put(Constants.SERVICIOS_APLICACIONES.desactivaraplicativo,body, {headers: this.getHeaders()}).subscribe(async res_data => {
      if(res_data == true){
        await this.listarAplicativos();
        await this.cerrarModal(modalDesactivar);
      } 
    });
  }


  abrirModalActualizar(modalAgregar){
    if ((this.seleccionadaAplicacion[2]).toUpperCase() == "SI") {
      this.switchPasarela = true;
    }else{
      this.switchPasarela = false;
    }
    this.codigoValue = this.seleccionadaAplicacion[0];
    this.descripcionValue = this.seleccionadaAplicacion[1];
    this.dataRButton = this.seleccionadaAplicacion[3];
    this.flag = 1;
    this.abrirModal(modalAgregar);
  }

  private getHeaders(): HttpHeaders {
    let header = new HttpHeaders();
    header = header.append('Content-Type', 'application/x-www-form-urlencoded');
    return header;
  };
 
}
