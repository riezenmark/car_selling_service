package org.example.carsellingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Настройка шифрования паролей.
 */
@Configuration
public class EncryptionConfig {
    /**
     * Возвращает кодировщик паролей с заданной силой кодирования.
     * @return Кодировщик паролей.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
