package com.spring.visa.builder;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.visa.model.T_TRANSACCION;

@Repository
public interface transaccionRepository extends CrudRepository<T_TRANSACCION, Long>{

	@Procedure(name = "SP_I_TRANSACCION_TRAMA")
    int SP_I_TRANSACCION_TRAMA(@Param("PIN_VC_USUARIO") String PIN_VC_USUARIO, 
    		                   @Param("PIN_NU_PEDIDO") int PIN_NU_PEDIDO,
    		                   @Param("PIN_VC_USUARIO_REGISTRO") String PIN_VC_USUARIO_REGISTRO,
    		                   @Param("PIN_VC_HOSTNAME_REGISTRO") String PIN_VC_HOSTNAME_REGISTRO);
	
	@Procedure(name = "SP_GET_DATOS_TRANSACCION")
    int SP_GET_DATOS_TRANSACCION(@Param("PIN_NU_PEDIDO") int PIN_VC_USUARIO);
}
