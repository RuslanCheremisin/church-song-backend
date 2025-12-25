package rus.cheremisin.churchsong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
    private final CustomSuccessHandler customSuccessHandler;

//    @Autowired
    public SecurityConfig(CustomSuccessHandler customSuccessHandler) {
        this.customSuccessHandler = customSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        return http
//                .csrf(csrf ->
//                        csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/",
                                        "/auth",
                                        "/auth.html",
                                        "/home",
                                        "/index.html",
                                        "/songs/**",
                                        "/bands/**",
                                        "/blog/**",
                                        "/videos/**",
                                        "/images/**",
                                        "/css/**",
                                        "/js/**",
                                        "/current-user",
                                        "/error"

                                )
                                .permitAll()
                                .requestMatchers(HttpMethod.POST,
                                        "/auth/local/register",
                                        "/logout",
                                        "/login"
                                ).permitAll()
                                .requestMatchers(HttpMethod.POST,
                                        "/blog/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,
                                        "/songs/**",
                                        "/songs/**",
                                        "/bands/**")
                                .hasAnyRole("ADMIN", "LEADER")
                                .requestMatchers(HttpMethod.PUT,
                                        "/blog/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,
                                        "/songs/**",
                                        "/songs/**",
                                        "/bands/**")
                                .hasAnyRole("ADMIN", "LEADER")
                                .requestMatchers(HttpMethod.DELETE,
                                        "/songs/**",
                                        "/bands/**",
                                        "/blog/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,
                                        "/songs/**",
                                        "/bands/**")
                                .hasRole("LEADER")

                                .anyRequest().authenticated()
                )
                .formLogin(form -> form.permitAll()
//                                .loginPage("/auth.html")
                                .loginPage("/index.html")
                                .successHandler(customSuccessHandler)
//                        .loginProcessingUrl("/auth")
//                                .defaultSuccessUrl("/auth", true)
                                .defaultSuccessUrl("/home", true)
                )
//                .oauth2Login(oauth2 -> oauth2.loginPage("/auth")
                .oauth2Login(oauth2 -> oauth2.loginPage("/home")
                        .defaultSuccessUrl("/home")
                        .failureUrl("/auth?error=true")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/index.html")
                        .permitAll())
                .build();
    }

}
