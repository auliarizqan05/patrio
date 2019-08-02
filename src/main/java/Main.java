import co.id.gooddoctor.patrio.service.ProcessExtractRSS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "co.id.gooddoctor.patrio")
@EnableJpaRepositories(basePackages = "co.id.gooddoctor.patrio.dao")
@EntityScan(basePackages = "co.id.gooddoctor.patrio.entity")
@EnableAutoConfiguration
public class Main {

    public static void main(String args[]){

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
//        context.getBean(ProcessExtractRSS.class).extractRSS();
    }

}
