package rus.cheremisin.churchsong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChurchSongApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChurchSongApplication.class, args);

//        SpringContextHolder context = new SpringContextHolder();
//        LifecycleTest test = context.getContext().getBean(LifecycleTest.class);
//        test.working();
    }

}
