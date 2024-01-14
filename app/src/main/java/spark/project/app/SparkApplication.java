/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package spark.project.app;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spark.project.app.repository.ActorRepository;


@SpringBootApplication
@RestController
@RequestMapping("v1/api/spark")
@RequiredArgsConstructor
@EntityScan(basePackages = "com.spark.domainmodel.model")
public class SparkApplication {
    private final ActorRepository actorRepository;
    public static void main(String[] args) {
        SpringApplication.run(SparkApplication.class);
    }

    @GetMapping
    public String home() {
        return "home page";
    }
    @Bean
    public ApplicationRunner runner(){
        return args -> {
            actorRepository.findAll().forEach(System.out::println);
        };
    }
}