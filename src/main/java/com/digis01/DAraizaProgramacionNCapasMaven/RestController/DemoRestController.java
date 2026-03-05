
package com.digis01.DAraizaProgramacionNCapasMaven.RestController;

import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.UsuarioDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo/api")
public class DemoRestController {
    
    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    @GetMapping
    public String demoApi(){
        
        return "Hola";
    }
    
    @GetMapping("saludo/{nombre}")
    public String saludo(@PathVariable ("nombre") String nombre){
        
        return "Hola " + nombre;
    }
    
    @GetMapping("suma/{numero1}/{numero2}")
    public int SumadosNumeros(@PathVariable ("numero1") int numero1, @PathVariable ("numero2") int numero2){
        
        return (numero1 + numero2);
    }
    
    @GetMapping("datos/{status}")
    public ResponseEntity ObtenerDatos(@PathVariable ("status") int status, Model model){
        Result result = new Result();
        
        
        result = usuarioDAOImplementation.GetAll();
        
        model.addAttribute("datos", result.objects);
        
        
        
        
        return ResponseEntity.status(status).body(result.objects);
    }
    
    @PostMapping("sumanumeros")
    public int SumarNNumeros (@RequestBody List<Integer> numeros){
        
        
        return 
        
    }
            
    
}
