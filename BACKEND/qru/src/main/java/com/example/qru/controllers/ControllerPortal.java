package com.example.qru.controllers;

import com.example.qru.entities.User;
import com.example.qru.exceptions.MyException;
import com.example.qru.services.ServiceUser;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ControllerPortal {

    @Autowired
    ServiceUser serviceUser;
    
    @GetMapping("/")
    public String index() {

        return "index.html";
    }
    
    @GetMapping("/registrar")
    public String registrar() {
        return "Registro.jsx";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String username,
            String password2, ModelMap modelo) {

       try {
            serviceUser. createUser(name, username, email, name, password, password2, email, password2);
            
            modelo.put("exito", "El Autor fue registrado correctamente!");
        } catch (MyException ex) {
                      
            modelo.put("error", ex.getMessage());
            return "";
        }
        return null;

    }
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo ) {

        if (error != null) {
            modelo.put("error", "Usuario o Contraseña invalidos!");
        }

        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session) {
        
        User logueado = (User) session.getAttribute("usuariosession");
        
        if (logueado.getRoll().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }
        
           return "inicio.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo,HttpSession session){
        User usuario = (User) session.getAttribute("usuariosession");
         modelo.put("usuario", usuario);
        return "usuario_modificar.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/perfil/{id}")
    public String actualizar(@PathVariable String id, @RequestParam String name,@RequestParam String email, 
            @RequestParam String password,@RequestParam String username,@RequestParam String password2, ModelMap modelo) {

        try {
            serviceUser.ModifyUser(name, username, email, id, password, password2, id, id);

            modelo.put("exito", "Usuario actualizado correctamente!");

            return "inicio.html";
        } catch (MyException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", name);
            modelo.put("email", email);

            return "usuario_modificar.html";
        }

    }
    
  
}
