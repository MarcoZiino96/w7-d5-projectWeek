package it.epicode.w7d5.repository;


import it.epicode.w7d5.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
