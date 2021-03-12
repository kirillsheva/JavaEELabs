package client;

import library.RealMadrid;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;



    @Component
    @ConditionalOnBean(RealMadrid.class)
    public class Barcelona implements InitializingBean {


        @Override
        public void afterPropertiesSet() throws Exception {

            System.out.println("Barcelona");

        }
    }


