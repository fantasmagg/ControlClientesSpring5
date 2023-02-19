/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.servicio;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.domain.Rol;
import com.example.demo.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{

    
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
         
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        
        //GrantedAuthority eso es para manejar los roles, no podemos poner
        // dik  ArrayList<Rol>(); ya que GrantedAuthority esta es la clase que 
        // se encarga de manejar los roles en spring
        var roles = new ArrayList<GrantedAuthority>();
        
        
        for(Rol rol:usuario.getRoles()){
                //aqui estamos combirtiendo los roles al formato de spring
                // para luego al macenarlos en una array de los roles de spring
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
            
        }
        
        return new User(usuario.getUsername(),usuario.getPassword(),roles);

    }
    
    
    
}
