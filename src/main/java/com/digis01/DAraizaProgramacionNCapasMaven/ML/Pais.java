
package com.digis01.DAraizaProgramacionNCapasMaven.ML;

public class Pais {
    private int idPais;
    private String Nombre;

    public Pais(int idPais, String Nombre) {
        this.idPais = idPais;
        this.Nombre = Nombre;
    }
    
    public Pais(){
        
    }
    
    
    public void setIdPais(int idPais){
        this.idPais = idPais;
    }
    
    public int getIdPais( ){
        return idPais;
    }
    
    public void setNombre( String Nombre){
        this.Nombre = Nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
}
