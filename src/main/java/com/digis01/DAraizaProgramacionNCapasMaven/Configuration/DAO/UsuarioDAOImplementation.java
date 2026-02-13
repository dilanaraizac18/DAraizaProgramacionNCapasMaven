
package com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO;

import com.digis01.DAraizaProgramacionNCapasMaven.ML.Colonia;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Direccion;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Estado;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Municipio;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Pais;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Rol;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuario{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    

    @Override
    public Result GetAll() {
        Result result = new Result();
        
        jdbcTemplate.execute("{CALL UsuarioDireccionGetAllSP(?)}", (CallableStatementCallback<Boolean>)callableStatement ->{
            callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            callableStatement.execute();
            
            ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
            
            result.objects = new ArrayList<>();
            
            
            
            
            while(resultSet.next()){
                int idUsuario = resultSet.getInt("idUsuario");
                if (!result.objects.isEmpty() && idUsuario == ((Usuario) (result.objects.get(result.objects.size() - 1))).getIdUsuario()) {
                    Direccion direccion = new Direccion();
                    Colonia colonia = new Colonia();
                    Municipio municipio= new Municipio();
                    Estado estado = new Estado();
                    Pais pais = new Pais();
                    direccion.setIdDireccion(resultSet.getInt("idDireccion"));
                    direccion.setCalle(resultSet.getString("Calle"));
                    direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                    direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                    direccion.colonia = new Colonia();
                    direccion.colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                    direccion.colonia.setNombre(resultSet.getString("NombreColonia"));
                    direccion.colonia.municipio = new Municipio();
                    direccion.colonia.municipio.setNombre(resultSet.getString("MunicipioNombre"));
                    direccion.colonia.municipio.estado = new Estado();
                    direccion.colonia.municipio.estado.setNombre(resultSet.getNString("NombreEstado"));
                    direccion.colonia.municipio.estado.pais = new Pais();
                    direccion.colonia.municipio.estado.pais.setNombre(resultSet.getString("NombrePais"));
                    

                    ((Usuario) (result.objects.get(result.objects.size() - 1))).Direcciones.add(direccion);

                } else {
                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(idUsuario);
                    usuario.setNombre(resultSet.getString("NombreUsuario"));
                    usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                    usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuario.setUsername(resultSet.getString("Username"));
                    usuario.setCURP(resultSet.getString("CURP"));
                    usuario.setEmail(resultSet.getString("Email"));
                    usuario.setNumeroTelefonico(resultSet.getString("NumeroTelefonico"));
                    usuario.setPassword(resultSet.getString("Password"));
                    usuario.setCelular(resultSet.getString("Celular"));
                    usuario.Rol = new Rol();
                    usuario.Rol.setNombreRol(resultSet.getString("NombreRol"));

                    int idDireccion = resultSet.getInt("idDireccion");
                    
                     if (idDireccion != 0) {
                        usuario.Direcciones = new ArrayList<>();
                        Direccion direccion = new Direccion();
                        direccion.setIdDireccion(idDireccion);
                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                        direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                        usuario.Direcciones.add(direccion);

                    }

                    

                    result.objects.add(usuario);

                }
            }
            
            return true;

        });
        return result;
        
        
    }

    @Override
    public Result GetById(int idUsuario) {
        
        Result result = new Result();
        
        jdbcTemplate.execute("{CALL UsuarioDireccionGetById(?,?)}", (CallableStatementCallback<Boolean>)callableStatement ->{
         callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
         callableStatement.setInt(2, idUsuario );
           
         callableStatement.execute();
         
         ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
         
         if(resultSet.next()){
              Usuario usuario = new Usuario();
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                usuario.setCURP(resultSet.getString("CURP"));
                usuario.setUsername(resultSet.getString("Username"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setNumeroTelefonico(resultSet.getString("NuevoTelefono"));
                usuario.setPassword(resultSet.getString("Password"));
                usuario.setCelular(resultSet.getString("Celular"));
                usuario.Rol = new Rol();
                
                usuario.Rol.setNombreRol(resultSet.getString("NombreRol"));
                
                int idDireccion = resultSet.getInt("idDireccion");
                
                if (idDireccion != 0) {
                        usuario.Direcciones = new ArrayList<>();
                        Direccion direccion = new Direccion();
                        direccion.setIdDireccion(idDireccion);
                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                        direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                        usuario.Direcciones.add(direccion);

                    }
                
                result.objects.add(usuario);
         }        
         return true;

         });
            
        
            
      
        
        return result;
    }
    
}
