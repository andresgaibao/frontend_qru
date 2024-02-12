package com.example.qru.services;

import com.example.qru.entities.Roll;
import com.example.qru.exceptions.MyException;
import com.example.qru.repositories.RollRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRoll {

    @Autowired
    RollRepository rollRepository;

    @Transactional
    public void createRoll(String name, String description, boolean state) throws MyException {
        validate(name, description);
        
        Roll roll = new Roll();
        
        roll.setName(name);
        roll.setDescription(description);
        roll.setState(state);
        
        rollRepository.save(roll);
    }
    
    public List<Roll> listRolls(){
        
        List<Roll> rolls = new ArrayList();
        
        rolls = rollRepository.findAll();
        
        return rolls;
    }
    
    @Transactional
    public void modifyRoll(String name,String description, boolean state) throws MyException{
        validate(name, description);
        
        Roll roll = new Roll();
        
        roll.setName(name);
        roll.setDescription(description);
        roll.setState(state);
        
        rollRepository.save(roll);
    }
    
    @Transactional
    public void deleteRollById(String rollId) throws MyException {
        if (rollId.isEmpty() || rollId == null) {
            throw new MyException("El ID de roll no puede ser nulo o estar vacío");
        }

        Optional<Roll> rollToDelete = rollRepository.findById(rollId);

        if (rollToDelete.isPresent()) {
            rollRepository.delete(rollToDelete.get());
        } else {
            throw new MyException("No se encontró un roll con el ID proporcionado");
        }
    }
    
     public Roll getOne(String id){
        return rollRepository.getOne(id);
    }


    public void validate(String name, String description) throws MyException {

        if (name.isEmpty() || name == null) {
            throw new MyException("El nombre no puede ser nulo o estar vacio");
        }

        if (description.isEmpty() || description == null) {
            throw new MyException("La descrición no puede ser nula o estar vacia");
        }

    }
}
