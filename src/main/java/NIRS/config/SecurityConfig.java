package NIRS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/register", "/login", "/css/**", "/catalogOfService-user", "/cars-user").permitAll() //Разрешает доступ к этим URL без аутентификации
                        .requestMatchers("/admin/**").hasRole("ADMIN") //Требует роль "ADMIN" для доступа к URL, начинающимся с "/admin/".
                        .anyRequest().authenticated() //Требует аутентификацию для всех остальных запросов.
                )
                .formLogin((form) -> form
                        .loginPage("/login") //Устанавливает страницу входа
                        .defaultSuccessUrl("/", true) //Устанавливает URL для перенаправления после успешного входа.
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

