package app;

import app.configuration.AppConfigurator;
import org.apache.log4j.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;

/**
 * @author Christian G. on 17.11.2017
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public final static Logger logger = Logger.getLogger(new Object() { }.getClass().getEnclosingClass());

    public static String IP;

    public static void main(String[] args) throws UnknownHostException {

        logger.info("starting application");

        IP = java.net.InetAddress.getLocalHost().getHostAddress();

        logger.info("ip: " + java.net.InetAddress.getLocalHost().getHostAddress());

        //System.out.println(System.getenv("TEST_ENV"));

        AppConfigurator appConfig = new AppConfigurator();
        appConfig.configure();



    }

}
