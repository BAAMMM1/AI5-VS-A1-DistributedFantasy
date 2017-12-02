package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;

/**
 * @author Christian G. on 17.11.2017
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws UnknownHostException {

        //SpringApplication.run(Application.class, args);

        System.out.println(java.net.InetAddress.getLocalHost().getHostAddress().toString());

        AppConfigurator appConfig = new AppConfigurator();
        appConfig.configure();



    }
}
