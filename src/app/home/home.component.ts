import { Component, OnInit, ɵConsole } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Constants } from '../../assets/utils/constants';
import { FormBuilder, FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { Router } from '@angular/router';
import * as moment from 'moment';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    constructor(
    private router: Router,
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private http: HttpClient,
    private fb: FormBuilder,
    ){
  }

  
  //Data Formulario
  data_visanet: string = "";
  formulario: FormGroup;
  token_data: string;
  session_data: string;
  productos_data: string;
  precio_final: number = 0;
  flagPrecioTotal: boolean = false;
  flagRespuestaVisa: boolean = false;
  data_cabecera;
  data_detalle;
  numero_pedido_f;
  precio_total_f;
  hora_pedido_f;

  ngOnInit() {
    this.mostrarResponse();
    this.crearFormulario();
    this.anadirLista();
  }

  async mostrarResponse(){
    let num_pedido = await localStorage.getItem('numero_pedido');
    console.log("num_pedido",num_pedido);
    if (num_pedido != "" && num_pedido != null) {
      this.flagRespuestaVisa = true;
      await this.httpClient.get(Constants.DATA_LOCAL.obtenerPedido+"?num_pedido_res="+num_pedido, {headers: this.getHeaders()}).subscribe(async res_data => {
        let Data_final = res_data;
        this.data_visanet = Data_final[0][8]
        console.log('DA',Data_final[0]);
        localStorage.removeItem('numero_pedido');
      });

    }
  }

  async getToken(modal){ 
  let headers = new HttpHeaders();
  headers = headers.append('Authorization', 'Basic aW50ZWdyYWNpb25lcy52aXNhbmV0QG5lY29tcGx1cy5jb206ZDVlN25rJE0=');
  headers = headers.append('Accept', `*/*`);
  this.http.post(Constants.DATA_VISA.urlApiSeguridad, null, { headers: headers, responseType: 'text' }).subscribe(async data => {
      this.token_data = data;
      await this.getSession(data,modal);
    }, error => console.log('Error2', error));
  }

  async getSession(token,modal){
    if (token != '' ) {
      let body = {
        "amount": this.precio_final,
        "antifraud": null,
        "channel": "web",
        "recurrenceMaxAmount": null
      };
      let headers = new HttpHeaders();
      headers = headers.append('Authorization', token);
      headers = headers.append('Content-Type', 'application/json');
      await this.http.post(Constants.DATA_VISA.urlApiSesion + Constants.DATA_VISA.merchantid,JSON.stringify(body),{headers: headers}).subscribe(
        async data => {
          this.session_data = data['sessionKey'];
          console.log('formularioVisa',this.formularioVisa);
          await this.insertPedido(modal);
          
        },
        error => {
          console.log('Error2',error);
        });
    }
  }

   async generarBoton() {
    let precio_fixed =  this.precio_total_f.toFixed(2);
    let scriptEl = document.createElement('script');
    console.log('this.precio_final', this.precio_final);
    scriptEl.setAttribute('src', Constants.DATA_VISA.urlJs);
    scriptEl.setAttribute('data-sessiontoken', this.session_data);
    scriptEl.setAttribute('data-merchantid', Constants.DATA_VISA.merchantid);
    scriptEl.setAttribute('data-purchasenumber', this.numero_pedido_f);
    scriptEl.setAttribute('data-channel', 'web');
    scriptEl.setAttribute('data-amount', precio_fixed);
    scriptEl.setAttribute('data-timeouturl', Constants.DATA_LOCAL.insertarDetalleVisa);
    console.log('scriptEl',scriptEl);
    document.getElementById("boton_pago").appendChild(scriptEl);
  }

  //#region Formulario
  crearFormulario() {
    this.formulario = this.fb.group({
      formularioVisa: this.fb.array([])
    });
  }

  get formularioVisa(): FormArray {
    return this.formulario.get('formularioVisa') as FormArray;
  }

  anadirLista() {
    this.flagPrecioTotal = false;
    this.flagRespuestaVisa = false;
    if (this.formularioVisa.length < 5) {
      const trabajo = this.fb.group({
        producto: new FormControl('', Validators.required),
        precio: new FormControl('0',Validators.required),
        cantidad: new FormControl('0',Validators.required),
        total: new FormControl('0',Validators.required)
      });
      this.formularioVisa.push(trabajo);
      console.log(this.formularioVisa.controls);
    }
  }

  borrarLista(indice: number) {
    this.formularioVisa.removeAt(indice);
  }

  calcular_total(item : FormControl){
    this.flagPrecioTotal = false;
    this.flagRespuestaVisa = false;
    let cantidad = parseInt(item['cantidad'].value);
    let precio = parseInt(item['precio'].value);
    item['total'].setValue(cantidad * precio);
  }

  abrirModal(modal){
    this.modalService.open(modal, { 
      size: 'xl',
      scrollable: true,
      backdrop: false
    });
  }

  async totalizar(){
    this.precio_final = 0;
    for (let index = 0; index < this.formularioVisa.controls.length ; index++) {
       this.precio_final = this.precio_final + parseInt(this.formularioVisa.controls[index].value['total']);
    };
    this.flagPrecioTotal = true;
    this.flagRespuestaVisa = true;
  }

  cerrarModal(modal){
    this.modalService.dismissAll(modal);
  }


  async cerrar_sesion(){
    await localStorage.clear();
    this.router.navigate(['/login']);
  }

  //Region Http Spring Rest
  async insertPedido(modal){
    let body = "precio_final="+this.precio_final.toString()+"&token="+this.token_data+"&usuario=Diego&host=Diego";
    await this.httpClient.post(Constants.DATA_LOCAL.insertPedido, body, {headers: this.getHeaders()}).subscribe(
      async num_pedido_res => {
        localStorage.setItem('numero_pedido', num_pedido_res.toString());
        let data_lenght = this.formularioVisa.controls.length;
        for (let i = 0; i < data_lenght; i++) {
          let producto = this.formularioVisa.controls[i]['controls'].producto.value;
          let precio = this.formularioVisa.controls[i]['controls'].precio.value;
          let cantidad = this.formularioVisa.controls[i]['controls'].cantidad.value;
          let total = this.formularioVisa.controls[i]['controls'].total.value;
          let body = "num_pedido_res="+num_pedido_res+"&producto="+producto.toString()+"&precio="+precio+"&cantidad="+cantidad+"&total="+total+"&usuario=Diego&host=Diego";
          await this.httpClient.post(Constants.DATA_LOCAL.insertDetalle, body, {headers: this.getHeaders()}).subscribe(res_data => {
            console.log("res_data",res_data);
          });
        }
        await this.abrirModal(modal);
      }
      );
    
  }

  async showModalDetalle(modaldetalle,modalTeryCon){
    await this.cerrarModal(modalTeryCon);
    let num_pedido = await localStorage.getItem('numero_pedido');
    console.log('num_pedido',num_pedido);
    await this.httpClient.get(Constants.DATA_LOCAL.obtenerPedido+"?num_pedido_res="+num_pedido, {headers: this.getHeaders()}).subscribe(async res_data => {
      this.data_cabecera = res_data;
      console.log("rest_",res_data);
      this.hora_pedido_f = moment(this.data_cabecera[0][2]).add(-5, 'hours').format('HH:mm:ss');
      this.data_cabecera[0][2] = moment(this.data_cabecera[0][2]).add(-5, 'hours').format('DD/MM/YYYY');
      this.numero_pedido_f = this.data_cabecera[0][0];
      this.precio_total_f = this.data_cabecera[0][1];
      //0 - NumPedido; 1 - Total Valor; 2 Fecha; 3 Usuario; 4 Hostname ; 5 Estado;
      await this.httpClient.get(Constants.DATA_LOCAL.obtenerPedidoDetalle+"?num_pedido_res="+num_pedido, {headers: this.getHeaders()}).subscribe(async res_data => {
        this.data_detalle = res_data;
        //0 - NumPedido_detalle; 1 - NumPedido;  2 VC_DESCRIPCION; 3 NU_PRECIO; 4 NU_CANTIDAD ; 5 NU_TOTAL, 6 DT_FECHA_REGISTRO, 7 VC_USUARIO_REGISTRO, 8 VC_HOSTNAME_REGISTRO, 9 CH_ESTADO
        await this.abrirModal(modaldetalle);
        await this.generarBoton();
      }); 
    }); 
  }


  
  async updatePedidoDetalle(){
    let num_pedido = await localStorage.getItem('numero_pedido');
    let body = "num_pedido_res="+num_pedido;
    await this.httpClient.put(Constants.DATA_LOCAL.updatePedido, body, {headers: this.getHeaders()}).subscribe(res_data => {
    //  localStorage.setItem('numero_pedido',"");
      window.location.reload();
    });
  }

  async updatePagina(){
    window.location.reload();
  }

  private getHeaders(): HttpHeaders {
    let header = new HttpHeaders();
    header = header.append('Content-Type', 'application/x-www-form-urlencoded');
    return header;
  }
}
