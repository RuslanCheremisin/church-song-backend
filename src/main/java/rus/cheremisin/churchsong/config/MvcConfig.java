package rus.cheremisin.churchsong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("forward:/index.html");
        registry.addViewController("/auth").setViewName("forward:/auth.html");
        registry.addViewController("/bandspage").setViewName("forward:/bands.html");
//        registry.addViewController("/logout").setViewName("forward:/logout.html");
    }
}
