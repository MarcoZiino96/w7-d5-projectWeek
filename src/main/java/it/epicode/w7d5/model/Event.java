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

    private String title;

    private String description;

    private String location;

    private LocalDate data;

    private Integer maximumNumberPartecipants;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> partecipantList;

    public void addUser(User user){
        partecipantList.add(user);
    }
}
