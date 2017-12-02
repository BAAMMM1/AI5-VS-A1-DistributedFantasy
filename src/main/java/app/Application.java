package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;

/**
 * @author Christian G. on 17.11.2017
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) throws UnknownHostException {

        // TODO - Spring Boot erst nach dem Login starten
        SpringApplication.run(Application.class, args);


        System.out.println(java.net.InetAddress.getLocalHost().getHostAddress().toString());
        System.out.print(System.getenv("TEST_ENV"));

        AppConfigurator appConfig = new AppConfigurator();
        appConfig.configure();



    }
}
