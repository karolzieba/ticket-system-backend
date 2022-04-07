package pl.ticketsystem.ticketsystem.Moderator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
    boolean existsByUserNameModerator(String name);
}
