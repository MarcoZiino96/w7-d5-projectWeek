package it.epicode.w7d5.repository;

import it.epicode.w7d5.model.Event;
import it.epicode.w7d5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findByEmail(String mail);

    @Query("SELECT b FROM User u JOIN u.bookedEvents b WHERE u.id= :id")
   List<Event> bookedEventByUser(int id);
}
