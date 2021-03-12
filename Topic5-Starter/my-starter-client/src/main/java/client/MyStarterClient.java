package client;

import library.DynamoKyiv;
import library.RealMadrid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MyStarterClient {

    public static void main(String[] args){
     SpringApplication.run(MyStarterClient.class,args);
    }

    @Bean
    @ConditionalOnBean(DynamoKyiv.class)
    public void scoreDyn(){
        System.out.println("Dynamo Kyiv scored a goal");
    }
    @Bean
    @ConditionalOnMissingBean(RealMadrid.class)
    public void scoreReal(){
        System.out.println("Real Madrid scored a goal");
    }
}
