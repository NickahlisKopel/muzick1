package com.careerdevs.muzick1.repositories;

import com.careerdevs.muzick1.models.Listener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListenerRepository extends JpaRepository<Listener, Long> {
    List<Listener> findAllByAge(Integer age);

   // @Query("SELECT * FROM listener WHERE name LIKE '?1%'")
    //List<Listener> findAllByPartialName(String query);

}
