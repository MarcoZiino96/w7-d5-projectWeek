package it.epicode.w7d5.security;

import it.epicode.w7d5.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityChain {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/auth/**").permitAll());

        httpSecurity.authorizeHttpRequests(request-> request.requestMatchers("/event").hasAuthority(Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request-> request.requestMatchers("/event/search/**").hasAuthority(Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request-> request.requestMatchers("/event/create").hasAuthority(Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request-> request.requestMatchers("/event/edit/**").hasAuthority(Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request-> request.requestMatchers("/event/delete/**").hasAuthority(Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request-> request.requestMatchers("/event/addUser/**").hasAuthority(Role.USER.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").denyAll());

        return httpSecurity.build();
    }
}
