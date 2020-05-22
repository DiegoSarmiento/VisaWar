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

import com.spring.visa.service.EmailService;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(path = "/visanet")
public class RestData {	
	
    @Autowired
    private EmailService service;
	private EntityManager entityManager = null;
	
	@Autowired
	public void ProcedureInvoker(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@RequestMapping(value = "/pedido", method = RequestMethod.POST)
    int addPedido(@RequestParam Map<String,String> parametros) {

		float data = Float.parseFloat(parametros.get("precio_final"));
		String usuario = parametros.get("usuario");
		String host = parametros.get("host");
		String token = parametros.get("token");
		
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_I_PEDIDO");

        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_TOTAL_VALOR", float.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_USUARIO_REGISTRO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_HOSTNAME_REGISTRO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_TOKEN", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("POUT_N_RESULTADO", int.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("PIN_NU_TOTAL_VALOR", data);
        storedProcedureQuery.setParameter("PIN_VC_USUARIO_REGISTRO", usuario);
        storedProcedureQuery.setParameter("PIN_VC_HOSTNAME_REGISTRO", host);
        storedProcedureQuery.setParameter("PIN_VC_TOKEN", token);
		

        storedProcedureQuery.execute();
        
        int res = (int) storedProcedureQuery.getOutputParameterValue("POUT_N_RESULTADO");
        return res;
		
    }
	
	@RequestMapping(value = "/detalle", method = RequestMethod.POST)
	boolean addDetalle(@RequestParam Map<String,String> parametros) {
		
		int num_pedido_res = Integer.parseInt(parametros.get("num_pedido_res"));
		String producto = parametros.get("producto");
		float precio = Float.parseFloat(parametros.get("precio"));
		int cantidad = Integer.parseInt(parametros.get("cantidad"));
		float total = Float.parseFloat(parametros.get("total"));

		String usuario = parametros.get("usuario");
		String host = parametros.get("host");
		
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_I_PEDIDO_DETALLE");

        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_DESCRIPCION", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PRECIO", float.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_CANTIDAD", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_TOTAL", float.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_USUARIO_REGISTRO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_HOSTNAME_REGISTRO", String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        storedProcedureQuery.setParameter("PIN_VC_DESCRIPCION", producto);
        storedProcedureQuery.setParameter("PIN_NU_PRECIO", precio);
        storedProcedureQuery.setParameter("PIN_NU_CANTIDAD", cantidad);
        storedProcedureQuery.setParameter("PIN_NU_TOTAL", total);
        storedProcedureQuery.setParameter("PIN_VC_USUARIO_REGISTRO", usuario);
        storedProcedureQuery.setParameter("PIN_VC_HOSTNAME_REGISTRO", host);

        try {
        	storedProcedureQuery.execute();
        	return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}   
	};
	
	@RequestMapping(value = "/detallePedido", method = RequestMethod.PUT)
	boolean updatedetallePedido(@RequestParam Map<String,String> parametros) {
		int num_pedido_res = Integer.parseInt(parametros.get("num_pedido_res"));
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_D_PEDIDO");
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        try {
        	storedProcedureQuery.execute();
        	return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	};
	
	@RequestMapping(value = "/obtenerPedido", method = RequestMethod.GET)
	Object obtenerPedido(@RequestParam Map<String,String> parametros) {
		int num_pedido_res = Integer.parseInt(parametros.get("num_pedido_res"));
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_GET_DATOS_PEDIDO");
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("POUT_CUR_PEDIDO", void.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        storedProcedureQuery.execute();
        Object obj = storedProcedureQuery.getResultList();
   		return obj;
	};
	/*
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
        	return res;
		} catch (Exception e) {
			return e;
		}
	};
	*/
	@RequestMapping(value = "/obtenerdetallePedido", method = RequestMethod.GET)
	Object obtenerdetallePedido(@RequestParam Map<String,String> parametros) {
		int num_pedido_res = Integer.parseInt(parametros.get("num_pedido_res"));
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_LST_PEDIDO_DETALLE");
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("POUT_CUR_PEDIDO_DETALLE", void.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        storedProcedureQuery.execute();
        Object obj_detalle = storedProcedureQuery.getResultList();
   		return obj_detalle;
	};
	
	@RequestMapping(value = "/obtenerterycon", method = RequestMethod.GET)
	String obtenerterycon(@RequestParam Map<String,String> parametros) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_TERMINOS.SP_GET_DATOS_TERMINOS");
        storedProcedureQuery.registerStoredProcedureParameter("POUT_CUR_TERMINOS", void.class, ParameterMode.REF_CURSOR);
        try {
            storedProcedureQuery.execute();
            Object obj_detalle = storedProcedureQuery.getResultList();
            String detalle = obj_detalle.toString();
       		return detalle;
		} catch (Exception e) {
			return e.toString();
		}
	};	
	
	@RequestMapping(value = "/obtenerResVisa", method = RequestMethod.GET)
	Object obtenerResVisa(@RequestParam Map<String,String> parametros) {
		int num_pedido_res = Integer.parseInt(parametros.get("num_pedido_res"));
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_TRANSACCION.SP_GET_DATOS_TRANSACCION");
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("POUT_CUR_PEDIDO", void.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        try {
            storedProcedureQuery.execute();
            Object obj_detalle = storedProcedureQuery.getResultList();
       		return obj_detalle;
		} catch (Exception e) {
			return e;
		}
	};	
	
	@PostMapping(value = "/visaconnect")
	@ResponseBody
	public String registrarVisa(@RequestParam Map<String,String> parametros, HttpServletResponse response) throws IOException {
		
		int num_pedido_res = Integer.parseInt(parametros.get("num_pedido_res"));
		String usuario_registro = parametros.get("usuario");
		String hostname_registro = parametros.get("host");
		String email_usuario = parametros.get("customerEmail");
		if (email_usuario == "") {
			email_usuario = parametros.get("email_res");
		}
		BigDecimal total = null;
		String token_ant = "";
		String ip = "";
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_GET_DATOS_PEDIDO");
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("POUT_CUR_PEDIDO", void.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        storedProcedureQuery.execute();
        ArrayList res_d = (ArrayList) storedProcedureQuery.getResultList();
        for (Iterator<Object> it = res_d.iterator(); it.hasNext();) {
            Object[] object = (Object[]) it.next();
            total = (BigDecimal) object[1];
            ip = (String) object[4];
            token_ant = (String) object[6];
        };
        total = total.setScale(2, BigDecimal.ROUND_HALF_UP); 
		String token = parametros.get("transactionToken");
		String response_data = "";
        String jsonInputString = "{"+ 
        							"\"antifraud\": null," + 
        							" \"captureType\": \"manual\"," +
        							"\"cardHolder\": null," + 
        							"\"channel\": \"web\"," +
        							"\"countable\": true," + 
        							" \"order\": {" +
        										    "\"amount\": "+total+"," + 
        										    "\"currency\": \"PEN\"," +
        										    "\"productId\": null," + 
        										    "\"purchaseNumber\": "+num_pedido_res+"," +
        										    "\"tokenId\": \""+token+"\"" +
        										"}," +	
        	        				"\"recurrence\": null," + 
        	        				"\"terminalId\": \"1\"," +
        	        				"\"terminalUnattended\": false" + 
        						  "}";   
		URL url = new URL("https://apitestenv.vnforapps.com/api.authorization/v3/authorization/ecommerce/522591303");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization",token_ant);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        try(OutputStream os = (OutputStream) conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);           
        }catch(Exception ex) {
        	
        }
        
        try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
        		    StringBuilder response1 = new StringBuilder();
        		    String responseLine = null;
        		    while ((responseLine = br.readLine()) != null) {
        		        response1.append(responseLine.trim());
        		    }
          		    response_data = response1.toString();
        	        StoredProcedureQuery storedProcedureQuery1 = entityManager.createStoredProcedureQuery("dbuser.PKG_TRANSACCION.SP_I_TRANSACCION_TRAMA");
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_CL_TRAMA", String.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_VC_USUARIO_REGISTRO", String.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_VC_HOSTNAME_REGISTRO", String.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("POUT_N_RESULTADO", int.class, ParameterMode.OUT);

        	        storedProcedureQuery1.setParameter("PIN_CL_TRAMA", response_data);
        	        storedProcedureQuery1.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        	        storedProcedureQuery1.setParameter("PIN_VC_USUARIO_REGISTRO", usuario_registro);
        	        storedProcedureQuery1.setParameter("PIN_VC_HOSTNAME_REGISTRO", hostname_registro);

        	        try {
        	        	storedProcedureQuery1.execute();
        	    		List<Object> detalles = new ArrayList<Object>();
        	            StoredProcedureQuery storedProcedureQuery2 = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_LST_PEDIDO_DETALLE");
        	            storedProcedureQuery2.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        	            storedProcedureQuery2.registerStoredProcedureParameter("POUT_CUR_PEDIDO_DETALLE", void.class, ParameterMode.REF_CURSOR);
        	            storedProcedureQuery2.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        	            storedProcedureQuery2.execute();
        	            ArrayList res_detalle = (ArrayList) storedProcedureQuery2.getResultList();
        	            for (Iterator<Object> it = res_detalle.iterator(); it.hasNext();) {
        	                Object[] object_detalle = (Object[]) it.next();
        	                detalles.add(object_detalle);
        	            };
        	    		Map<String, Object> model = new HashMap<>();
        	    		model.put("nombre", "Nombre X");
        	    		model.put("num_pedido", num_pedido_res);
        	    		model.put("total_pedido", total);
        	    		model.put("email", email_usuario);
        	    		model.put("num_ip", ip);
        	    		model.put("num_transaccion", "Valor2");
        	    		model.put("detalles", detalles);
        	            service.sendEmail(email_usuario, "TIENDA VIRTUAL INDECOPI", "zkseven2@gmail.com", model);
        	            //response.sendRedirect("http://localhost:4200/#/");
        	        	// response.sendRedirect("http://159.89.121.195/#/");
						response.sendRedirect("https://desapache2.indecopi.gob.pe/#/");
        			} catch (Exception e) {
        	            //response.sendRedirect("http://localhost:4200/#/");
        	        	// response.sendRedirect("http://159.89.121.195/#/");
						response.sendRedirect("https://desapache2.indecopi.gob.pe/#/");
        			}
        }catch(Exception ex) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"))) {
          		    StringBuilder response1 = new StringBuilder();
          		    String responseLine = null;
          		    while ((responseLine = br.readLine()) != null) {
          		        response1.append(responseLine.trim());
          		    }
          		    response_data = response1.toString();
        	        StoredProcedureQuery storedProcedureQuery1 = entityManager.createStoredProcedureQuery("dbuser.PKG_TRANSACCION.SP_I_TRANSACCION_TRAMA");
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_CL_TRAMA", String.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_VC_USUARIO_REGISTRO", String.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_VC_HOSTNAME_REGISTRO", String.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("POUT_N_RESULTADO", int.class, ParameterMode.OUT);

        	        storedProcedureQuery1.setParameter("PIN_CL_TRAMA", response_data);
        	        storedProcedureQuery1.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        	        storedProcedureQuery1.setParameter("PIN_VC_USUARIO_REGISTRO", usuario_registro);
        	        storedProcedureQuery1.setParameter("PIN_VC_HOSTNAME_REGISTRO", hostname_registro);
        	        try {
        	        	storedProcedureQuery1.execute();
        	            //response.sendRedirect("http://localhost:4200/#/");
        	        	// response.sendRedirect("http://159.89.121.195/#/");
						response.sendRedirect("https://desapache2.indecopi.gob.pe/#/");
        			} catch (Exception e) {
        	            //response.sendRedirect("http://localhost:4200/#/");
        	        	// response.sendRedirect("http://159.89.121.195/#/");
						response.sendRedirect("https://desapache2.indecopi.gob.pe/#/");
        			}
            }catch(Exception ex2) {
            	return ex2.toString();
            }
        }
		return "Exito";
	}
	
}
