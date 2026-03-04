/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO;

import com.digis01.DAraizaProgramacionNCapasMaven.JPA.Colonia;
import com.digis01.DAraizaProgramacionNCapasMaven.JPA.Direccion;
import com.digis01.DAraizaProgramacionNCapasMaven.JPA.Rol;
import com.digis01.DAraizaProgramacionNCapasMaven.JPA.Usuario;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class UsuarioDAOJPAImplementation implements IUsuarioJPA{

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired 
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        
        
        try{
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);
            
            List<Usuario> usuarios = queryUsuario.getResultList();
            
            result.objects = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                result.objects.add(usuario);
                
            }
            result.correct = true;
            
        }catch( Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        
        
        return result;
        
    }
    
    @Override
    @Transactional
    public Result ADD(com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario usuario){
        Result result = new Result();
        
        try{
            Usuario usuariojpa = new Usuario();
            
            usuariojpa.setNombre(usuario.getNombre());
            usuariojpa.setApellidoPaterno(usuario.getApellidoPaterno());
            usuariojpa.setApellidoMaterno(usuario.getApellidoMaterno());
            usuariojpa.setEmail(usuario.getEmail());
            usuariojpa.setFechaNacimiento(usuario.getFechaNacimiento());
            usuariojpa.setNumeroTelefonico(usuario.getNumeroTelefonico());
            usuariojpa.setCelular(usuario.getNumeroTelefonico());
            usuariojpa.setUsername(usuario.getUsername());
            usuariojpa.setImagen(usuario.getImagen());
            usuariojpa.setPassword(usuario.getPassword());

            usuariojpa.Rol = new Rol();
            usuariojpa.Rol.setidRol(usuario.Rol.getidRol());
            
            usuariojpa.Direcciones = new ArrayList<>();
            Direccion direccionjpa = new Direccion();
            direccionjpa.colonia = new Colonia();
            
            com.digis01.DAraizaProgramacionNCapasMaven.ML.Direccion direccion = usuario.Direcciones.get(0);
            
            direccionjpa.setCalle(direccion.getCalle());
            direccionjpa.setNumeroInterior(direccion.getNumeroInterior());
            direccionjpa.setNumeroExterior(direccion.getNumeroExterior());
            direccionjpa.colonia.setIdColonia(direccion.colonia.getIdColonia());
            
            
            usuariojpa.Direcciones.add(direccionjpa);
            direccionjpa.usuario = usuariojpa;
            
            entityManager.persist(usuariojpa);
            
            result.correct = true;
            
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        
        return result;
    }

    @Override
    public Result GetById(int idUsuario) {
        ModelMapper modelMapper = new ModelMapper();
        
        Result result = new Result();
        
        try{
            Usuario usuariojpa = entityManager.find(Usuario.class,idUsuario);
            
             if (usuariojpa != null) {

            com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario usuarioML =
                    modelMapper.map(
                            usuariojpa,
                            com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario.class
                    );

            result.object = usuarioML;
            result.correct = true;

        } else {
            result.correct = false;
            result.errorMessage = "Usuario no encontrado";
        }
            
        }catch( Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        return result;
    }

    @Override
    @Transactional
    public Result Delete(int idusuario) {
        Result result = new Result();
        
        try{
            com.digis01.DAraizaProgramacionNCapasMaven.JPA.Usuario usuariojpa = entityManager.find(com.digis01.DAraizaProgramacionNCapasMaven.JPA.Usuario.class, idusuario);
            
            entityManager.remove(usuariojpa);
            
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        
        return result;
    }

    @Override
    public Result UpdateImagen(int idUsuario) {
        Result result = new Result();
        
        try{
            com.digis01.DAraizaProgramacionNCapasMaven.JPA.Usuario usuariojpa = entityManager.find(com.digis01.DAraizaProgramacionNCapasMaven.JPA.Usuario.class, idUsuario);

            if(usuariojpa != null){
                usuariojpa.setImagen(usuariojpa.getImagen());
                
                result.correct = true;
            }else{
                result.correct= false;
                result.errorMessage = "No se ha encontrado al usuario";
            }
            
            
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        return result;
        
    }
    
    

 
 
    
    
    
   
    
}
