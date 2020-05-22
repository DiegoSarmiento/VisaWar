package com.spring.visa.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.visa.seguridad.JwtUser;
import com.spring.visa.seguridad.JwtUtil;
import com.spring.visa.seguridad.Usuario;
import com.spring.visa.service.EmailService;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(path = "/")
public class RestLogin {	
	
    @Autowired
    private EmailService service;
	private EntityManager entityManager = null;
	
	 @Autowired
	 private JwtUtil jwtUtil;
	   
	@Autowired
	public void ProcedureInvoker(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
  
	
	@RequestMapping(value = "/obtenerDatosUsuario", method = RequestMethod.POST)
	Object obtenerDatosUsuario(@RequestParam Map<String,String> parametros) {
		String usuario = parametros.get("usuario");
		String password = parametros.get("password");
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_USUARIO.SP_GET_DATOS_USUARIO");
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_USUARIO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_PASSWORD", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("POUT_NU_RESULTADO", int.class, ParameterMode.OUT);
        storedProcedureQuery.setParameter("PIN_VC_USUARIO", usuario);
        storedProcedureQuery.setParameter("PIN_VC_PASSWORD", password);
        try {
        	storedProcedureQuery.execute();
        	int res = (int) storedProcedureQuery.getOutputParameterValue("POUT_NU_RESULTADO");
        	
        	if(res==1){
        		// CREACION DEL TOKEN
        		JwtUser jwtUser = new JwtUser();
            	Usuario objUsuario = new Usuario();
            	objUsuario.setUsuario(usuario);
            	//objUsuario.setClave(password);
				
            	
            	jwtUser.setUserName(usuario);
            	jwtUser.setId(Integer.MIN_VALUE);
            	jwtUser.setRole("USER");
            	String jwtToken = jwtUtil.generateToken(jwtUser);
            	return jwtToken;
        	}
        	
        	return res;
		} catch (Exception e) {
			return e;
		}
	};
	 
	
}
