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
        insertPedido: 'http://159.89.121.195:8080/pasarelawar/visanet/pedido',
        insertDetalle: 'http://159.89.121.195:8080/pasarelawar/visanet/detalle',
        updatePedido: 'http://159.89.121.195:8080/pasarelawar/visanet/detallePedido',
        obtenerPedido: 'http://159.89.121.195:8080/pasarelawar/visanet/obtenerPedido',
        obtenerPedidoDetalle: 'http://159.89.121.195:8080/pasarelawar/visanet/obtenerdetallePedido',
        insertarDetalleVisa: 'http://159.89.121.195:8080/pasarelawar/visanet/visaconnect',
        getPedido: 'http://159.89.121.195:8080/pasarelawar/visanet/get_pedido',
        deletePedido: 'http://159.89.121.195:8080/pasarelawar/visanet/del_pedido'
    }
}