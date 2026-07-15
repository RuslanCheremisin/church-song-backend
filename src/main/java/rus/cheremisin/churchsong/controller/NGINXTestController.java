package rus.cheremisin.churchsong.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nginx")
public class NGINXTestController {
    private final Environment environment;

    public NGINXTestController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/instance")
    public String instance() {
        return environment.getProperty("HOSTNAME", "local");
    }
}
