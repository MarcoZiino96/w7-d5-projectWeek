package it.epicode.w7d5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence_event")
    private int id;
    private String titolo;

    private String description;

    private String location;

    private LocalDate data;

    private String place;

    private int numberPlacesAvailable;

    private int maximumNumberPartecipants;

    @ManyToMany
    @JoinTable(
            name = "users_bookings",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_event"))
    private List<User> partecipantlist;

    private boolean availability;

    public void addUser(User user){
        partecipantlist.add(user);
    }
}
