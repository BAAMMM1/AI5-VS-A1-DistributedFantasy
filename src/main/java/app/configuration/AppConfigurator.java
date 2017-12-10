package app.configuration;

import app.Application;
import app.layerGraphicAndPresentation.shell.Shell;
import app.layerLogicAndService.cmpService.service.blackboard.IListenerService;
import app.layerLogicAndService.cmpService.service.blackboard.ListenerServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Chris on 24.11.2017
 */
@ComponentScan
public class AppConfigurator {

    private static final int WELL_KNOWN_PORT = 24000;

    private IListenerService listener;

    private Shell shell;

    /**
     * Dependency-Injection hier
     */
    public AppConfigurator() {

        this.listener = new ListenerServiceImpl(WELL_KNOWN_PORT);

        this.shell = new Shell();
    }

    public void configure() {
        System.out.println("configure Application");

        System.out.println("starting listener thread");
        this.listener.start();

        System.out.println("starting spring boot");
        SpringApplication.run(Application.class);

        System.out.println("starting shell");
        this.shell.start();



    }


}
