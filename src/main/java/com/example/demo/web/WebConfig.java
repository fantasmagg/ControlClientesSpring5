
package com.example.demo.web;


import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/*
esto es una clase para configurar los idiomas
la sesscion del video donde vi esto lo puedes encontrar en las notas

*/


@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    
    @Bean
    public LocaleResolver localeResolver(){
           var slr = new SessionLocaleResolver();
           slr.setDefaultLocale(new Locale("es"));
           return slr;
           
    }
    
    @Bean
    public LocaleChangeInterceptor localeChengeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
        
        
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        
        registro.addInterceptor(localeChengeInterceptor());
        
    }
    
    
    /*
    esto lo que hace es poner una pagina por default
    en este caso es la de inicio, pero solo queremos que se muestre
    si estas logeado, por eso tenemos que poner una parte que le 
    permita logearse al inicio
    
    */
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }
    
    
}
