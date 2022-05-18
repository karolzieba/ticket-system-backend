package pl.ticketsystem.ticketsystem.Moderator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moderator")
public class ModeratorController {
    public final ModeratorService moderatorService;

    @Autowired
    public ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PatchMapping(path="{idModerator}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void updateModerator(@PathVariable long idModerator, @RequestBody Moderator moderator) {
        moderatorService.updateModerator(idModerator, moderator);
    }
}
