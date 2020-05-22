package com.spring.visa.builder;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.visa.model.T_USUARIO;

@Repository
public interface usuarioRepository extends CrudRepository<T_USUARIO, Long>{

	@Procedure(name = "SP_GET_DATOS_USUARIO")
    int SP_GET_DATOS_USUARIO(@Param("PIN_VC_USUARIO") String PIN_VC_USUARIO, 
    		                 @Param("PIN_VC_PASSWORD") String PIN_VC_PASSWORD);
	
}
