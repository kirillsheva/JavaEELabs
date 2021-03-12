package library;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
public class RealMadrid implements Team,InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        printName();
    }

    @Override
    public void printName() {
        System.out.println("Real Madrid");
    }
}

