/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.DAraizaProgramacionNCapasMaven.Configuration.DAO;

import com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;

/**
 *
 * @author digis
 */
public interface IUsuarioJPA {
    Result GetAll();
    Result ADD(Usuario usuario);
    Result GetById(int idUsuario);
    Result Delete(int idusuario);
    Result UpdateImagen(int idUsuario);
}
