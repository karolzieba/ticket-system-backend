package pl.ticketsystem.ticketsystem.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthUtilController {
    private final AuthUtilService authUtilService;

    @Autowired
    public AuthUtilController(AuthUtilService authUtilService) {
        this.authUtilService = authUtilService;
    }

    @PostMapping("/socialDeauthorize")
    public void socialDeauthorize() {
        authUtilService.socialDeauthorize();
    }

    @GetMapping("/getInfo")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_CLIENT_FACEBOOK', 'ROLE_MODERATOR', 'ROLE_AGENCY')")
    public Map<String, String> getInfo() {
        return authUtilService.getInfo();
    }
}
