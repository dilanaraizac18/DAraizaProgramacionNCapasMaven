/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DAraizaProgramacionNCapasMaven;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    
    @GetMapping
    public String Login(@RequestParam(value = "error", required = false) String error,  Model model){
        
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        
        
        return "Login";
    }
}
