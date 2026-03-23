package rus.cheremisin.churchsong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.http.HttpHeaders;
@Component
public class VimeoConfig {
    @Value("${vimeo.access-token}")
    private String accessToken;

    @Value("${vimeo.base-url}")
    private String baseUrl;

    @Bean
    public WebClient vimeoWebClient() {
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer "+accessToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }
}
