package crud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("crud")
public class SpringBootInit {
    public static void main (String [] args){
        SpringApplication.run(SpringBootInit.class, args);
    }
}