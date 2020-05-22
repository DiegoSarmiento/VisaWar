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
    		name = "SP_GET_DATOS_USUARIO",
            procedureName = "dbuser.PKG_USUARIO.SP_GET_DATOS_USUARIO",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_USUARIO", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_PASSWORD", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.OUT, name="POUT_NU_RESULTADO", type=int.class)
              }),
    @NamedStoredProcedureQuery(
    		name = "SP_U_USUARIO",
            procedureName = "dbuser.PKG_USUARIO.SP_U_USUARIO",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_USUARIO", type=String.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="PIN_VC_EMAIL", type=String.class)
              })
    })

public class T_USUARIO {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int NU_ID;
    private String VC_USUARIO;
    private String VC_PASSWORD;
    private Date DT_FECHA_REGISTRO;
    private int NU_ACTIVO;
    private String VC_EMAIL;
    
	public T_USUARIO() {
		super();
	}

	public int getNU_ID() {
		return NU_ID;
	}

	public void setNU_ID(int nU_ID) {
		NU_ID = nU_ID;
	}

	public String getVC_USUARIO() {
		return VC_USUARIO;
	}

	public void setVC_USUARIO(String vC_USUARIO) {
		VC_USUARIO = vC_USUARIO;
	}

	public String getVC_PASSWORD() {
		return VC_PASSWORD;
	}

	public void setVC_PASSWORD(String vC_PASSWORD) {
		VC_PASSWORD = vC_PASSWORD;
	}

	public Date getDT_FECHA_REGISTRO() {
		return DT_FECHA_REGISTRO;
	}

	public void setDT_FECHA_REGISTRO(Date dT_FECHA_REGISTRO) {
		DT_FECHA_REGISTRO = dT_FECHA_REGISTRO;
	}

	public int getNU_ACTIVO() {
		return NU_ACTIVO;
	}

	public void setNU_ACTIVO(int nU_ACTIVO) {
		NU_ACTIVO = nU_ACTIVO;
	}

	public String getVC_EMAIL() {
		return VC_EMAIL;
	}

	public void setVC_EMAIL(String vC_EMAIL) {
		VC_EMAIL = vC_EMAIL;
	}

}
