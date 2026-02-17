package com.digis01.DAraizaProgramacionNCapasMaven;

import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.ColoniaDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.EstadoDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.MunicipioDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.PaisDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.RolDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.UsuarioDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Pais;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario;
import jakarta.validation.Valid;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @Autowired
    private PaisDAOImplementation paisDAOImplementation;

    @Autowired
    private RolDAOImplementation rolDAOImplementation;

    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;

    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;
    
    @GetMapping
    public String Usuarios(Model model) {
//        List<Usuario> usuario= new ArrayList<>();  
//        usuario.add(new Usuario("Dilan","Araiza"));
//        
//        model.addAttribute("usuario", usuario);
//        return "GetAll";

        Result result = usuarioDAOImplementation.GetAll();
        model.addAttribute("usuario", result.objects);
        return ("GetAll");
    }

    @GetMapping("getid")
    public String UsuariosGetById(@RequestParam int numero, Model model) {
        Result result = usuarioDAOImplementation.GetById(numero);
        model.addAttribute("usuario", result.objects);

        return ("GetByID");
    }

    @GetMapping("form")
    public String Formulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        Result resultPaises = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultPaises.objects);

        Result resultRol = rolDAOImplementation.GetAll();
        model.addAttribute("roles", resultRol.objects);

        return "Formulario";
    }

    @PostMapping("form")
    public String Formulario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, @RequestParam("Imagen")MultipartFile imagen, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            return "form";
            
        }
        
         String nombreArchivo = imagen.getOriginalFilename();
         
        //2. Cortar la palabra
        String[] cadena = nombreArchivo.split("\\.");
        if (cadena[1].equals("jpg") || cadena[1].equals("png")) {
            //convierto imagen a base 64, y la cargo en el modelo alumno 
            byte [] fileContent = nombreArchivo.getBytes();
            
            String encodedString = Base64.getEncoder().encodeToString(fileContent);   
            
            System.out.println(encodedString);
            // realizar la conversión de imagen a base 64; 
        } else if (imagen != null){
            //retorno error de archivo no permititido y regreso a formulario 
            System.out.println("Error");
        }
        System.out.println("Agregar");
//        usuarioDAOImplementation.Add(usuario); IMPLEMENTAR MAS TARDE
        //proceso de agregar datos y retorno a vista de todos los usuarios
        return "redirect:/usuario";
    }
    
    @GetMapping("/GetById/IdUsuario")
    public String GetById(@RequestParam int IdUsuario, Model model) {

        Result result = usuarioDAOImplementation.GetById(IdUsuario);

        model.addAttribute("usuario", result.object);
        return "usuario";
    }
    

    @GetMapping("getEstadosByPais/{idPais}")
    @ResponseBody
    public Result getEstadosByPais(@PathVariable("idPais") int idPais) {
        Result result = estadoDAOImplementation.GetByID(idPais);

        return result;
    }
    
    @GetMapping("getMunicipioByEstado/{idEstado}")
    @ResponseBody
    public Result getMunicipioByEstado(@PathVariable ("idEstado") int idEstado){
        Result result = municipioDAOImplementation.GetById(idEstado);
        
        return result;
    }
    
    @GetMapping("getColoniaByMunicipio/{idMunicipio}")
    @ResponseBody
    public Result getColoniaByMunicipioS(@PathVariable ("idMunicipio") int idMunicipio){
        Result result = coloniaDAOImplementation.GetByID(idMunicipio);
        
        return result;
    }

}