package com.pagoefectivo.web.app.models;

public class ConfiguracionModel {
	private String ServidorPagoEfectivo;
	private String AccessKey;
	private String SecretKey;
	private String IDComercio;
	private String NombreComercio;
	private String EmailComercio;
	private String ModoIntegracion;
	private String TiempoExpiracionPago;
	private String Pais;
	private String TipoMoneda;
	private String Monto;

	public ConfiguracionModel() {
		ServidorPagoEfectivo = "";
		AccessKey = "";
		SecretKey = "";
		IDComercio = "";
		NombreComercio = "";
		EmailComercio = "";
		ModoIntegracion = "";
		TiempoExpiracionPago = "";
		Pais = "";
		TipoMoneda = "";
		Monto = "";
	}

	public String getServidorPagoEfectivo() {
		return ServidorPagoEfectivo;
	}

	public void setServidorPagoEfectivo(String servidorPagoEfectivo) {
		ServidorPagoEfectivo = servidorPagoEfectivo;
	}

	public String getAccessKey() {
		return AccessKey;
	}

	public void setAccessKey(String accessKey) {
		AccessKey = accessKey;
	}

	public String getSecretKey() {
		return SecretKey;
	}

	public void setSecretKey(String secretKey) {
		SecretKey = secretKey;
	}

	public String getIDComercio() {
		return IDComercio;
	}

	public void setIDComercio(String iDComercio) {
		IDComercio = iDComercio;
	}

	public String getNombreComercio() {
		return NombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		NombreComercio = nombreComercio;
	}

	public String getEmailComercio() {
		return EmailComercio;
	}

	public void setEmailComercio(String emailComercio) {
		EmailComercio = emailComercio;
	}

	public String getModoIntegracion() {
		return ModoIntegracion;
	}

	public void setModoIntegracion(String modoIntegracion) {
		ModoIntegracion = modoIntegracion;
	}

	public String getTiempoExpiracionPago() {
		return TiempoExpiracionPago;
	}

	public void setTiempoExpiracionPago(String tiempoExpiracionPago) {
		TiempoExpiracionPago = tiempoExpiracionPago;
	}

	public String getPais() {
		return Pais;
	}

	public void setPais(String pais) {
		Pais = pais;
	}

	public String getTipoMoneda() {
		return TipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		TipoMoneda = tipoMoneda;
	}

	public String getMonto() {
		return Monto;
	}

	public void setMonto(String monto) {
		Monto = monto;
	}

}
