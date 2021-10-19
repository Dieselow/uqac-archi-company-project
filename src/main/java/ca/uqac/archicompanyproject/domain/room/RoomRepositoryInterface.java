package ca.uqac.archicompanyproject.domain.room;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomRepositoryInterface extends CrudRepository<Room, Integer> {
    Optional<Room> findByName(String name);
}
