package it.epicode.w7d5.service;


import it.epicode.w7d5.exception.NotFoundException;
import it.epicode.w7d5.model.Event;
import it.epicode.w7d5.model.EventRequest;
import it.epicode.w7d5.model.User;
import it.epicode.w7d5.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserService userService;


    public List<Event> getAllEventi(){
       return eventRepository.findAll();
    }

    public Event getEventById(int id){
       return eventRepository.findById(id).orElseThrow(()->new NotFoundException("User non trovato"));
    }

    public Event save(EventRequest eventRequest){

        Event event = new Event();

        event.setDescription(eventRequest.getDescription());
        event.setData(eventRequest.getDate());
        event.setLocation(eventRequest.getLocation());
        event.setTitle(eventRequest.getTitle());
        event.setMaximumNumberPartecipants(eventRequest.getMaximumNumberPartecipants());

        return  eventRepository.save(event);
    }

    public Event update(int id, EventRequest eventRequest){

        Event event = getEventById(id);
        event.setDescription(eventRequest.getDescription());
        event.setData(eventRequest.getDate());
        event.setLocation(eventRequest.getLocation());
        event.setTitle(eventRequest.getTitle());
        event.setMaximumNumberPartecipants(eventRequest.getMaximumNumberPartecipants());

       return  eventRepository.save(event);
    }

    public void delete(int id){
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

    public Event updateUser(int id, int eventId){

        User user = userService.userGetById(id);
        Event event = getEventById(eventId);

        if (event.getPartecipantList().contains(user)){
            throw  new RuntimeException(user.getName()+" ha già prenotato un posto in questo evento");
        }
        if (event.getMaximumNumberPartecipants()<=event.getPartecipantList().size()){
            throw  new RuntimeException("L'evento"+event.getTitle()+" ha raggiunto il numero massimo di partecipanti");
        }
         event.addUser(user);
         return eventRepository.save(event);
    }

}
