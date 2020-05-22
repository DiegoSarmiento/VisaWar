package com.spring.visa.builder;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.visa.model.T_TERMINOS;

@Repository
public interface terminosRepository extends CrudRepository<T_TERMINOS, Long>{
	
	@Procedure(name = "SP_GET_DATOS_TERMINOS")
    Object SP_GET_DATOS_TERMINOS();

}
