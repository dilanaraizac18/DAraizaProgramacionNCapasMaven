package com.digis01.DAraizaProgramacionNCapasMaven;

import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.ColoniaDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.EstadoDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.MunicipioDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.PaisDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.RolDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO.UsuarioDAOImplementation;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.ErroresArchivo;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Pais;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Rol;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario;
import com.digis01.DAraizaProgramacionNCapasMaven.Service.ValidationService;
import jakarta.validation.Valid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @Autowired
    private ValidationService validationService;

    @GetMapping
    public String Usuarios(Model model) {
//        List<Usuario> usuario= new ArrayList<>();  
//        usuario.add(new Usuario("Dilan","Araiza"));
//        
//        model.addAttribute("usuario", usuario);
//        return "GetAll";
    
        
        Result resultRol = rolDAOImplementation.GetAll();
        model.addAttribute("roles", resultRol.objects);
        Result result = usuarioDAOImplementation.GetAll();
        model.addAttribute("usuario", result.objects);
        
        model.addAttribute("usuariobuscar", new Usuario() );
        
        return ("GetAll");
    }
    
    
    @PostMapping
    public String Search(@ModelAttribute ("usuariobuscar") Usuario usuario, Model model){
        Result result = new Result();
        try{
        Result resultsearch = usuarioDAOImplementation.Search(usuario);
        model.addAttribute("usuarios", resultsearch.objects);
        Result resultRol = rolDAOImplementation.GetAll();
        model.addAttribute("roles", resultRol.objects);
        Result resultpais = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultpais.objects);
        model.addAttribute("usuariobuscar", usuario);

        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
    return "GetAll";
    }
    
    
    @GetMapping("details")
    public String DetailsVista( Model model){
        
        return ("Details");
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
    public String Formulario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, @RequestParam("imagen") MultipartFile imagen, Model model) {
        Result result = new Result();

        try{
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("usuario", usuario);
//            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
//            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
//
//            int idPais = usuario.Direcciones.get(0).colonia.municipio.estado.pais.getIdPais();
//            int idEstado = usuario.Direcciones.get(0).colonia.municipio.estado.getIdEstado();
//            int idMunicipio = usuario.Direcciones.get(0).colonia.municipio.getIdMunicipio();
//            int idColonia = usuario.Direcciones.get(0).colonia.getIdColonia();
//
//         if (idEstado != 0) {
//               model.addAttribute("estados", estadoDAOImplementation.GetByID(idPais).objects);
//           
//            if (idMunicipio != 0) {
//                model.addAttribute("municipios", municipioDAOImplementation.GetById(idEstado).objects);
//            
//
//            if (idColonia != 0) {
//                model.addAttribute("colonias", coloniaDAOImplementation.GetByID(idMunicipio));
//            }
//            }
//         }
//            return "Formulario";
//
//        }

        String nombreArchivo = imagen.getOriginalFilename();

        //2. Cortar la palabra
        String[] cadena = nombreArchivo.split("\\.");
        if (cadena[1].equals(
                "jpg") || cadena[1].equals("png")) {
            //convierto imagen a base 64, y la cargo en el modelo alumno 
            try {
                byte[] fileContent = imagen.getBytes();

                String encodedString = Base64.getEncoder().encodeToString(fileContent);

                System.out.println(encodedString);

                usuario.setImagen(encodedString);

            } catch (Exception ex) {
                result.correct = false;
                result.errorMessage = ex.getLocalizedMessage();
                result.ex = ex;
            }

            // realizar la conversión de imagen a base 64; 
        } else if (imagen
                != null) {
            System.out.println("Error");

            return "form";
            //retorno error de archivo no permititido y regreso a formulario 
        }
        
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage= ex.getLocalizedMessage();
            result.ex = ex;
            
            
        }

        System.out.println(
                "Agregar");
        model.addAttribute("usuario", usuario);
        result = usuarioDAOImplementation.Add(usuario);
        if (result.correct == false) {
            return "Formulario";
        }

        return "redirect:/usuario";
    }
    
    
    @GetMapping("/delete/{idUsuario}")
    public String Delete(@PathVariable ("idUsuario") int idUsuario, RedirectAttributes redirecAttributes){
        
        Result result = new Result();
        result = usuarioDAOImplementation.Delete(idUsuario);
        
        if(result.correct == true){
        redirecAttributes.addFlashAttribute("message","Usuario eliminado");    
        return ("redirect:/usuario");

        }else{
            return("redirect:/usuario");
        }

}
    

        
    
        @PostMapping ("/update/{IdUsuario}") 
    public String UpdateUsuario( @ModelAttribute("usuario") Usuario usuario,RedirectAttributes redirecAttribute, @PathVariable("IdUsuario") int identificador, Model model) {
        Result result = new Result();

        try {
            
            result = usuarioDAOImplementation.Update(usuario);
            if (result.correct == false) {
                return "GetAll";
            }
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }

        return "GetAll";
    }

    @GetMapping("/GetById/{IdUsuario}")
    @ResponseBody
    public Result GetById(@PathVariable("IdUsuario") int identificador, Model model) {
        Result result = new Result();
        try {
            result = usuarioDAOImplementation.GetById(identificador);

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        System.out.println("Funciona GetByIDPais");
        return result;
    }

    @GetMapping("/details/{IdUsuario}")
    public String Details(@PathVariable("IdUsuario") int identificador, Model model) {
        Result result = new Result();
        Usuario usuario = new Usuario();

        try {
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("usuario", usuarioDAOImplementation.GetById(identificador).object);

            int idPais = usuario.Direcciones.get(0).colonia.municipio.estado.pais.getIdPais();
            int idEstado = usuario.Direcciones.get(0).colonia.municipio.estado.getIdEstado();
            int idMunicipio = usuario.Direcciones.get(0).colonia.municipio.getIdMunicipio();
            int idColonia = usuario.Direcciones.get(0).colonia.getIdColonia();

            if (idEstado != 0) {
                //guardo el valor
                model.addAttribute("estados", estadoDAOImplementation.GetByID(idPais).objects);
                if (idMunicipio != 0) {
                    //guardo el valor
                    model.addAttribute("municipios", municipioDAOImplementation.GetById(idEstado).objects);
                    if (idColonia != 0) {
                        //guardo el valor
                        model.addAttribute("colonias", coloniaDAOImplementation.GetByID(idMunicipio).objects);

                    }
                }

            }

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        System.out.println("Funciona GetByIDPais");

        return "Details";
    }

//    COLOCAR EL PROCEDURE O ACTUALIZAR EN LA BASE DE DATOS, NO OLVIDAR CAMBIAR LOS NOMBRES DE LAS TABLAS EN LA BD DE LA EMPRESA

//
//    
    
    
    @PostMapping("/updateimg/{IdUsuario}")
    public String UpdateImagen(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, @PathVariable("IdUsuario") int identificador, @RequestParam("imagen") MultipartFile imagen, Model model) {
        Result result = new Result();
        try {
            //  Verificar si se subió un archivo nuevo
            if (imagen != null && !imagen.isEmpty()) {
                String nombreArchivo = imagen.getOriginalFilename();
                String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
 
                if (extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg")) {
                    // Leer bytes y convertir a Base64
                    byte[] fileContent = imagen.getBytes();
                    String encodedString = Base64.getEncoder().encodeToString(fileContent);
                    usuario.setImagen(encodedString);
                }
            } else {
 
                result = usuarioDAOImplementation.GetById(identificador);
                if (result.correct) {
                    Usuario usuarioanterior = (Usuario) result.object;
                    usuario.setImagen(usuarioanterior.getImagen());
                }
            }
 
            // Ejecutar la actualización
            result = usuarioDAOImplementation.UpdateImagen(usuario);
 
            if (!result.correct) {
                return "redirect:/usuario/details/" + identificador;
            }
 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
 
        return "redirect:/usuario/details/" + identificador;
    }
 
    
    
        @GetMapping("/cargamasiva")
    public String CargaMasiva() {
        return "CargaMasiva";
    }

    @PostMapping("/cargamasiva")
    public String CargaMasiva(@RequestParam("archivo") MultipartFile archivo) {
        try {
            if (archivo != null) {

                String rutaBase = System.getProperty("user.dir");
                String rutaCarpeta = "src/main/resources/archivosCM";
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
                String nombreArchivo = fecha + archivo.getOriginalFilename();
                String rutaArchivo = rutaBase + "/" + rutaCarpeta + "/" + nombreArchivo;
                String extension = archivo.getOriginalFilename().split("\\.")[1];
                List<Usuario> usuarios = null;
                if (extension.equals("txt")) {
                    archivo.transferTo(new File(rutaArchivo));
               usuarios = LecturaArchivoTxt(new File(rutaArchivo));
                } else if (extension.equals("xlsx")) {

                } else {
                    System.out.println("Extensión erronea, manda archivos del formato solicitado");
                }

                
                List<ErroresArchivo> errores = ValidarDatos(usuarios);
                
                 if (errores.isEmpty()) {
//                    se guarda info
                } else {
//                    retorno lista errores, y la renderizo.
                }
                /*
                    - insertarlos
                    - renderizar la lista de errores
                 */
                /*
                    - insertarlos
                    - renderizar la lista de errores
                 */
            }
        } catch (Exception ex) {
            // notificación de error

            System.out.println(ex.getLocalizedMessage());
        }
        return "CargaMasiva";
    }

    public List<Usuario> LecturaArchivoTxt(File archivo) {
        List<Usuario> usuarios = new ArrayList<>();
        try(InputStream inputStream = new FileInputStream(archivo);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            
            usuarios = new ArrayList<>();
            String cadena = "";
            while ( (cadena = bufferedReader.readLine()) != null) {                
//                Nombre|ApellidoPaterno|Materno|Fecha
                String[] datosAlumno = cadena.split("\\|");
                Usuario usuario = new Usuario();
                usuario.setNombre(datosAlumno[0]);
                usuario.setApellidoPaterno(datosAlumno[1]);
                
                usuarios.add(usuario);
            }
            
        }catch(Exception ex){
            return null;
        }
        
        
        return usuarios;
    }

    public List<ErroresArchivo> ValidarDatos(List<Usuario> usuarios) {
        List<ErroresArchivo> errores = new ArrayList<>();

    int fila = 1;

    for (Usuario usuario : usuarios) {
            BindingResult bindingResult = validationService.ValidateObject(usuario);
            
            
             if (bindingResult.hasErrors()) {
                for (ObjectError objectError : bindingResult.getAllErrors()) {
                    ErroresArchivo erroresArchivo = new ErroresArchivo();
//                    erroresArchivo.dato = objectError.getObjectName();
                }
            }
        // Validar nombre
//        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
//            errores.add(new ErroresArchivo(fila, "nombre", "El nombre está vacío"));
//        }
//
//        // Validar apellidoPaterno
//        if (usuario.getApellidoPaterno() == null || usuario.getApellidoPaterno().trim().isEmpty()) {
//            errores.add(new ErroresArchivo(fila, "apellido", "El apellido está vacío"));
//        }
//        
//        if (usuario.getApellidoMaterno() == null || usuario.getApellidoMaterno().trim().isEmpty()) {
//            errores.add(new ErroresArchivo(fila, "apellido", "El apellido está vacío"));
//        }
        
        
        

        // Validar apellidoMaterno
//        if (usuario.getEmail() == null || 
//            !usuario.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
//
//            errores.add(new ErroresArchivo(fila, "email", "Formato de email inválido"));
//        }

        // Validar edad numérica y mayor de edad
//        try {
//            if (usuario.getEdad() == null || usuario.getEdad() < 18) {
//                errores.add(new ErroresArchivo(fila, "edad", "Debe ser mayor de edad"));
//            }
//        } catch (Exception e) {
//            errores.add(new ErroresArchivo(fila, "edad", "Edad no es un número válido"));
//        }


        fila++;
    }

    return errores;
    }
    

    @GetMapping("getEstadosByPais/{idPais}")
    @ResponseBody
    public Result getEstadosByPais(@PathVariable("idPais") int idPais) {
        Result result = estadoDAOImplementation.GetByID(idPais);

        
        result.correct = true;
        return result;
    }

    @GetMapping("getMunicipioByEstado/{idEstado}")
    @ResponseBody
    public Result getMunicipioByEstado(@PathVariable("idEstado") int idEstado) {
        Result result = municipioDAOImplementation.GetById(idEstado);

        result.correct = true;
        return result;
    }

    @GetMapping("getColoniaByMunicipio/{idMunicipio}")
    @ResponseBody
    public Result getColoniaByMunicipio(@PathVariable("idMunicipio") int idMunicipio) {
        Result result = coloniaDAOImplementation.GetByID(idMunicipio);

        result.correct = true;
        return result;
    }

}
