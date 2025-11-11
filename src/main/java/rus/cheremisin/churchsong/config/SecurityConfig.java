package rus.cheremisin.churchsong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        "/",
                                        "/auth",
                                        "/home",
                                        "/auth/local/register",
                                        "/songs/**",
                                        "/bands/**",
                                        "/blog/**",
                                        "/videos/**",
                                        "/images/**",
                                        "/css/**",
                                        "/js/**",
                                        "/error"

                                )
                                .permitAll()
//                                .anyRequest().authenticated()
                )
                .formLogin(form -> form.permitAll()
                        .loginPage("/auth.html")
//                        .loginProcessingUrl("/auth")
                        .defaultSuccessUrl("/index.html", true)
                )
                .oauth2Login(oauth2 -> oauth2.loginPage("/auth")
                        .defaultSuccessUrl("/home")
                        .failureUrl("/auth?error=true")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/index.html")
                        .permitAll())
//                .csrf(csrf -> csrf.disable())
                .build();
    }

}
