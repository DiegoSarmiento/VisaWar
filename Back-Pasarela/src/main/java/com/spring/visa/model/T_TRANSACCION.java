package com.spring.visa.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;


@Entity

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
    		name = "SP_I_TRANSACCION_TRAMA",
            procedureName = "dbuser.PKG_TRANSACCION.SP_I_TRANSACCION_TRAMA",
            parameters = {
                    @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_CL_TRAMA", type=String.class),
                    @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_PEDIDO", type=int.class),
                    @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_USUARIO_REGISTRO", type=String.class),
                    @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_HOSTNAME_REGISTRO", type=String.class),
                    @StoredProcedureParameter(mode=ParameterMode.OUT, name="POUT_N_RESULTADO", type=int.class),

              }),
    @NamedStoredProcedureQuery(
    		name = "SP_GET_DATOS_TRANSACCION",
            procedureName = "dbuser.PKG_TRANSACCION.SP_GET_DATOS_TRANSACCION",
            parameters = {
                    @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_PEDIDO", type=int.class),
                    @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, name="POUT_CUR_PEDIDO", type=void.class)
              })
    })

public class T_TRANSACCION {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int NU_ID;
    private int NU_NUMERO;
    private String VC_CODIGO;
    private String VC_BRAND;
    private String VC_UUID;
    private Date DT_UFECHA;
    private String VC_CANAL;
    private String VC_MONEDA;
    private float NU_MONTO;
    private String VC_CARD;
    private String VC_TRANSACID;
    private Date DT_TRANSAC;
    private String VC_CODE;
    private String VC_DESCRIPTION;
    private Byte[] CL_TRAMA;
    private String VC_USUARIO_REGISTRO;
    private Date DT_FECHA_REGISTRO;
    private String VC_HOSTNAME_REGISTRO;
    
    
	public T_TRANSACCION() {
		super();
	}


	public int getNU_ID() {
		return NU_ID;
	}


	public void setNU_ID(int nU_ID) {
		NU_ID = nU_ID;
	}


	public int getNU_NUMERO() {
		return NU_NUMERO;
	}


	public void setNU_NUMERO(int nU_NUMERO) {
		NU_NUMERO = nU_NUMERO;
	}


	public String getVC_CODIGO() {
		return VC_CODIGO;
	}


	public void setVC_CODIGO(String vC_CODIGO) {
		VC_CODIGO = vC_CODIGO;
	}


	public String getVC_BRAND() {
		return VC_BRAND;
	}


	public void setVC_BRAND(String vC_BRAND) {
		VC_BRAND = vC_BRAND;
	}


	public String getVC_UUID() {
		return VC_UUID;
	}


	public void setVC_UUID(String vC_UUID) {
		VC_UUID = vC_UUID;
	}


	public Date getDT_UFECHA() {
		return DT_UFECHA;
	}


	public void setDT_UFECHA(Date dT_UFECHA) {
		DT_UFECHA = dT_UFECHA;
	}


	public String getVC_CANAL() {
		return VC_CANAL;
	}


	public void setVC_CANAL(String vC_CANAL) {
		VC_CANAL = vC_CANAL;
	}


	public String getVC_MONEDA() {
		return VC_MONEDA;
	}


	public void setVC_MONEDA(String vC_MONEDA) {
		VC_MONEDA = vC_MONEDA;
	}


	public float getNU_MONTO() {
		return NU_MONTO;
	}


	public void setNU_MONTO(float nU_MONTO) {
		NU_MONTO = nU_MONTO;
	}


	public String getVC_CARD() {
		return VC_CARD;
	}


	public void setVC_CARD(String vC_CARD) {
		VC_CARD = vC_CARD;
	}


	public String getVC_TRANSACID() {
		return VC_TRANSACID;
	}


	public void setVC_TRANSACID(String vC_TRANSACID) {
		VC_TRANSACID = vC_TRANSACID;
	}


	public Date getDT_TRANSAC() {
		return DT_TRANSAC;
	}


	public void setDT_TRANSAC(Date dT_TRANSAC) {
		DT_TRANSAC = dT_TRANSAC;
	}


	public String getVC_CODE() {
		return VC_CODE;
	}


	public void setVC_CODE(String vC_CODE) {
		VC_CODE = vC_CODE;
	}


	public String getVC_DESCRIPTION() {
		return VC_DESCRIPTION;
	}


	public void setVC_DESCRIPTION(String vC_DESCRIPTION) {
		VC_DESCRIPTION = vC_DESCRIPTION;
	}


	public Byte[] getCL_TRAMA() {
		return CL_TRAMA;
	}


	public void setCL_TRAMA(Byte[] cL_TRAMA) {
		CL_TRAMA = cL_TRAMA;
	}


	public String getVC_USUARIO_REGISTRO() {
		return VC_USUARIO_REGISTRO;
	}


	public void setVC_USUARIO_REGISTRO(String vC_USUARIO_REGISTRO) {
		VC_USUARIO_REGISTRO = vC_USUARIO_REGISTRO;
	}


	public Date getDT_FECHA_REGISTRO() {
		return DT_FECHA_REGISTRO;
	}


	public void setDT_FECHA_REGISTRO(Date dT_FECHA_REGISTRO) {
		DT_FECHA_REGISTRO = dT_FECHA_REGISTRO;
	}


	public String getVC_HOSTNAME_REGISTRO() {
		return VC_HOSTNAME_REGISTRO;
	}


	public void setVC_HOSTNAME_REGISTRO(String vC_HOSTNAME_REGISTRO) {
		VC_HOSTNAME_REGISTRO = vC_HOSTNAME_REGISTRO;
	}

}
