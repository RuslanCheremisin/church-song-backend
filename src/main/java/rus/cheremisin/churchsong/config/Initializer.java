package rus.cheremisin.churchsong.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rus.cheremisin.churchsong.DTO.UserCreateRequest;
import rus.cheremisin.churchsong.service.UserService;
import rus.cheremisin.churchsong.util.Utils;

//@Component
//@RequiredArgsConstructor
public class Initializer {
//    @Autowired
    private UserService service;
    @PostConstruct
    public void initializeUsersForTests() {
        for (int i = 0; i < 10; i++) {
            service.addUser(new UserCreateRequest(
                    Utils.getRandomDoubleToString(),
                    Utils.getRandomDoubleToString(),
                    null,
                    Utils.getRandomDoubleToString(),
                    Utils.getRandomDoubleToString(),
                    "password"
            ));
        }
    }
}
