package kea.exercise.hogwarts3.repositories;

import kea.exercise.hogwarts3.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer> {
}
