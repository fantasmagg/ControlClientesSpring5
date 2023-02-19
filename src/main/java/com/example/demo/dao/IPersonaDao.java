package com.example.demo.dao;

import com.example.demo.domain.Personas;
import org.springframework.data.repository.CrudRepository;

//CrudRepository<personas,Long> eso lo que esta haciendo es que contrulle los 
// mentos clasicos de insert select delete update, por eso le estamos pasando 
// <personas,Long> el objeto que vamos a usar en este caso es Personas
// que a subes esta serializada, Long es para indicarle cual es le primari key 
// es para indicarle de que tipo es
public interface IPersonaDao extends CrudRepository<Personas,Long>{
    
    
    
}
