package com.pagoefectivo.web.app.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.pagoefectivo.web.app.models.AutorizacionCuerpoModel;
import com.pagoefectivo.web.app.models.AutorizacionTokenModel;
import com.pagoefectivo.web.app.models.ConfiguracionModel;
import com.pagoefectivo.web.app.models.DataSignatureModel;
import com.pagoefectivo.web.app.models.GeneraModel;
import com.pagoefectivo.web.app.models.GeneracionCIPModel;
import com.pagoefectivo.web.app.models.ResponseModel;
import com.pagoefectivo.web.app.models.SignatureModel;
import com.pagoefectivo.web.app.utils.FilesUtil;

@Controller
public class RestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/genera")
	public String generaGet(Model model) {
		ConfiguracionModel configuracion = new ConfiguracionModel();
		String path = env.getProperty("route.data");
		FilesUtil util = new FilesUtil();
		Gson gson = new Gson();
		String contenido = "";
		try {
			contenido = util.LeerArchivo(path+"configura.json");
			configuracion = gson.fromJson(contenido, ConfiguracionModel.class);
			model.addAttribute("configuracion", configuracion);
			model.addAttribute("enlaceCIP", "");
			model.addAttribute("post", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "genera";
	}
	
	@PostMapping("/genera")
	public String generaPost(HttpServletResponse response, Model model) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
		String path = env.getProperty("route.data");
		FilesUtil util = new FilesUtil();
		ConfiguracionModel configuracion = new ConfiguracionModel();
		SignatureModel signatureCuerpo = new SignatureModel();
		DataSignatureModel dataSignatureCuerpo = new DataSignatureModel();
		Date fechaHora = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		Gson gson = new Gson();
		Calendar calendar = Calendar.getInstance();
		AutorizacionCuerpoModel autorizacionCM = new AutorizacionCuerpoModel();
		AutorizacionTokenModel cuerpoToken = new AutorizacionTokenModel();
		SecretKeySpec secretKeySpec = new SecretKeySpec("0".getBytes(StandardCharsets.UTF_8), "HmacSHA256");
		GeneraModel generaModel = new GeneraModel();
		df.setTimeZone(TimeZone.getTimeZone("America/Lima"));

		try {
			String contenido = util.LeerArchivo(path+"configura.json");
			configuracion = gson.fromJson(contenido, ConfiguracionModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String autorizacionRuta = configuracion.getServidorPagoEfectivo() + "authorizations";
		
		//Concatenar 'cips' a ruta de servidor
		generaModel.setCIPRuta(configuracion.getServidorPagoEfectivo() + "cips");
		
		generaModel.setFechaAutorizacion(df.format(fechaHora));
		autorizacionCM.setAccessKey(configuracion.getAccessKey());
		autorizacionCM.setIdService(configuracion.getIDComercio());
		autorizacionCM.setDateRequest(generaModel.getFechaAutorizacion());
		autorizacionCM.setHashString(generaModel.getAutorizacionHash());

		String AccessKey = configuracion.getAccessKey();
		String SecretKey = configuracion.getSecretKey();
		String ID = configuracion.getIDComercio();
		String fechaAutorizacion = generaModel.getFechaAutorizacion();
		
		generaModel.setAutorizacionData(ID+"."+AccessKey+"."+SecretKey+"."+fechaAutorizacion);
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(generaModel.getAutorizacionData().getBytes(StandardCharsets.UTF_8));
			autorizacionCM.setHashString(String.format("%064x", new BigInteger(1, digest.digest())));
			generaModel.setAutorizacionHash(String.format("%064x", new BigInteger(1, digest.digest())));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String jsonAutorizacion = gson.toJson(autorizacionCM);
    	util.GuardarArchivo(path + "autorizaPeticion.json", jsonAutorizacion);
    	
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>(jsonAutorizacion,headers);
	    try {
	    	ResponseModel result = restTemplate.exchange(autorizacionRuta, HttpMethod.POST, entity, ResponseModel.class).getBody();
	    	String resultadoHttp = gson.toJson(result);
	    	util.GuardarArchivo(path + "autorizaRespuesta.json", resultadoHttp);
			calendar.setTime(fechaHora); // Configuramos la fecha que se recibe
			calendar.add(Calendar.HOUR, Integer.parseInt(configuracion.getTiempoExpiracionPago())); // numero de horas a añadir, o restar en caso de horas<0
			fechaHora = calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
			generaModel.setFechaExpiracion(df.format(fechaHora));
				
			// Generar numero de transaccion Random para casos de prueba
			Random rand = new Random();
			int maxRandom = 999999;
			int transaccionRandom = rand.nextInt(maxRandom);
				
			cuerpoToken.setCurrency(configuracion.getTipoMoneda());
			cuerpoToken.setAmount(configuracion.getMonto());
			cuerpoToken.setTransactionCode(transaccionRandom);
			cuerpoToken.setDateExpiry(generaModel.getFechaExpiracion());
			cuerpoToken.setPaymentConcept("Prueba Java- Validar");
			cuerpoToken.setAdditionalData("Prueba Java datos adicionales");
			cuerpoToken.setAdminEmail(configuracion.getEmailComercio());
			cuerpoToken.setUserEmail(configuracion.getEmailComercio());
			cuerpoToken.setUserId("001");
			cuerpoToken.setUserName("Juan Alejandro");
			cuerpoToken.setUserLastName("Lam");
			cuerpoToken.setUserUbigeo(150115);
			cuerpoToken.setUserCountry("PERU");
			cuerpoToken.setUserDocumentType("DNI");
			cuerpoToken.setUserDocumentNumber(12345678);
			cuerpoToken.setUserPhone(975810287);
			cuerpoToken.setUserCodeCountry("+51");
			cuerpoToken.setServiceId(configuracion.getIDComercio());
			
			String cuerpoAutorizacion = gson.toJson(cuerpoToken);
		    util.GuardarArchivo(path + "cipsPeticion.json", cuerpoAutorizacion);
		    String ruta = configuracion.getServidorPagoEfectivo() + "cips";
		    
			//Petición al Servicio Genera
			HttpHeaders headersGenera = new HttpHeaders();
			headersGenera.setContentType(MediaType.APPLICATION_JSON);
			headersGenera.setAcceptLanguage(Locale.LanguageRange.parse("es-PE"));
			headersGenera.setOrigin("web");
			headersGenera.setBearerAuth(result.getData().getToken());
			HttpEntity<String> entity_cip = new HttpEntity<String>(cuerpoAutorizacion,headersGenera);
		    GeneracionCIPModel respuestaCIP = restTemplate.exchange(ruta, HttpMethod.POST, entity_cip, GeneracionCIPModel.class).getBody();
			
			String respuestaCIP_json = gson.toJson(respuestaCIP);
			util.GuardarArchivo(path + "cipsRespuesta.json", respuestaCIP_json);
			
			signatureCuerpo.setEventType("cip.paid");
			signatureCuerpo.setOperationNumber("12345");
			dataSignatureCuerpo.setCip(respuestaCIP.getData().getCip());
			dataSignatureCuerpo.setCurrency(respuestaCIP.getData().getCurrency());
			dataSignatureCuerpo.setAmount(respuestaCIP.getData().getAmount());
			signatureCuerpo.setData(dataSignatureCuerpo);
			
			String requestBody = gson.toJson(signatureCuerpo);
			
			byte[] hmacSha256 = "0".getBytes();
			Mac mac = Mac.getInstance("HmacSHA256");
			secretKeySpec = new SecretKeySpec(SecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
			mac.init(secretKeySpec);
			hmacSha256 = mac.doFinal(requestBody.getBytes(StandardCharsets.UTF_8));

			String PESignatureGenerado = String.format("%064x", new BigInteger(1, hmacSha256));

			String Data = "PE-Signature = \"" + PESignatureGenerado + "\"" + "\r\n" + "requestBody = \"" + requestBody + "\"";
			util.GuardarArchivo(path + "notificacion.json", Data);
			
			if ((configuracion.getModoIntegracion()).equals("RED")) {
				response.sendRedirect(respuestaCIP.getData().getCipUrl());
			} else {
				model.addAttribute("configuracion", configuracion);
				model.addAttribute("enlaceCIP", respuestaCIP.getData().getCipUrl());
				model.addAttribute("post", 1);
				return "genera";
			}
		} catch (HttpStatusCodeException exception) {
			util.SobreescribirArchivo(path + "error.json", "Error: "+ fechaHora + " " + exception.toString() + "\r\n");
			String accionMensajeIncorrecto = "Hubo un error en la consulta";
			model.addAttribute("accionMensajeIncorrecto", accionMensajeIncorrecto);
			model.addAttribute("configuracion", configuracion);
			model.addAttribute("enlaceCIP", "");
			model.addAttribute("post", 0);
			return "genera";
		}
		return null;
	}
	
	@GetMapping({"/configura", "/index", "/"})
	public String configura(Model model, ConfiguracionModel configuracion) throws IOException {
		ConfiguracionModel configuracion_get = new ConfiguracionModel();
		FilesUtil util = new FilesUtil();
		String path = env.getProperty("route.data");
		Gson gson = new Gson();
		Calendar calendar = Calendar.getInstance();
		String horaActual = calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) + calendar.get(Calendar.SECOND) + "";
		try {
			String contenido = util.LeerArchivo(path + "configura.json");
			if (contenido.isEmpty()) {
				model.addAttribute("configuracion", configuracion_get);
			} else {
				configuracion_get = gson.fromJson(contenido, ConfiguracionModel.class);
				model.addAttribute("configuracion", configuracion_get);
			}
		} catch (IOException e) {
			util.SobreescribirArchivo(path + "error.json", horaActual + e.toString() + "\r\n");
		}
		return "configura";
	}
	
	@PostMapping("/configura")
	public String configuraPost(@RequestParam Map<String,String> request, Model model) {
		
		String path = env.getProperty("route.data");
		ConfiguracionModel configuracion = new ConfiguracionModel();
		FilesUtil util = new FilesUtil();
		Gson gson = new Gson();	
		configuracion.setServidorPagoEfectivo(request.get("ServidorPagoEfectivo"));
		configuracion.setAccessKey(request.get("AccessKey"));
		configuracion.setSecretKey(request.get("SecretKey"));
		configuracion.setIDComercio(request.get("IDComercio"));
		configuracion.setNombreComercio(request.get("NombreComercio"));
		configuracion.setEmailComercio(request.get("EmailComercio"));
		configuracion.setModoIntegracion(request.get("ModoIntegracion"));
		configuracion.setTiempoExpiracionPago(request.get("TiempoExpiracionPago"));
		configuracion.setPais(request.get("Pais"));
		configuracion.setTipoMoneda(request.get("TipoMoneda"));
		configuracion.setMonto(request.get("Monto"));
		String contenido = gson.toJson(configuracion);
		try {
			boolean resultadoGrabacion = util.GuardarArchivo(path + "configura.json", contenido);
			if (resultadoGrabacion) {
				String accionMensajeCorrecto = "La configuración se guardó satisfactoriamente";
				model.addAttribute("accionMensajeCorrecto", accionMensajeCorrecto);
				model.addAttribute("configuracion", configuracion);
			}else {
				String accionMensajeIncorrecto = "La configuración no se guardó";
				model.addAttribute("accionMensajeCorrecto", accionMensajeIncorrecto);
				ConfiguracionModel configuracion_empty = new ConfiguracionModel();
				model.addAttribute("configuracion", configuracion_empty);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "configura";
	}
	
	@GetMapping("/notifica")
	public String notifica(Model model) {
		
		SignatureModel signatureModel = new SignatureModel();
		DataSignatureModel dataSignatureModel = new DataSignatureModel();
		signatureModel.setData(dataSignatureModel);
		signatureModel.setEventType("cip.paid");
		Gson gson = new Gson();
		String requestBody = gson.toJson(signatureModel);
		model.addAttribute("PESignatureRecibido", "");
		model.addAttribute("requestBody", requestBody);

		return "notifica";
	}
	
	@PostMapping("/notifica")
	public String notificaPost(@RequestParam Map<String,String> request, Model model) {
		
		String PESignatureRecibido = request.get("PE-Signature");
		String requestBody = request.get("requestBody");
		String path = env.getProperty("route.data");
		
		ConfiguracionModel configuracion = new ConfiguracionModel();
		FilesUtil util = new FilesUtil();
		Gson gson = new Gson();
		SecretKeySpec secretKeySpec = new SecretKeySpec("0".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

		String contenido = null;
		String PESignatureGenerado = null;
		
		model.addAttribute("PESignatureRecibido", PESignatureRecibido);
		model.addAttribute("requestBody", requestBody);
		
		try {
			
			contenido = util.LeerArchivo(path + "configura.json");
			configuracion = gson.fromJson(contenido, ConfiguracionModel.class);
			String secretKey = configuracion.getSecretKey();
			secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(secretKeySpec);
			
			byte[] hmacSha256 = "0".getBytes();
			hmacSha256 = mac.doFinal(requestBody.getBytes(StandardCharsets.UTF_8));
			
			PESignatureGenerado = String.format("%064x", new BigInteger(1, hmacSha256));

		} catch (NoSuchAlgorithmException | InvalidKeyException | IOException e) {
			String accionMensajeIncorrecto = "Hubo un error en el PE Signature";
			model.addAttribute("accionMensajeIncorrecto", accionMensajeIncorrecto);
			return "notifica";
		};
	

		if(PESignatureRecibido.equals(PESignatureGenerado)) {
			String accionMensajeCorrecto = "El PE-Signature es correcto";
			model.addAttribute("accionMensajeCorrecto", accionMensajeCorrecto);
		}else {
			String accionMensajeIncorrecto = "El PE-Signature es incorrecto";
			model.addAttribute("accionMensajeIncorrecto", accionMensajeIncorrecto);
		}
		return "notifica";
	}	
	
}
