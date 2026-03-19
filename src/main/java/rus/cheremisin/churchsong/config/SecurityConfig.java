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
public class SecurityConfig {
    private final CustomSuccessHandler customSuccessHandler;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CorsConfig corsConfig;

    @Autowired
    public SecurityConfig(CustomSuccessHandler customSuccessHandler, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, CorsConfig corsConfig) {
        this.customSuccessHandler = customSuccessHandler;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.corsConfig = corsConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->
                        csrf.disable())
                .cors(cors ->
                        cors.configurationSource(corsConfig.corsConfigurationSource()))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                HttpMethod.GET,
                                "/",
                                "http://localhost:5173/**",
                                "/home",
                                "/auth",
                                "/bands/**",
                                "/js/**",
                                "/auth/current-user",
                                "/error",
                                "/auth.html"
                        )
                        .permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/auth/local/register",
                                "/auth/telegram",
                                "/bands/**",
                                "/login",
                                "/logout")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginProcessingUrl("/login")
                                .successHandler((req, res, auth) -> {
                                    res.setContentType("application/json");
                                    res.getWriter().write("{\"status\":\"ok\"}");
                                })
                                .failureHandler((req, res, ex) -> {
                                    res.setContentType("application/json");
                                    res.setStatus(401);
                                    res.getWriter().write("{\"status\":\"error\",\"message\":\"Invalid credentials\"}");
                                })
                                .permitAll())
                .oauth2Login(oauth2 -> oauth2.loginPage("http://localhost:5173/auth")
                        .defaultSuccessUrl("http://localhost:5173/home")
//                        .loginProcessingUrl("/auth/oauth2")
                        .failureUrl("http://localhost:5173/auth?error=true")
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
