/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dao;

import com.example.demo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<Usuario,Long> eso lo que esta haciendo es que contrulle los 
// mentos clasicos de insert select delete update, por eso le estamos pasando 
// <personas,Long> el objeto que vamos a usar en este caso es Personas
// que a subes esta serializada, Long es para indicarle cual es le primari key 
// es para indicarle de que tipo es
public interface UsuarioDao extends JpaRepository<Usuario,Long>{
    
    Usuario findByUsername(String username);
    
    
}
