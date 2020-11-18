package crud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@ComponentScan("crud")
@EntityScan("crud")
@EnableWebMvc
public class SpringBootInit {
    public static void main (String [] args){
        SpringApplication.run(SpringBootInit.class, args);
    }
}