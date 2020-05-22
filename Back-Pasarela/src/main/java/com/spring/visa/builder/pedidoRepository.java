package com.spring.visa.builder;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.visa.model.T_PEDIDO;

@Repository
public interface pedidoRepository extends CrudRepository<T_PEDIDO, Long>{

	@Procedure(name = "SP_I_PEDIDO")
    String   SP_I_PEDIDO(@Param("PIN_NU_TOTAL_VALOR") float PIN_NU_TOTAL_VALOR, 
    		             @Param("PIN_VC_USUARIO_REGISTRO") String PIN_VC_USUARIO_REGISTRO,
    		             @Param("PIN_VC_HOSTNAME_REGISTRO") String PIN_VC_HOSTNAME_REGISTRO,
    		             @Param("PIN_VC_TOKEN") String PIN_VC_TOKEN);
	
	@Procedure(name = "SP_D_PEDIDO")
    String   SP_D_PEDIDO(@Param("PIN_NU_PEDIDO") int PIN_NU_PEDIDO);
	
	@Procedure(name = "SP_GET_DATOS_PEDIDO")
	String   SP_GET_DATOS_PEDIDO(@Param("PIN_NU_PEDIDO") int PIN_NU_PEDIDO);
	
	@Procedure(name = "SP_U_PEDIDO")
    String SP_U_PEDIDO(@Param("PIN_NU_PEDIDO") int PIN_NU_PEDIDO,
    				   @Param("PIN_VC_RESPUESTA") String PIN_VC_RESPUESTA,
    				   @Param("PIN_NU_CODIGO_RESPUESTA") String PIN_NU_CODIGO_RESPUESTA);
}
