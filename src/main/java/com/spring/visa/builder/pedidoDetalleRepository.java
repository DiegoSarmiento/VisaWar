package com.spring.visa.builder;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.visa.model.T_PEDIDO_DETALLE;;

@Repository
public interface pedidoDetalleRepository extends CrudRepository<T_PEDIDO_DETALLE, Long>{

	@Procedure(name = "SP_I_PEDIDO_DETALLE")
    String SP_I_PEDIDO_DETALLE(@Param("PIN_NU_PEDIDO") int PIN_NU_PEDIDO, 
    		             @Param("PIN_VC_DESCRIPCION") String PIN_VC_DESCRIPCION,
    		             @Param("PIN_NU_PRECIO") int PIN_NU_PRECIO,
    		             @Param("PIN_NU_CANTIDAD") int PIN_NU_CANTIDAD,
    		             @Param("PIN_NU_TOTAL") int PIN_NU_TOTAL,
    		             @Param("PIN_VC_USUARIO_REGISTRO") String PIN_VC_USUARIO_REGISTRO,
    		             @Param("PIN_VC_HOSTNAME_REGISTRO") String PIN_VC_HOSTNAME_REGISTRO);
	
	@Procedure(name = "SP_GET_DATOS_PEDIDO")
    String SP_GET_DATOS_PEDIDO(@Param("PIN_NU_PEDIDO") int PIN_NU_PEDIDO);
	
	@Procedure(name = "SP_LST_PEDIDO_DETALLE")
    String SP_LST_PEDIDO_DETALLE(@Param("PIN_NU_PEDIDO") int PIN_NU_PEDIDO);
	

	
	
}
