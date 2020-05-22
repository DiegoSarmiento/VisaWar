package com.spring.visa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.util.Date;

@Entity

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
    		name = "SP_GET_DATOS_TERMINOS",
            procedureName = "dbuser.PKG_TERMINOS.SP_GET_DATOS_TERMINOS",
            parameters = {
                    @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, name="POUT_CUR_TERMINOS", type=void.class),
              })
    })


public class T_TERMINOS {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int NU_ID;
	
	@Lob
	private byte[] CL_TERMINO;
	private String VC_USUARIO_REGISTRO;
	private String VC_USUARIO_MODIFICACION;
	private Date DT_FECHA_REGISTRO;
	private Date DT_FECHA_MODIFICACION;
	private String VC_HOSTNAME_REGISTRO;
	private String VC_HOSTNAME_MODIFICACION;
	
	public T_TERMINOS() {
		super();
	}
	public int getNU_ID() {
		return NU_ID;
	}
	public void setNU_ID(int nU_ID) {
		NU_ID = nU_ID;
	}
	public byte[] getCL_TERMINO() {
		return CL_TERMINO;
	}
	public void setCL_TERMINO(byte[] cL_TERMINO) {
		CL_TERMINO = cL_TERMINO;
	}
	public String getVC_USUARIO_REGISTRO() {
		return VC_USUARIO_REGISTRO;
	}
	public void setVC_USUARIO_REGISTRO(String vC_USUARIO_REGISTRO) {
		VC_USUARIO_REGISTRO = vC_USUARIO_REGISTRO;
	}
	public String getVC_USUARIO_MODIFICACION() {
		return VC_USUARIO_MODIFICACION;
	}
	public void setVC_USUARIO_MODIFICACION(String vC_USUARIO_MODIFICACION) {
		VC_USUARIO_MODIFICACION = vC_USUARIO_MODIFICACION;
	}
	public Date getDT_FECHA_REGISTRO() {
		return DT_FECHA_REGISTRO;
	}
	public void setDT_FECHA_REGISTRO(Date dT_FECHA_REGISTRO) {
		DT_FECHA_REGISTRO = dT_FECHA_REGISTRO;
	}
	public Date getDT_FECHA_MODIFICACION() {
		return DT_FECHA_MODIFICACION;
	}
	public void setDT_FECHA_MODIFICACION(Date dT_FECHA_MODIFICACION) {
		DT_FECHA_MODIFICACION = dT_FECHA_MODIFICACION;
	}
	public String getVC_HOSTNAME_REGISTRO() {
		return VC_HOSTNAME_REGISTRO;
	}
	public void setVC_HOSTNAME_REGISTRO(String vC_HOSTNAME_REGISTRO) {
		VC_HOSTNAME_REGISTRO = vC_HOSTNAME_REGISTRO;
	}
	public String getVC_HOSTNAME_MODIFICACION() {
		return VC_HOSTNAME_MODIFICACION;
	}
	public void setVC_HOSTNAME_MODIFICACION(String vC_HOSTNAME_MODIFICACION) {
		VC_HOSTNAME_MODIFICACION = vC_HOSTNAME_MODIFICACION;
	}
	
	
	
}
