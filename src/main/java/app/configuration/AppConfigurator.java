package app.configuration;

import app.Application;
import app.layerGraphicAndPresentation.shell.Shell;
import app.layerLogicAndService.cmpService.service.blackboard.BlackboardServiceImp;
import app.layerLogicAndService.cmpService.service.blackboard.IBlackboardService;
import app.layerLogicAndService.cmpService.service.blackboard.IListenerService;
import app.layerLogicAndService.cmpService.service.blackboard.ListenerServiceImpl;
import app.layerLogicAndService.cmpService.service.hero.HeroService;
import app.layerLogicAndService.cmpService.service.hero.IHeroService;
import app.layerLogicAndService.cmpService.service.heroToHero.HeroToHeroService;
import app.layerLogicAndService.cmpService.service.heroToHero.IHeroToHeroService;
import app.layerLogicAndService.cmpService.service.quest.IQuestService;
import app.layerLogicAndService.cmpService.service.quest.QuestServiceImpl;
import app.layerLogicAndService.cmpService.service.taverna.ITavernaService;
import app.layerLogicAndService.cmpService.service.taverna.TavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.BlackboardConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IBlackboardConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.HeroToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IHeroToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IQuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.QuestConsumerImpl;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.ITavernaConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.TavernaConsumer;
import org.springframework.boot.SpringApplication;

/**
 * @author Chris on 24.11.2017
 */
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
