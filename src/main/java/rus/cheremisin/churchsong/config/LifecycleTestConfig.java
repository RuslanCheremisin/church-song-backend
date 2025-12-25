package rus.cheremisin.churchsong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rus.cheremisin.churchsong.util.LifecycleTest;

@Configuration
public class LifecycleTestConfig {
    @Bean
    public LifecycleTest lifecycleTest() {
        return LifecycleTest.getLifecycleTest();
    }
}
