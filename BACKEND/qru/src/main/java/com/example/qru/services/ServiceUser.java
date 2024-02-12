package com.example.qru.services;

import com.example.qru.entities.Photos;
import com.example.qru.entities.Roll;
import com.example.qru.entities.User;
import com.example.qru.exceptions.MyException;
import com.example.qru.repositories.PhotoRepository;
import com.example.qru.repositories.RollRepository;
import com.example.qru.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    RollRepository rollRepository;

    @Transactional
    public void createUser(String name, String username, String email,
            String cc, String password, String Password2, String idRoll,
            String idPhotos) throws MyException {
        validate(name, username, email, cc, password, Password2, idRoll, idPhotos);
        
        Optional<Roll> respuestaR = rollRepository.findById(idRoll);
        Optional<Photos> respuestaP = photoRepository.findById(idPhotos);
        
        Roll roll = new Roll();
        Photos photo = new Photos();
        
        if(respuestaR.isPresent()){
            roll =respuestaR.get();
        }
        
        if(respuestaP.isPresent()){
            photo = respuestaP.get();
        }
        
        User user =new User();
        
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPassword2(Password2);
        user.setRoll(roll);
        user.setPhotos(photo);
        
        userRepository.save(user);
    }
    
    public List<User> listUsers() {

        List<User> users = new ArrayList();

        users = userRepository.findAll();

        return users;
    }
    
    @Transactional
    public void ModifyUser(String name, String username, String email, String cc, String password, String Password2, String idRoll, String idPhotos) throws MyException{
    
        validate(name, username, email, cc, password, Password2, idRoll, idPhotos);
        
        Optional<Roll> respuestaR = rollRepository.findById(idRoll);
        Optional<Photos> respuestaP = photoRepository.findById(idPhotos);
        
        Roll roll = new Roll();
        Photos photo = new Photos();
        
        if(respuestaR.isPresent()){
            roll =respuestaR.get();
        }
        
        if(respuestaP.isPresent()){
            photo = respuestaP.get();
        }
        
        User user =new User();
        
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPassword2(Password2);
        user.setRoll(roll);
        user.setPhotos(photo);
        
        userRepository.save(user);
    }
    
    @Transactional
    public void deleteUserById(String id) throws MyException {
        if (id.isEmpty() || id == null) {
            throw new MyException("El ID de usuario no puede ser nulo o estar vacío");
        }

        Optional<User> userToDelete = userRepository.findById(id);

        if (userToDelete.isPresent()) {
            userRepository.delete(userToDelete.get());
        } else {
            throw new MyException("No se encontró un usuario con el ID proporcionado");
        }
    }
    
     public User getOne(String id){
        return userRepository.getOne(id);
    }

    public void validate(String name, String username, String email, String cc, String password, String password2, String idRoll, String idPhotos) throws MyException {

        if (name.isEmpty() || name == null) {
            throw new MyException("el nombre no puede ser nulo o estar vacio");
        }

        if (username.isEmpty() || username == null) {
            throw new MyException("el nombre de usuario no puede ser nulo o estar vacio");
        }

        if (email.isEmpty() || email == null) {
            throw new MyException("El email no puede ser nulo o estar vacio");
        }

        if (cc.isEmpty() || cc == null) {
            throw new MyException("El número de identificación no puede ser nulo o estar vacio");
        }

        if (password.isEmpty() || password == null) {
            throw new MyException("La contraseña no puede ser nulo o estar vacia");
        }

        if (password2.isEmpty() || password2 == null) {
            throw new MyException("La confirmación de su contraseña no puede ser nulo o estar vacio");
        }

        if (idRoll.isEmpty() || idRoll == null) {
            throw new MyException("El roll no puede ser nulo o estar vacio");
        }

        if (idPhotos.isEmpty() || idPhotos == null) {
            throw new MyException("El usuario deberia registrarse con una foto");
        }
    }

  
}
