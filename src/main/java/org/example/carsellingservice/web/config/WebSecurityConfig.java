package org.example.carsellingservice.web.config;

import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.repository.UserDetailsRepository;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers(
                        "/", "/login**", "/js/**", "/error**",
                        "/models**", "/cars**", "/img/**", "/favicon.ico"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepository repository) {
        return map -> {
            String id = (String) map.get("sub");
            User user = repository.findById(id).orElseGet(() -> {
                User newUser = new User();

                newUser.setId(id);
                newUser.setName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setGender((String) map.get("gender"));
                newUser.setLocale((String) map.get("locale"));
                newUser.setUserpic((String) map.get("picture"));

                return newUser;
            });
            user.setLastVisit(LocalDateTime.now());
            return repository.save(user);
        };
    }
}
