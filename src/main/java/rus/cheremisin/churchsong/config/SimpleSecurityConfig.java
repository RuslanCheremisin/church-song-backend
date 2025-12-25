package rus.cheremisin.churchsong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SimpleSecurityConfig {
    private final CustomSuccessHandler customSuccessHandler;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SimpleSecurityConfig(CustomSuccessHandler customSuccessHandler, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.customSuccessHandler = customSuccessHandler;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                HttpMethod.GET,
                                "/",
                                "/home",
                                "/auth",
                                "/index.html",
                                "/js/**",
                                "/auth/current-user",
                                "/error",
                                "/auth.html"
                        )
                        .permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/auth/oauth2", //удали при мне
                                "/auth/local/register",
                                "/login",
                                "/logout")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/auth.html")
                                .loginProcessingUrl("/login")
                                .successHandler(customSuccessHandler)
                                .permitAll())
                .oauth2Login(oauth2 -> oauth2.loginPage("/auth")
                        .defaultSuccessUrl("/home")
//                        .loginProcessingUrl("/auth/oauth2")
                        .failureUrl("/auth?error=true")
                )
                .userDetailsService(userDetailsService)
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
