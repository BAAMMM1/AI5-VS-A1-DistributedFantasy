package app;

import app.layerGraphicAndPresentation.shell.Shell;
import app.layerLogicAndService.cmpBlackboard.service.BlackboardServiceImp;
import app.layerLogicAndService.cmpBlackboard.service.IBlackboardService;
import app.layerLogicAndService.cmpBlackboard.service.IListenerService;
import app.layerLogicAndService.cmpBlackboard.service.ListenerServiceImpl;
import app.layerLogicAndService.cmpHeroToHero.service.HeroToHeroService;
import app.layerLogicAndService.cmpHeroToHero.service.IHeroToHeroService;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerLogicAndService.cmpQuest.service.QuestServiceImpl;
import app.layerLogicAndService.cmpTaverna.service.ITavernaService;
import app.layerLogicAndService.cmpTaverna.service.TavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.BlackboardConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.IBlackboardConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer.HeroToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer.IHeroToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.IQuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.QuestConsumerImpl;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer.ITavernaConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer.TavernaConsumer;

/**
 * @author Chris on 24.11.2017
 */
public class AppConfigurator {

    private static final int WELL_KNOWN_PORT = 24000;

    private IBlackboardConsumer blackboardConsumer;
    private IBlackboardService blackboardService;

    private IQuestConsumer questConsumer;
    private IQuestService questService;

    private ITavernaConsumer tavernaConsumer;
    private ITavernaService tavernaService;

    private IHeroToHeroConsumer heroToHeroConsumer;
    private IHeroToHeroService heroToHeroService;

    private IListenerService listener;
    private Shell shell;

    /**
     * Dependency-Injection hier
     */
    public AppConfigurator() {

        this.listener = new ListenerServiceImpl(WELL_KNOWN_PORT);

        blackboardConsumer = new BlackboardConsumer();
        blackboardService = new BlackboardServiceImp(blackboardConsumer);

        questConsumer = new QuestConsumerImpl();
        questService = new QuestServiceImpl(questConsumer);

        tavernaConsumer = new TavernaConsumer();
        tavernaService = new TavernaService(tavernaConsumer);

        this.heroToHeroConsumer = new HeroToHeroConsumer();
        this.heroToHeroService = new HeroToHeroService(heroToHeroConsumer);

        this.shell = new Shell(blackboardService, questService, tavernaService, heroToHeroService);
    }

    public void configure() {
        System.out.println("configure Application");

        System.out.println("starting listener thread");
        this.listener.start();

        System.out.println("starting shell");
        this.shell.start();

    }


}
