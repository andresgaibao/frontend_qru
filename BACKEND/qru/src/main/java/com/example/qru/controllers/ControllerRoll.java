
package com.example.qru.controllers;

import com.example.qru.entities.Roll;
import com.example.qru.entities.User;
import com.example.qru.exceptions.MyException;
import com.example.qru.services.ServiceRoll;
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
@RequestMapping("/roll")
public class ControllerRoll {
    
    @Autowired
    ServiceRoll serviceRoll;
    
     @GetMapping("/registrar") //localhost:8080/user/registrar
    public String registrar(){
        return "";
    }
    
    
    @PostMapping("/registro")
    public String registro(@RequestParam String name,String descrition,boolean state, ModelMap modelo){
        
        try {
            serviceRoll.createRoll(name, descrition, state);
            
            modelo.put("exito", "El Roll fue registrado correctamente!");
        } catch (MyException ex) {
                      
            modelo.put("error", ex.getMessage());
            return "";
        }
        
        return "";        
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List <Roll> rolls = serviceRoll.listRolls();
        
        modelo.addAttribute("rolls", rolls);
        
        return "";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("autor", serviceRoll.getOne(id));
        
        return "autor_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String name,String description,boolean state, ModelMap modelo){
        try {
            serviceRoll.modifyRoll(name, description, state);
            
            return "redirect:../lista";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "";
        }
        
    }
}
