
package com.digis01.DAraizaProgramacionNCapasMaven.ML;

/**
 *
 * @author digis
 */
public class Direccion {
    private int idDireccion;
    private String Calle;
    private String NumeroInterior;
    private String NumeroExterior;
    public com.digis01.DAraizaProgramacionNCapasMaven.ML.Colonia colonia;

    public Direccion() {
    }

    public Direccion(int idDireccion, String Calle, String NumeroInterior, String NumeroExterior, Colonia colonia) {
        this.idDireccion = idDireccion;
        this.Calle = Calle;
        this.NumeroInterior = NumeroInterior;
        this.NumeroExterior = NumeroExterior;
        this.colonia = colonia;
    }
    
    
            
    public void setIdDireccion( int idDireccion){
        this.idDireccion = idDireccion;
    }        
    
    public int getIdDireccion (){
        return idDireccion;
    }
    
    public void setCalle (String Calle){
        this.Calle = Calle;
    }
    
    public String getCalle (){
        return Calle;
    }
    
    public void setNumeroInterior (String NumeroInterior){
        this.NumeroInterior = NumeroInterior;
    }
    
    public String getNumeroInterior (){
        return NumeroInterior;
    }
    
    public void setNumeroExterior (String NumeroExterior){
        this.NumeroExterior = NumeroExterior;
    }
    
    public String getNumeroExterior (){
        return NumeroExterior;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }
    
    
}
