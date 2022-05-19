package pl.ticketsystem.ticketsystem.Moderator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ticketsystem.ticketsystem.Client.Client;

import java.util.List;

@RestController
@RequestMapping("/api/moderator")
public class ModeratorController {
    public final ModeratorService moderatorService;

    @Autowired
    public ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public List<Moderator> getModerators() {
        return moderatorService.getModerators();
    }

    @GetMapping(path="{moderatorId}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public Moderator getModerator(@PathVariable long moderatorId) {
        return moderatorService.getModerator(moderatorId);
    }

    @PatchMapping(path="{idModerator}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void updateModerator(@PathVariable long idModerator, @RequestBody Moderator moderator) {
        moderatorService.updateModerator(idModerator, moderator);
    }

    @DeleteMapping(path="{idModerator}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void deleteModerator(@PathVariable long idModerator) {
        moderatorService.deleteModerator(idModerator);
    }
}
