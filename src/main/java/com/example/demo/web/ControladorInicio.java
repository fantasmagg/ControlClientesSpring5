/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.web;


import com.example.demo.domain.Personas;
import com.example.demo.servicio.PersonaServiceImp;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// esa anotacion combierte esta clase en un controlador 
@Controller
@Slf4j
public class ControladorInicio {
    
  
    @Autowired
    private PersonaServiceImp personaService;
    
    /*
    @AuthenticationPrincipal User user aqui estamos trayendonos al usuario 
    que hizo login
    */
    @GetMapping("/")
    public String inicio (Model model,@AuthenticationPrincipal User user){
        var personas = personaService.listarPersonas();
        //aqui estamos imprimiendo el usuario que hizo login en la consola
        log.info("usuario que hizo login:"+user);
        model.addAttribute("persona", personas);
        
        
        var saldoTotal =  0D;
        
        for(var p : personas){
            saldoTotal +=p.getSaldo();
        }
        model.addAttribute("saldoTotal",saldoTotal);
        
        model.addAttribute("totalClientes",personas.size());
        
        return "index";
        
    }
    
    /*
    
    public String agregar(Personas person){
        return "modificar";
    }
    
    eso parece un metodo simple que a su ves esta haciendo barias cosas
     la primera y mas obvia es que esta recibiendo la peticion get 
    de "agregar" luego nos redirege a la pagina de modificar que tenemos 
    
    pero tambien 
    
    ves ese objecto que esta reciviendo por parametro 
    
    Personas person ese objecto se esta inicializando, y a su ves, 
    se esta enviando junto con la el return es como si estuvieramos 
    compatiendo en todos los niveles esa variable 
    
    luego lo vamos a recuperar en el archivo modificar
    
    */
    @GetMapping("/agregar")
    public String agregar(Personas personas){
        return "modificar";
    }
    
    //@Valid Personas personas ,eso se le pone a los objetos que vayamos a validar
    //en este caso se le pone a personas por que se esta validando incluso desde la clase Personas
    //para que no pueda resivir valores vacios
    @PostMapping("/guardar")
    public String guardar(@Valid Personas personas, Errors errores){
        
        if(errores.hasErrors()){
            return "modificar";
        }
        
        /*
            personaService.guardar(personas); ese metodo esta funcioando tanto para guardar 
            un objecto nuevo como tambien para modificar uno ya existente
        */
        personaService.guardar(personas);
        return "redirect:/";
    }
    
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Personas personas,Model model){
        personas=personaService.encontrarPersona(personas);
        model.addAttribute("personas", personas);
        return "modificar";
    }
    
    //aqui estamos enviando el idpersona unido a esa peticion /eliminar
    @GetMapping("/eliminar")
    public String eliminar(Personas personas){
        personaService.eliminar(personas);
        return "redirect:/";
    }
    
    
}
