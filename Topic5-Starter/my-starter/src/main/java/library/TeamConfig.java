package library;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TeamConfig.class)
public class TeamConfig {

    @Bean
    @ConditionalOnProperty(value = "dynamo.fc", havingValue = "fcdk")
    Team dynamo() {
        return new DynamoKyiv();
    }

    @Bean
    @ConditionalOnProperty(value = "real.fc", havingValue = "fcrm")
    Team real() {
        return new RealMadrid();
    }
}
