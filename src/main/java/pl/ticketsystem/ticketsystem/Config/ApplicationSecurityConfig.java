package pl.ticketsystem.ticketsystem.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pl.ticketsystem.ticketsystem.Auth.OAuth.OAuthAccountDetailsService;
import pl.ticketsystem.ticketsystem.Auth.REST.AccountDetailsService;
import pl.ticketsystem.ticketsystem.Auth.REST.JsonObjectAuthenticationFilter;
import pl.ticketsystem.ticketsystem.Auth.REST.RestAuthenticationFailureHandler;
import pl.ticketsystem.ticketsystem.Auth.REST.RestAuthenticationSuccessHandler;
import pl.ticketsystem.ticketsystem.Role.Role;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private PasswordEncoder passwordEncoder;
    private AccountDetailsService accountDetailsService;
    private RestAuthenticationSuccessHandler authenticationSuccessHandler;
    private RestAuthenticationFailureHandler authenticationFailureHandler;
    private OAuthAccountDetailsService oAuthAccountDetailsService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, AccountDetailsService accountDetailsService, RestAuthenticationSuccessHandler authenticationSuccessHandler,
                          RestAuthenticationFailureHandler authenticationFailureHandler,
                                     OAuthAccountDetailsService oAuthAccountDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.accountDetailsService = accountDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.oAuthAccountDetailsService = oAuthAccountDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .ignoringAntMatchers("/login")
                .ignoringAntMatchers("/oauth2/authorization/facebook")
                .ignoringAntMatchers("/register/agency")
                .ignoringAntMatchers("/register/moderator")
                .ignoringAntMatchers("/register/client")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/oauth2/authorization/facebook").permitAll()
                .antMatchers("/register/agency").permitAll()
                .antMatchers("/register/moderator").permitAll()
                .antMatchers("/register/client").permitAll()
                .antMatchers("/api/event/**").permitAll()
                .antMatchers("/swagger-ui.html").hasRole(Role.MODERATOR.name())
                .antMatchers("/v2/api-docs").hasRole(Role.MODERATOR.name())
                .antMatchers("/webjars/**").hasRole(Role.MODERATOR.name())
                .antMatchers("/swagger-resources/**").hasRole(Role.MODERATOR.name())
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(oAuthAccountDetailsService)
                    .and()
                        .defaultSuccessUrl("http://localhost:3000/")
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("SESSION")
                    .logoutSuccessUrl("http://localhost:3000/")
                .and()
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "responseType", "Authorization", "X-XSRF-TOKEN", "XSRF-TOKEN"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(accountDetailsService);
        return provider;
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(super.authenticationManager());
        return filter;
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
