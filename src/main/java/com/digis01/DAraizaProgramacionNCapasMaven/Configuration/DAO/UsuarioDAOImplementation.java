
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
import java.text.SimpleDateFormat;
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
                    usuario.setImagen(resultSet.getString("Imagen"));
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
    public Result GetById(int identificador) {
        
        Result result = new Result();
        
        try{
        jdbcTemplate.execute("{CALL UsuarioDireccionGetById(?,?)}", (CallableStatementCallback<Boolean>)callableStatement ->{
         callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
         callableStatement.setInt(2, identificador );
           
         callableStatement.execute();
         
         ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
         
         result.objects = new ArrayList<>();
                while (resultSet.next()) {
                    int idUsuario = resultSet.getInt("idUsuario");
                    //si no esta vacias yyy es el mismo que el ultimo
                    if (!result.objects.isEmpty() && idUsuario == ((Usuario) (result.objects.get(result.objects.size() - 1))).getIdUsuario()) {
                        Direccion direccion = new Direccion();

                        direccion.setIdDireccion(resultSet.getInt("idDireccion"));
                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumeroInterior("NumeroInterior");
                        direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                        direccion.colonia = new Colonia();
                        direccion.colonia.municipio = new Municipio();
                        direccion.colonia.municipio.estado = new Estado();
                        direccion.colonia.municipio.estado.pais = new Pais();

                        direccion.colonia.setIdColonia(resultSet.getInt("idColonia"));
                        direccion.colonia.setNombre(resultSet.getString("NombreColonia"));
                        direccion.colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                        direccion.colonia.municipio.setIdMunicipio(resultSet.getInt("idMunicipio"));
                        direccion.colonia.municipio.setNombre(resultSet.getString("NombreMunicipio"));
                        direccion.colonia.municipio.estado.setIdEstado(resultSet.getInt("idEstado"));
                        direccion.colonia.municipio.estado.setNombre(resultSet.getString("NombreEstado"));
                        direccion.colonia.municipio.estado.pais.setIdPais(resultSet.getInt("idPais"));
                        direccion.colonia.municipio.estado.pais.setNombre("NombrePais");

                        ((Usuario) (result.objects.get(result.objects.size() - 1))).Direcciones.add(direccion);
                    } else {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(idUsuario);
                        usuario.setNombre(resultSet.getString("NombreUsuario"));
                        usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                        usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                        usuario.setNumeroTelefonico(resultSet.getString("NumeroTelefonico"));
                        usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                        usuario.setEmail(resultSet.getString("Email"));
                        usuario.setCURP(resultSet.getString("CURP"));
                        usuario.setUsername(resultSet.getString("Username"));
                        usuario.setSexo(resultSet.getString("Sexo"));
                        usuario.setPassword(resultSet.getString("Password"));
                        usuario.setCelular(resultSet.getString("Celular"));
                        usuario.setImagen(resultSet.getString("Imagen"));
                        usuario.Rol = new Rol();
                        usuario.Rol.setidRol(resultSet.getInt("idRol"));
                        usuario.Rol.setNombreRol(resultSet.getString("NombreRol"));

                        int idDireccion = resultSet.getInt("IdDireccion");
                        if (idDireccion != 0) {
                            usuario.Direcciones = new ArrayList<>();
                            Direccion direccion = new Direccion();
                            direccion.setIdDireccion(idDireccion);
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                            direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                            direccion.colonia = new Colonia();
                            direccion.colonia.municipio = new Municipio();
                            direccion.colonia.municipio.estado = new Estado();
                            direccion.colonia.municipio.estado.pais = new Pais();

                            direccion.colonia.setIdColonia(resultSet.getInt("idColonia"));
                            direccion.colonia.setNombre(resultSet.getString("NombreColonia"));
                            direccion.colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                            direccion.colonia.municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                            direccion.colonia.municipio.setNombre(resultSet.getString("NombreMunicipio"));
                            direccion.colonia.municipio.estado.setIdEstado(resultSet.getInt("IdEstado"));
                            direccion.colonia.municipio.estado.setNombre(resultSet.getString("NombreEstado"));
                            direccion.colonia.municipio.estado.pais.setIdPais(resultSet.getInt("IdPais"));
                            direccion.colonia.municipio.estado.pais.setNombre("NombrePais");

                            usuario.Direcciones.add(direccion);

                        }
                        result.object = usuario;
                        result.correct = true;
                    }
                }
                return true;
            });
        
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
}            
        
            
      
        
        return result;
    }
    
    @Override
    public Result Add(Usuario usuario) {
        Result result = new Result();
        try {
            jdbcTemplate.execute("{CALL UsuarioDireccionesAddSP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
//            ResultSet resultSet = (ResultSet) callableStatement.getObject(1);            
                Direccion direccion = usuario.Direcciones.get(0);
//            direccion.setCalle(resultSet.getString("Calle")); //ya no se usa el resultset

                callableStatement.setString(1, usuario.getNombre());
                callableStatement.setString(2, usuario.getApellidoPaterno());
                callableStatement.setString(3, usuario.getApellidoMaterno());
                callableStatement.setString(4, usuario.getEmail());
                callableStatement.setString(5, usuario.getPassword());
                callableStatement.setString(6, usuario.getUsername());

               
                callableStatement.setDate(7, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
                System.out.println(usuario.getFechaNacimiento());
                System.out.println(usuario.getFechaNacimiento().getTime());
                callableStatement.setString(8, usuario.getSexo());

                callableStatement.setString(9, usuario.getNumeroTelefonico());
                callableStatement.setString(10, usuario.getCelular());
                callableStatement.setString(11, usuario.getCURP());
                callableStatement.setInt(12, usuario.Rol.getidRol());
                callableStatement.setString(13, usuario.getImagen());
                //aqui empiezan las direcciones
                callableStatement.setString(14, direccion.getCalle());
                callableStatement.setString(15, direccion.getNumeroInterior());
                callableStatement.setString(16, direccion.getNumeroExterior());
                callableStatement.setInt(17, direccion.colonia.getIdColonia());

                int rowAffected = 0;
                rowAffected = callableStatement.executeUpdate();

                result.correct = rowAffected != 0 ? true : false;

                return true;
            });
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }

        return result;
    }
    @Override
    public Result Delete (int identificadorUsuario){
        Result result = new Result();
        
        try{
            jdbcTemplate.execute("{Call UsuarioDeleteSP(?)}", (CallableStatementCallback<Boolean>) callableStatement->{
               
                callableStatement.setInt(1, identificadorUsuario);
                int rowAffect = callableStatement.executeUpdate();
                
                if(rowAffect !=0){
                    System.out.println("Completado con exito");
                    result.correct = true;
                }else{
                    result.correct = false;
                }
                
                return true;
            });
            
            
        } catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        
        return result;
    }
    
  
    @Override
    public Result Update(Usuario usuario) {
        Result result = new Result();

        try {

            jdbcTemplate.execute("{CALL UsuarioUpdateSP(?,?,?,?,?,?,?,?,?,?,?,?)}", (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.setString(1, usuario.getNombre());
                callableStatement.setString(2, usuario.getApellidoPaterno());
                callableStatement.setString(3, usuario.getApellidoMaterno());
                callableStatement.setDate(4, new java.sql.Date(usuario.getFechaNacimiento().getTime()));                
                callableStatement.setString(5, usuario.getCURP());                
                callableStatement.setString(6, usuario.getUsername());
                callableStatement.setString(7, usuario.getEmail());

                callableStatement.setString(8, usuario.getNumeroTelefonico());
                callableStatement.setString(9, usuario.getSexo());
                callableStatement.setString(10, usuario.getCelular());
                callableStatement.setInt(11, usuario.Rol.getidRol());
                callableStatement.setInt(12, usuario.getIdUsuario());
                
                int rowAffectted = 0;
                rowAffectted = callableStatement.executeUpdate();
                
                result.correct = rowAffectted != 0 ? true : false;

                return true;
            });

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }

        return result;
    }

    
    @Override
    public Result UpdateImagen(Usuario usuario){
        Result result = new Result();
        
        try{
            jdbcTemplate.execute("{CALL UsuarioImageUpdateSP(?,?)}", (CallableStatementCallback<Boolean>) callableStatement ->{
            callableStatement.setString(1, usuario.getImagen());
            callableStatement.setInt(2, usuario.getIdUsuario());
             int rowAffectted = 0;
                rowAffectted = callableStatement.executeUpdate();
                
                result.correct = rowAffectted != 0 ? true : false;

                return true;
            } );
        
        
        }
            
            
        catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
}
}
 
