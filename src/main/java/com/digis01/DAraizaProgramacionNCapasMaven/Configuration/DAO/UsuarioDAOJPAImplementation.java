/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO;

import com.digis01.DAraizaProgramacionNCapasMaven.JPA.Usuario;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class UsuarioDAOJPAImplementation implements IUsuarioJPA{

    @Autowired 
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        
        
        try{
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);
            
            List<Usuario> usuarios = queryUsuario.getResultList();
            
            result.correct = true;
            
        }catch( Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            
        }
        
        
        
        return result;
        
    }
    
    
    
    
   
    
}
