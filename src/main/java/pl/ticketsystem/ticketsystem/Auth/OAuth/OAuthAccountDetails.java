package pl.ticketsystem.ticketsystem.Auth.OAuth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class OAuthAccountDetails implements OAuth2User, Serializable {
    private final Collection<? extends GrantedAuthority> authorities;
    private final Map<String, Object> attributes;
    private final String name;

    public OAuthAccountDetails(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String name) {
        this.authorities = authorities;
        this.attributes = attributes;
        this.name = name;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }

    public String getEmail() { return (String)attributes.get("email"); }

    public String getIdSocial() { return (String)attributes.get("id"); }
}
