package pl.ticketsystem.ticketsystem.Agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/agency")
public class AgencyController {
    public final AgencyService agencyService;

    @Autowired
    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @PatchMapping(path="{idAgency}")
    @PreAuthorize("hasAnyRole('ROLE_AGENCY', 'ROLE_MODERATOR')")
    public void updateAgency(@PathVariable long idAgency, @RequestBody Agency agency) {
        agencyService.updateAgency(idAgency, agency);
    }
}
