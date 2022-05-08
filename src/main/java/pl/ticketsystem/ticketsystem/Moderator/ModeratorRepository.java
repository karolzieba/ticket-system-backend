package pl.ticketsystem.ticketsystem.Moderator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
    boolean existsByUserNameModerator(String name);
    Optional<Moderator> getModeratorByAccount_IdAccount(long idAccount);
}
