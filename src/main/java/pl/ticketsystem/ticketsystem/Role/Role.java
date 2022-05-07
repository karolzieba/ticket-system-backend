package pl.ticketsystem.ticketsystem.Role;

import static pl.ticketsystem.ticketsystem.Role.Authority.*;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    CLIENT(Sets.newHashSet(EVENT_SHOW, TICKET_ADD, TICKET_SHOW, ACCOUNT_SHOW_SINGLE, ACCOUNT_UPDATE_USER)),
    CLIENT_FACEBOOK(Sets.newHashSet(EVENT_SHOW, TICKET_ADD, TICKET_SHOW, ACCOUNT_SHOW_SINGLE, ACCOUNT_UPDATE_USER)),
    AGENCY(Sets.newHashSet(EVENT_SHOW, EVENT_ADD, EVENT_UPDATE)),
    MODERATOR(Sets.newHashSet(EVENT_ADD, EVENT_SHOW, EVENT_DELETE, EVENT_ACCEPT, EVENT_UPDATE,
            ACCOUNT_UPDATE_MODERATOR, ACCOUNT_SHOW_SINGLE, ACCOUNT_SHOW_MANY, ACCOUNT_DELETE,
            PAYMENT_SHOW_MANY, PAYMENT_UPDATE, PAYMENT_DELETE, TICKET_ADD, TICKET_SHOW));

    private Set<Authority> authoritySet;

    Role(Set<Authority> authoritySet) {
        this.authoritySet = authoritySet;
    }

    public Set<Authority> getAuthoritySet() {
        return authoritySet;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getAuthoritySet()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}
