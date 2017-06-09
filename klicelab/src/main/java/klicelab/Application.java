package klicelab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by hasee on 2017/4/23.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
