package com.spring.visa.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import oracle.jdbc.OracleTypes;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/visanet")
public class RestData {	
	
	
	private EntityManager entityManager = null;
	
	@Autowired
	public void ProcedureInvoker(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@RequestMapping(value = "/pedido", method = RequestMethod.POST)
    int addPedido(@RequestParam Map<String,String> parametros) {
    	//pedidoRepository.save(user);
		int data = Integer.parseInt(parametros.get("precio_final"));
		String usuario = parametros.get("usuario");
		String host = parametros.get("host");
		String token = parametros.get("token");
		
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_I_PEDIDO");
        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_TOTAL_VALOR", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_USUARIO_REGISTRO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_HOSTNAME_REGISTRO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_TOKEN", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("POUT_N_RESULTADO", int.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        storedProcedureQuery.setParameter("PIN_NU_TOTAL_VALOR", data);
        storedProcedureQuery.setParameter("PIN_VC_USUARIO_REGISTRO", usuario);
        storedProcedureQuery.setParameter("PIN_VC_HOSTNAME_REGISTRO", host);
        storedProcedureQuery.setParameter("PIN_VC_TOKEN", token);


        // Realizamos la llamada al procedimiento
        storedProcedureQuery.execute();
        
        // Obtenemos los valores de salida
        int res = (int) storedProcedureQuery.getOutputParameterValue("POUT_N_RESULTADO");
        return res;
		
    }
	
	
	@RequestMapping(value = "/detalle", method = RequestMethod.POST)
	boolean addDetalle(@RequestParam Map<String,String> parametros) {
		
		//DataDetalle
		int num_pedido_res = Integer.parseInt(parametros.get("num_pedido_res"));
		String producto = parametros.get("producto");
		int precio = Integer.parseInt(parametros.get("precio"));
		int cantidad = Integer.parseInt(parametros.get("cantidad"));
		int total = Integer.parseInt(parametros.get("total"));

		//DataUsuario
		String usuario = parametros.get("usuario");
		String host = parametros.get("host");
		
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_I_PEDIDO_DETALLE");

        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_DESCRIPCION", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PRECIO", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_CANTIDAD", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_TOTAL", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_USUARIO_REGISTRO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("PIN_VC_HOSTNAME_REGISTRO", String.class, ParameterMode.IN);

        // Configuramos el valor de entrada
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
	
	
	
	
	@PostMapping(value = "/visaconnect")
	@ResponseBody
	public String registrarVisa(@RequestParam Map<String,String> parametros, HttpServletResponse response) throws IOException {
		
		int num_pedido_res = Integer.parseInt(parametros.get("num_pedido_res"));
		BigDecimal total = null;
		String token_ant = "";
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_GET_DATOS_PEDIDO");
        storedProcedureQuery.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("POUT_CUR_PEDIDO", void.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        storedProcedureQuery.execute();
        ArrayList res_d = (ArrayList) storedProcedureQuery.getResultList();
        for (Iterator<Object> it = res_d.iterator(); it.hasNext();) {
            Object[] object = (Object[]) it.next();
            total = (BigDecimal) object[1];
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
        	        StoredProcedureQuery storedProcedureQuery1 = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_U_PEDIDO");
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_NU_PEDIDO", String.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_VC_RESPUESTA", String.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_NU_CODIGO_RESPUESTA", String.class, ParameterMode.IN);

        	        storedProcedureQuery1.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        	        storedProcedureQuery1.setParameter("PIN_VC_RESPUESTA", response_data);
        	        storedProcedureQuery1.setParameter("PIN_NU_CODIGO_RESPUESTA", "200");

        	        try {
        	        	storedProcedureQuery1.execute();
        	        	response.sendRedirect("http://desapache1.indecopi.gob.pe/home");
        			} catch (Exception e) {
        				response.sendRedirect("http://desapache1.indecopi.gob.pe/home");
        			}
        }catch(Exception ex) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"))) {
          		    StringBuilder response1 = new StringBuilder();
          		    String responseLine = null;
          		    while ((responseLine = br.readLine()) != null) {
          		        response1.append(responseLine.trim());
          		    }
          		    response_data = response1.toString();
          		    JSONObject obj = new JSONObject(response_data);
          		    String code = String.valueOf(obj.getInt("errorCode"));
        	        StoredProcedureQuery storedProcedureQuery1 = entityManager.createStoredProcedureQuery("dbuser.PKG_PEDIDOS.SP_U_PEDIDO");
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_NU_PEDIDO", int.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_VC_RESPUESTA", String.class, ParameterMode.IN);
        	        storedProcedureQuery1.registerStoredProcedureParameter("PIN_NU_CODIGO_RESPUESTA", String.class, ParameterMode.IN);

        	        storedProcedureQuery1.setParameter("PIN_NU_PEDIDO", num_pedido_res);
        	        storedProcedureQuery1.setParameter("PIN_VC_RESPUESTA", response_data);
        	        storedProcedureQuery1.setParameter("PIN_NU_CODIGO_RESPUESTA", code);
        	        
        	        try {
        	        	storedProcedureQuery1.execute();
        	        	response.sendRedirect("http://desapache1.indecopi.gob.pe/home");  
        			} catch (Exception e) {
        				response.sendRedirect("http://desapache1.indecopi.gob.pe/home"); 
        			}
            }catch(Exception ex2) {
            	return "Ocurrio un Error Inesperado";
            }
        }
		return "Exito";
	}
	
}
