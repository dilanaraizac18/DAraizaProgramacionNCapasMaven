
package com.digis01.DAraizaProgramacionNCapasMaven.ML;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;


public class Usuario {
    
    private int IdUsuario;
    @NotEmpty(message= "No puede ser nulo")
    @Size(min = 3, max = 50, message = "El nombre debe llevar minimo 3 caracteres")
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String NumeroTelefonico;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaNacimiento;
    private String CURP;
    private String Username;
    private String Sexo;
    private String Celular;
    private String Email;
    private String Password;
    public com.digis01.DAraizaProgramacionNCapasMaven.ML.Rol Rol;    
    public List<com.digis01.DAraizaProgramacionNCapasMaven.ML.Direccion> Direcciones;
    
    //setters
    public int getIdUsuario(){
        return IdUsuario;
    }
    
    public void setIdUsuario(int IdUsuario){
        this.IdUsuario = IdUsuario;
    }

    public Usuario(int IdUsuario, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String NumeroTelefonico, Date FechaNacimiento, String CURP, String Username, String Sexo, String Celular, String Email, String Password, Rol Rol, List<Direccion> Direcciones) {
        this.IdUsuario = IdUsuario;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.NumeroTelefonico = NumeroTelefonico;
        this.FechaNacimiento = FechaNacimiento;
        this.CURP = CURP;
        this.Username = Username;
        this.Sexo = Sexo;
        this.Celular = Celular;
        this.Email = Email;
        this.Password = Password;
        this.Rol = Rol;
        this.Direcciones = Direcciones;
    }
    
    
    public Usuario(){
    }

   

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public void setApellidoPaterno(String ApellidoPaterno){
        this.ApellidoPaterno = ApellidoPaterno;
        
    }
    
    public String getApellidPaterno(){
        return ApellidoPaterno;
    }
    
    public void setApellidoMaterno (String ApellidoMaterno){
        this.ApellidoMaterno = ApellidoMaterno;
    }
    
    public String getApellidoMaterno (){
        return ApellidoMaterno;
    }
     

    public void setFechaNacimiento(Date FechaNacimiento){
        this.FechaNacimiento = FechaNacimiento;
    }
    
    public Date getFechaNacimiento(){
        return FechaNacimiento;
    }
    
    public void setCURP(String CURP){
        this.CURP = CURP;
    }
    
    public String getCURP(){
        return CURP;
    }
    
    public void setNumeroTelefonico(String NumeroTelefonico){
        this.NumeroTelefonico = NumeroTelefonico;
    }
    
    public String getNumeroTelefonico (){
        return NumeroTelefonico;
    } 
    
    public void setUsername(String Username){
        this.Username = Username;
    }
    
    public String getUsername(){
        return Username;
    }
    
    
    public void setSexo(String Sexo){
        this.Sexo = Sexo;
    }
    
    public String getSexo(){
        return Sexo;
    }
    
    public void setCelular(String Celular){
        this.Celular =  Celular;
    }
    
    public String getCelular(){
        return Celular;
    }
    
    public void setEmail(String Email){
        this.Email = Email;
    }
    
    public String getEmail (){
        return Email;
    }
    
    public void setPassword(String Password){
        this.Password = Password;
    }
    
    public String getPassword(){
        return Password;
    }

    public Rol getRol() {
        return Rol;
    }

    public void setRol(Rol Rol) {
        this.Rol = Rol;
    }

    public List<Direccion> getDirecciones() {
        return Direcciones;
    }

    public void setDirecciones(List<Direccion> Direcciones) {
        this.Direcciones = Direcciones;
    }
    
 
}
