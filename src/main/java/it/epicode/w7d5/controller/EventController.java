package it.epicode.w7d5.controller;


import it.epicode.w7d5.exception.BadRequestException;
import it.epicode.w7d5.exception.CustomResponse;
import it.epicode.w7d5.model.Event;
import it.epicode.w7d5.model.EventRequest;
import it.epicode.w7d5.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    EventService eventService;


    @GetMapping("/event")
    public ResponseEntity<CustomResponse> getAllEvent(){
        return CustomResponse.success(HttpStatus.OK.toString(), eventService.getAllEventi(), HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<CustomResponse>getEvent(@PathVariable int id){
        return CustomResponse.success(HttpStatus.OK.toString(), eventService.getEventById(id), HttpStatus.OK);
    }

    @PostMapping("/event/create")
    public ResponseEntity<CustomResponse> saveEvent(@RequestBody @Validated EventRequest eventRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
        return  CustomResponse.success(HttpStatus.OK.toString(), eventService.save(eventRequest), HttpStatus.OK);
    }

    @PutMapping("/event/edit")
    public ResponseEntity<CustomResponse> updateEvent(@PathVariable int id,@RequestBody @Validated EventRequest eventRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
        return  CustomResponse.success(HttpStatus.OK.toString(), eventService.update(id, eventRequest), HttpStatus.OK);
    }

    @DeleteMapping("event/delete")
    public ResponseEntity<CustomResponse> deleteEvent(@PathVariable int id){
        eventService.delete(id);
        return  CustomResponse.emptyResponse("Evento con id "+id+" è stata cancellato", HttpStatus.OK);
    }

    @PatchMapping("/event/addUser/{id}")
    public ResponseEntity<CustomResponse> addUserEvent(@PathVariable int id, @RequestParam("idDipendente") int idDipendente){

        Event event = eventService.updateUser(id, idDipendente);

        return CustomResponse.success(HttpStatus.OK.toString(), event, HttpStatus.OK);
    }
}
