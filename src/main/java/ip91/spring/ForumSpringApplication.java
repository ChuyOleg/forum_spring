package ip91.spring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Forum Ip-91 API", version = "1.0.0"))
public class ForumSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumSpringApplication.class, args);
    }

}
