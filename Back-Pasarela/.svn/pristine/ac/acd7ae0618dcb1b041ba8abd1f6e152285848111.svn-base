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
		name = "SP_I_PEDIDO_DETALLE",
        procedureName = "dbuser.PKG_PEDIDOS.SP_I_PEDIDO_DETALLE",
        parameters = {
          @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_PEDIDO", type=int.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_DESCRIPCION", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_PRECIO", type=float.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_CANTIDAD", type=int.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_TOTAL", type=float.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_USUARIO_REGISTRO", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_HOSTNAME_REGISTRO", type=String.class)
          }),
@NamedStoredProcedureQuery(
		name = "SP_LST_PEDIDO_DETALLE",
        procedureName = "dbuser.PKG_PEDIDOS.SP_LST_PEDIDO_DETALLE",
        parameters = {
          @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_NU_PEDIDO", type=long.class),
          @StoredProcedureParameter(mode=ParameterMode.OUT, name="POUT_CUR_PEDIDO_DETALLE", type=void.class),
          })
})



public class T_PEDIDO_DETALLE {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int NU_PEDIDO_DETALLE;           
	private int NU_PEDIDO;
	private String VC_DESCRIPCION;
	private float NU_PRECIO;
	private int NU_CANTIDAD;
	private float NU_TOTAL;
	private Date DT_FECHA_REGISTRO;
	private String VC_USUARIO_REGISTRO;
	private String VC_HOSTNAME_REGISTRO;
	private String CH_ESTADO;
	
	
	public T_PEDIDO_DETALLE() {
		super();
	}


	public int getNU_PEDIDO_DETALLE() {
		return NU_PEDIDO_DETALLE;
	}


	public void setNU_PEDIDO_DETALLE(int nU_PEDIDO_DETALLE) {
		NU_PEDIDO_DETALLE = nU_PEDIDO_DETALLE;
	}


	public int getNU_PEDIDO() {
		return NU_PEDIDO;
	}


	public void setNU_PEDIDO(int nU_PEDIDO) {
		NU_PEDIDO = nU_PEDIDO;
	}


	public String getVC_DESCRIPCION() {
		return VC_DESCRIPCION;
	}


	public void setVC_DESCRIPCION(String vC_DESCRIPCION) {
		VC_DESCRIPCION = vC_DESCRIPCION;
	}


	public float getNU_PRECIO() {
		return NU_PRECIO;
	}


	public void setNU_PRECIO(float nU_PRECIO) {
		NU_PRECIO = nU_PRECIO;
	}


	public int getNU_CANTIDAD() {
		return NU_CANTIDAD;
	}


	public void setNU_CANTIDAD(int nU_CANTIDAD) {
		NU_CANTIDAD = nU_CANTIDAD;
	}


	public float getNU_TOTAL() {
		return NU_TOTAL;
	}


	public void setNU_TOTAL(float nU_TOTAL) {
		NU_TOTAL = nU_TOTAL;
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
	
}
