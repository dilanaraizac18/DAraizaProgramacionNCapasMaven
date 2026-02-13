
package com.digis01.DAraizaProgramacionNCapasMaven.ML;


public class Municipio {
    private int idMunicipio;
    private String Nombre;
    public com.digis01.DAraizaProgramacionNCapasMaven.ML.Estado estado;
    
    
    
    public void setIdMunicipio(int idMunicipio){
        this.idMunicipio = idMunicipio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public int getIdMunicipio(){
        return idMunicipio;
    }
    
    public void setNombre( String Nombre){
        this.Nombre = Nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
}
