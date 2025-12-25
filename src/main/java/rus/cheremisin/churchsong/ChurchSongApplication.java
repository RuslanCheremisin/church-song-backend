package rus.cheremisin.churchsong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rus.cheremisin.churchsong.util.LifecycleTest;
import rus.cheremisin.churchsong.util.impl.SpringContextHolder;

@SpringBootApplication
public class ChurchSongApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChurchSongApplication.class, args);


//        SpringContextHolder context = new SpringContextHolder();
//        LifecycleTest test = context.getContext().getBean(LifecycleTest.class);
//        test.working();
    }

}
