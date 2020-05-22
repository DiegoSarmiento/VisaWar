package com.spring.visa.model;

import java.util.Date;

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
    		name = "SP_I_PEDIDO",
            procedureName = "dbuser.PKG_PEDIDOS.SP_I_PEDIDO",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_TOTAL_VALOR", type=float.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_USUARIO_REGISTRO", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_HOSTNAME_REGISTRO", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_TOKEN", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.OUT, name="POUT_N_RESULTADO", type=int.class)
              }),  
    @NamedStoredProcedureQuery(
    		name = "SP_D_PEDIDO",
            procedureName = "dbuser.PKG_PEDIDOS.SP_D_PEDIDO",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_PEDIDO", type=int.class)
              }),
    @NamedStoredProcedureQuery(
    		name = "SP_GET_DATOS_PEDIDO",
            procedureName = "dbuser.PKG_PEDIDOS.SP_GET_DATOS_PEDIDO",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_PEDIDO", type=int.class),
              @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, name="POUT_CUR_PEDIDO", type=void.class),
              }),
    @NamedStoredProcedureQuery(
    		name = "SP_U_PEDIDO",
            procedureName = "dbuser.PKG_PEDIDOS.SP_U_PEDIDO",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_PEDIDO", type=int.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_RESPUESTA", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_CODIGO_RESPUESTA", type=String.class),
              })
    })



public class T_PEDIDO {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int NU_PEDIDO;
    private float NU_TOTAL_VALOR;
    private Date DT_FECHA_REGISTRO;
    private String VC_USUARIO_REGISTRO;
    private String VC_HOSTNAME_REGISTRO;
	private String CH_ESTADO;
	private String VC_RESPUESTA;
	private String NU_CODIGO_RESPUESTA;
	private String VC_TOKEN;
	
	public T_PEDIDO() {
		super();
	}
	public long getNU_PEDIDO() {
		return NU_PEDIDO;
	}
	public void setNU_PEDIDO(int nU_PEDIDO) {
		NU_PEDIDO = nU_PEDIDO;
	}
	public float getNU_TOTAL_VALOR() {
		return NU_TOTAL_VALOR;
	}
	public void setNU_TOTAL_VALOR(float nU_TOTAL_VALOR) {
		NU_TOTAL_VALOR = nU_TOTAL_VALOR;
	}
	public Date getDT_FECHA_REGISTRO() {
		return DT_FECHA_REGISTRO;
	}
	public void setDT_FECHA_REGISTRO(Date dT_FECHA_REGISTRO) {
		DT_FECHA_REGISTRO = dT_FECHA_REGISTRO;
	}
	public String getVC_USUARIO_REGISTRO() {
		return VC_USUARIO_REGISTRO;
	}
	public void setVC_USUARIO_REGISTRO(String vC_USUARIO_REGISTRO) {
		VC_USUARIO_REGISTRO = vC_USUARIO_REGISTRO;
	}
	public String getVC_HOSTNAME_REGISTRO() {
		return VC_HOSTNAME_REGISTRO;
	}
	public void setVC_HOSTNAME_REGISTRO(String vC_HOSTNAME_REGISTRO) {
		VC_HOSTNAME_REGISTRO = vC_HOSTNAME_REGISTRO;
	}
	public String getCH_ESTADO() {
		return CH_ESTADO;
	}
	public void setCH_ESTADO(String cH_ESTADO) {
		CH_ESTADO = cH_ESTADO;
	}
	public String getVC_RESPUESTA() {
		return VC_RESPUESTA;
	}
	public void setVC_RESPUESTA(String vC_RESPUESTA) {
		VC_RESPUESTA = vC_RESPUESTA;
	}
	public String getNU_CODIGO_RESPUESTA() {
		return NU_CODIGO_RESPUESTA;
	}
	public void setNU_CODIGO_RESPUESTA(String nU_CODIGO_RESPUESTA) {
		NU_CODIGO_RESPUESTA = nU_CODIGO_RESPUESTA;
	}
	public String getVC_TOKEN() {
		return VC_TOKEN;
	}
	public void setVC_TOKEN(String vC_TOKEN) {
		VC_TOKEN = vC_TOKEN;
	}
	
	
}



