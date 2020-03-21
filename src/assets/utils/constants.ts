export class Constants {

    public static DATA_VISA = {
        merchantid : "522591303",
        user : "integraciones.visanet@necomplus.com",
        password : "d5e7nk$M",
        urlApiSeguridad : "https://apitestenv.vnforapps.com/api.security/v1/security",
        urlApiSesion : "https://apitestenv.vnforapps.com/api.ecommerce/v2/ecommerce/token/session/",
        urlJs : "https://static-content-qas.vnforapps.com/v2/js/checkout.js?qa=true"
    };

    public static DATA_LOCAL = {
        insertPedido: 'http://desapache2.indecopi.gob.pe/pasarela/visanet/pedido',
        insertDetalle: 'http://desapache2.indecopi.gob.pe/pasarela/visanet/detalle',
        updatePedido: 'http://desapache2.indecopi.gob.pe/pasarela/visanet/detallePedido',
        obtenerPedido: 'http://desapache2.indecopi.gob.pe/pasarela/visanet/obtenerPedido',
        obtenerPedidoDetalle: 'http://desapache2.indecopi.gob.pe/pasarela/visanet/obtenerdetallePedido',
        insertarDetalleVisa: 'http://desapache2.indecopi.gob.pe/pasarela/visanet/visaconnect',
        getPedido: 'http://desapache2.indecopi.gob.pe/pasarela/visanet/get_pedido',
        deletePedido: 'http://desapache2.indecopi.gob.pe/pasarela/visanet/del_pedido'
    }
}