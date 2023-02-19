package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    @Bean 
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    
     
    /*
    con este metodo lo que estamos haciendo es busca la configuracion para el login
    primero buscamos que clase esta implemtado esa funcion 
    "userDetailsService" luego de que la encontramos establecemos esa clase
    como la configuracion de login, en este caso la clase que esta utilizando eso es 
    esta "UsuarioService"
    */
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
  
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        
        auth.inMemoryAuthentication()
                .withUser("admin")
                //{noop} ESO Es para que no se encrite la contrasenna
                .password("{noop}1234")
                .roles("ADMIN","USER")
            .and()
                .withUser("user")
                .password("{noop}1234")
                .roles("USER");
            
        
    }*/
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        
        http.authorizeRequests()
                //aqui estamos indicando los path a los cuales no todo el mundo puede aceder
                // hasRole("ADMIN") ahi estamos diciendo que solo los que tienen
                // el rol de admin pude acceder a /editar/**","/agregar/**","/eliminar
                .antMatchers("/editar/**","/agregar/**","/eliminar")
                    .hasRole("ADMIN")
                /*
                ("/") y aqui estamos indicando la ventana de la lista de usuario el archivo index
                y estamos indicando quienes puden acceder a este path
                USER","ADMIN
                */
                .antMatchers("/")
                .hasAnyRole("USER","ADMIN")
                // esta parte de aqui es para que siempre al inicio se le muestre el logueo al usuario
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                    // esto es para manejar los errores
                    .exceptionHandling().accessDeniedPage("/errores/403")
                ;
    }
    
}
