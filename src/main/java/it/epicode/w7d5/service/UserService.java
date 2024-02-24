package it.epicode.w7d5.service;

import it.epicode.w7d5.exception.NotFoundException;
import it.epicode.w7d5.model.Event;
import it.epicode.w7d5.model.Role;
import it.epicode.w7d5.model.User;
import it.epicode.w7d5.model.UserRequest;
import it.epicode.w7d5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public User userGetById(int id){
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User non trovato"));
    }
    public User save (UserRequest userRequest){

        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER);
        return  userRepository.save(user);
    }
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("Email non trovato"));
    }

    public User update(String email, UserRequest userRequest){

        User user = getUserByEmail(email);
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        return  userRepository.save(user);
    }

    public void delete(String email){
        User user = getUserByEmail(email);
        userRepository.delete(user);
    }

    public List<Event> bookedEventByUser(int id){
       return userRepository.bookedEventByUser(id);
    }
}
