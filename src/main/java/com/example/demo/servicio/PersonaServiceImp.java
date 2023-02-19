/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.servicio;

import com.example.demo.dao.IPersonaDao;
import com.example.demo.domain.Personas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImp implements PersonasServices{
    
    /*
        @Transactional esa etiqueta lo que hace es la mismo funcion 
        para commit y rollback
    */
    
    @Autowired
    private IPersonaDao personaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Personas> listarPersonas() {
        return (List<Personas>)personaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Personas person) {
        personaDao.save(person);
    }

    @Override
    @Transactional
    public void eliminar(Personas person) {
        personaDao.delete(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Personas encontrarPersona(Personas person) {
        return personaDao.findById(person.getIdPersona()).orElse(null);
    }
    
}
