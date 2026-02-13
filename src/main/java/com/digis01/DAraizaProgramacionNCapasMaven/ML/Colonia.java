
package com.digis01.DAraizaProgramacionNCapasMaven.ML;


public class Colonia {
    private int idColonia;
    private String Nombre;
    private String CodigoPostal;
    public com.digis01.DAraizaProgramacionNCapasMaven.ML.Municipio municipio;
    
    public void setIdColonia(int idColonia){
        this.idColonia = idColonia;
    }
    
    public int getIdColonia(){
        return idColonia;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public void setCodigoPostal( String CodigoPostal){
        this.CodigoPostal = CodigoPostal;
}
    public String getCodigoPostal(){
        return CodigoPostal;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
}
