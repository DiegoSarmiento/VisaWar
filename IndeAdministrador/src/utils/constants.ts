import { environment } from '../environments/environment';

export class Constants {

    public static SERVICIOS_APLICACIONES = {
        obtenerAplicativos: environment.url +'/administracion/obteneraplicativos',
        agregarAplicacion: environment.url +'/administracion/agregaraplicativo',
        desactivaraplicativo: environment.url +'/administracion/desactivaraplicativo',
        actualizaraplicativo: environment.url +'/administracion/actualizaraplicativo',
    }

    public static SERVICIOS_CONFIGURACION = {
        obtenerconfiguraciones: environment.url +'/administracion/obtenerconfiguraciones',
        modificarterminos: environment.url +'/administracion/modificarterminos',
        modificarcorreo: environment.url +'/administracion/modificarcorreo',
        obtenercorreo: environment.url +'/administracion/obtenercorreo',
        obtenerterminos: environment.url +'/administracion/obtenerterminos',
    }

    public static SERVICIOS_EXTERNOS = {
        obtenerip : 'http://api.ipify.org/?format=json',
    }
}