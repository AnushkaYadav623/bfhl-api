package in.acropolis.bfhl;

import in.acropolis.bfhl.config.BfhlUserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BfhlUserProperties.class)
public class BfhlApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BfhlApiApplication.class, args);
    }
}
