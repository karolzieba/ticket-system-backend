package pl.ticketsystem.ticketsystem.Moderator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ModeratorService {
    private final ModeratorRepository moderatorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ModeratorService(ModeratorRepository moderatorRepository, PasswordEncoder passwordEncoder) {
        this.moderatorRepository = moderatorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void updateModerator(long idModerator, Moderator moderator) {
        if(moderatorRepository.existsById(idModerator)) {
            Moderator updatedModerator = moderatorRepository.findById(idModerator).orElseThrow(() -> new IllegalStateException("Moderator with this ID does not exist!"));

            if(!Objects.isNull(moderator.getAccount().getUsername())) {
                updatedModerator.getAccount().setUsername(moderator.getAccount().getUsername());
            }

            if(!Objects.isNull(moderator.getAccount().getEmailAccount())) {
                updatedModerator.getAccount().setEmailAccount(moderator.getAccount().getEmailAccount());
            }

            if(!Objects.isNull(moderator.getAccount().getPassword())) {
                updatedModerator.getAccount().setPassword(passwordEncoder.encode(moderator.getAccount().getPassword()));
            }

            if(!Objects.isNull(moderator.getUserNameModerator())) {
                updatedModerator.setUserNameModerator(moderator.getUserNameModerator());
            }

            moderatorRepository.save(updatedModerator);
        }
        else {
            throw new IllegalStateException("Moderator with this ID does not exist!");
        }
    }
}
