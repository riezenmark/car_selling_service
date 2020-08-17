package org.example.carsellingservice.config;

import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //todo static
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/**", "/api/**/**", "/static/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository repository) {
        return map -> {
            String id = (String) map.get("sub");
            User user = repository.findById(id).orElseGet(() ->
                    User.builder()
                            .id(id)
                            .name((String) map.get("name"))
                            .email((String) map.get("email"))
                            .gender((String) map.get("gender"))
                            .locale((String) map.get("locale"))
                            .userpic((String) map.get("picture"))
                            .build()
            );
            user.setLastVisit(LocalDateTime.now());
            return repository.save(user);
        };
    }
}
