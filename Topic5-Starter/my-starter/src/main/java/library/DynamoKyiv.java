package library;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DynamoKyiv implements Team, InitializingBean {


    @Override
    public void printName() {
        System.out.println("Динамо Київ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        printName();
    }
}
