package rus.cheremisin.churchsong.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;

public class LifecycleTest {


    private LifecycleTest(){};
    public static LifecycleTest getLifecycleTest() {
        System.out.println("this is factory method");
        return new LifecycleTest();
    }

//    @PostConstruct
    public void postConstruct() {
        System.out.println("this is post-construct");
    }

//    @PreDestroy
    public void preDestroy() {
        System.out.println("this is pre-destroy");
    }

    public void working() {
        System.out.println("bean is in work");
    }
}
