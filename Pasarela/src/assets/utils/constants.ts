import { environment } from '../../environments/environment';

export class Constants {

    public static DATA_VISA = {
        merchantid : "522591303",
        user : "integraciones.visanet@necomplus.com",
        password : "d5e7nk$M",
        antifraud : null,
        channel : "web",
        recurrenceMaxAmount : null,
        colorBoton: "#9e004f",
        urlApiSeguridad : "https://apitestenv.vnforapps.com/api.security/v1/security",
        urlApiSesion : "https://apitestenv.vnforapps.com/api.ecommerce/v2/ecommerce/token/session/",
        urlJs : "https://static-content-qas.vnforapps.com/v2/js/checkout.js?qa=true",
        urlImagen: "https://www.indecopi.gob.pe/image/layout_set_logo?img_id=3224595&t=1585113168475",
    };

    public static DATA_LOCAL = {
        insertPedido: environment.url +'/visanet/pedido',
        insertDetalle: environment.url + '/visanet/detalle',
        updatePedido: environment.url + '/visanet/detallePedido',
        obtenerPedido: environment.url + '/visanet/obtenerPedido',
        obtenerPedidoDetalle: environment.url + '/visanet/obtenerdetallePedido',
        insertarDetalleVisa: environment.url + '/visanet/visaconnect',
        getPedido: environment.url + '/visanet/get_pedido',
        deletePedido: environment.url + '/visanet/del_pedido',
        loginUsuario: environment.url + '/obtenerDatosUsuario',
        terminosycondiciones: environment.url + '/visanet/obtenerterycon',
        obtenerResVisa: environment.url + '/visanet/obtenerResVisa'
    }

    public static DATA_EXTERNA = {
        codigoApp: "XC001",
        url: environment.url_limpio + '/administracion/obteneraplicativo',
        obtenerip  : 'http://api.ipify.org/?format=json',
    }
}