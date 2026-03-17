/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DAraizaProgramacionNCapasMaven.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Dilan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserDetailJPA userDetailJPA;

    public SecurityConfiguration(UserDetailJPA userDetailJPA) {
        this.userDetailJPA = userDetailJPA;
    }
    // 1 validar que rutas o endpoints necesitan un proceso de seguridad
    // Almacena la sesión del usuario una vez logueado 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
     
        /*Usar un login personalizado (hasta que todo, todoooooo quede funcional)*/
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/alumno/**")
                .hasAnyRole("1er Semestre", "2do Semestre")
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .defaultSuccessUrl("/alumno")
                )
                .userDetailsService(userDetailJPA);
                
        
        return http.build();          
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return new NoOpPasswordEncoder();
    }

    
    
    
    
    
    
}
