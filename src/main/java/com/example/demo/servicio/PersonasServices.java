
package com.example.demo.servicio;

import com.example.demo.domain.Personas;
import java.util.List;


public interface PersonasServices {
    
    public List<Personas> listarPersonas();
    
    public void guardar (Personas person);
    
    
    public void eliminar (Personas person);
    
    public Personas encontrarPersona(Personas person);
    
    
    
    
    
}
