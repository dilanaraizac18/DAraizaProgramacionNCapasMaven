
package com.digis01.DAraizaProgramacionNCapasMaven.ML;

public class Rol {
    public String NombreRol;
    public int idRol;

    public Rol(String NombreRol, int idRol) {
        this.NombreRol = NombreRol;
        this.idRol = idRol;
    }
    
    public Rol(){
        
    }
    
    public void setNombreRol(String NombreRol){
        this.NombreRol = NombreRol;
    }
    
    public String getNombreRol(){
        return NombreRol;
    }
    
    public void setidRol(int idRol){
        this.idRol = idRol;
    }
    
    public int getidRol(){
        return idRol;
    }
    
}
