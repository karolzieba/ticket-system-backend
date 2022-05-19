package pl.ticketsystem.ticketsystem.Agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/agency")
public class AgencyController {
    public final AgencyService agencyService;

    @Autowired
    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public List<Agency> getAgencies() {
        return agencyService.getAgencies();
    }

    @GetMapping(path="{agencyId}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public Agency getAgency(@PathVariable long agencyId) {
        return agencyService.getAgency(agencyId);
    }

    @PatchMapping(path="{idAgency}")
    @PreAuthorize("hasAnyRole('ROLE_AGENCY', 'ROLE_MODERATOR')")
    public void updateAgency(@PathVariable long idAgency, @RequestBody Agency agency) {
        agencyService.updateAgency(idAgency, agency);
    }

    @DeleteMapping(path="{idAgency}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void deleteAgency(@PathVariable long idAgency) {
        agencyService.deleteAgency(idAgency);
    }
}
