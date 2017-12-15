package app.configuration;

import app.Application;
import app.layerGraphicAndPresentation.shell.Shell;
import app.layerLogicAndService.cmpService.service.IListenerService;
import app.layerLogicAndService.cmpService.service.ListenerService;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Chris on 24.11.2017
 */
@ComponentScan
public class AppConfigurator {

    public final static Logger log = Logger.getLogger(new Object() { }.getClass().getEnclosingClass());

    private static final int WELL_KNOWN_PORT = 24000;

    private IListenerService listener;

    private Shell shell;

    /**
     * Dependency-Injection hier
     */
    public AppConfigurator() {

        this.listener = new ListenerService(WELL_KNOWN_PORT);

        this.shell = new Shell();
    }

    public void configure() {
        log.info("configure Application");

        log.info("starting listener thread");
        this.listener.start();

        log.info("starting spring boot");
        SpringApplication.run(Application.class);

        log.info("starting shell");
        this.shell.start();



    }


}
