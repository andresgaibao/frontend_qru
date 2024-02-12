package com.example.qru.controllers;

import com.example.qru.entities.User;
import com.example.qru.exceptions.MyException;
import com.example.qru.services.ServiceUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class ControllerUser {

    @Autowired
    ServiceUser serviceUser;
    
    @GetMapping("/registrar") //localhost:8080/user/registrar
    public String registrar(){
        return "";
    }
    
    
    @PostMapping("/registro")
    public String registro(@RequestParam String name,String username,String email, String password, String password2, ModelMap modelo){
        
        try {
            serviceUser. createUser(name, username, email, name, password, password2, email, password2);
            
            modelo.put("exito", "El Autor fue registrado correctamente!");
        } catch (MyException ex) {
                      
            modelo.put("error", ex.getMessage());
            return "";
        }
        
        return "";        
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List <User> users = serviceUser.listUsers();
        
        modelo.addAttribute("users", users);
        
        return "";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("autor", serviceUser.getOne(id));
        
        return "autor_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String name,String username,String email, String password, String password2, ModelMap modelo){
        try {
            serviceUser.ModifyUser(name, username, email, id, password, password2, id, id);
            
            return "redirect:../lista";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "";
        }
        
    }
    
     @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        try {
            serviceUser.deleteUserById(id);
            return "redirect:../lista";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "";
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPost(@PathVariable String id, ModelMap modelo) {
        try {
            serviceUser.deleteUserById(id);
            return "redirect:../../lista"; // Puedes ajustar la redirección según la estructura de tus rutas
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "";
        }
    }
}
