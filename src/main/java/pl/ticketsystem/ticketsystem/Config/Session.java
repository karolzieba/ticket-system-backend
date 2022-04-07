package pl.ticketsystem.ticketsystem.Config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Session {
    public String getSessionUserLogin()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userlogin="";
        if (principal instanceof AccountDetalis) {
            userlogin = ((AccountDetalis)principal).getUsername();
        } else {
            userlogin = principal.toString();
        }

        return userlogin;
    }

}
