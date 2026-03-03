
package com.digis01.DAraizaProgramacionNCapasMaven.JPA;

import com.digis01.DAraizaProgramacionNCapasMaven.ML.*;
import jakarta.persistence.Entity;

@Entity
public class Estado {
    private int idEstado;
    private String Nombre;
    public Pais pais;

    public int getIdEstado() {
        return idEstado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
    
}
