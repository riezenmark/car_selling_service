package org.example.carsellingservice.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Настройка ModelViewController'а.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * Перенаправляет пользователя на главную страницу приложения в случае, если он не авторизован,
     * не имеет прав на просмотр страницы или пытается зайти на несуществующую страницу.
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
            container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/"));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/"));
        };
    }
}
