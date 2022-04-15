package pl.ticketsystem.ticketsystem.Config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Register.RoleUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class AccountDetalis implements UserDetails {

    private Account account;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleUser> roles = account.getRoles();
        List<SimpleGrantedAuthority> authorties = new ArrayList<>();

        for (RoleUser roleUser : roles)
        {
            authorties.add(new SimpleGrantedAuthority(roleUser.getNameRole()));
        }
        return authorties;
    }

    @Override
    public String getPassword() {
        if(account==null)
        {
            return null;
        }
        return account.getPasswordAccount();
    }

    @Override
    public String getUsername() {
        return account.getAccountLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
