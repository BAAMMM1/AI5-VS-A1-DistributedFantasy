package app.layerLogicAndService.cmpHeroToHero.service;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpHero.entity.Hiring;
import app.layerLogicAndService.cmpHero.entity.Message;
import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerLogicAndService.cmpQuest.entity.Quest;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer.IHeroToHeroConsumer;

/**
 * @author Chris on 02.12.2017
 */
public class HeroToHeroService implements IHeroToHeroService {

    IHeroToHeroConsumer heroToHeroConsumer;

    ITavernaService tavernaService;

    IQuestService questService;

    public HeroToHeroService(IHeroToHeroConsumer heroToHeroConsumer, ITavernaService tavernaService, IQuestService questService) {
        this.heroToHeroConsumer = heroToHeroConsumer;
        this.tavernaService = tavernaService;
        this.questService = questService;
    }

    @Override
    public String invite(String heroName, int groupId, int questId, String messageToHero) throws ErrorCodeException {

        // TODO - Benutzer über invite benachrichten

        Adventurer adventurer = this.tavernaService.getAdventure(heroName);

        // TODO - Wenn Grouß nicht da, dann besser response als 404 - Not Found
        Group group = this.tavernaService.getGroup(groupId);

        Quest quest = this.questService.getQuest(questId);

        String heroServiceUrl = adventurer.getUrl();

        if(!heroServiceUrl.substring(0,7).equals("http://")){
            heroServiceUrl = "http://" + heroServiceUrl;
        }

        System.out.println(heroServiceUrl);

        Service heroService = this.heroToHeroConsumer.getHeroService(heroServiceUrl);

        String heroHiringUrl = heroService.getHirings();

        if(!heroHiringUrl.substring(0,7).equals("http://")){
            heroHiringUrl = "http://" + heroHiringUrl;
        }

        // TODO - Ist das Hiring hier mit den richtigen Daten befüllt?
        Hiring hiring = new Hiring(group.get_links().getSelf(), quest.getName(),  messageToHero);

        return this.heroToHeroConsumer.hiringHero(hiring, heroHiringUrl);
    }

    @Override
    public void sendMessage(String adventurer, String string) throws ErrorCodeException {

        // TODO - Benutzer über Message income benachrichtigen

        Adventurer adven = this.tavernaService.getAdventure(adventurer);

        String heroServiceUrl = adven.getUrl();

        if(!heroServiceUrl.substring(0,7).equals("http://")){
            heroServiceUrl = "http://" + heroServiceUrl;
        }

        Service heroService = this.heroToHeroConsumer.getHeroService(heroServiceUrl);

        String heroMessageUrl = heroService.getMessages();

        if(!heroMessageUrl.substring(0,7).equals("http://")){
            heroMessageUrl = "http://" + heroMessageUrl;
        }

        // TODO - User über fehlgeschlagene Zustellung informieren

        // TODO - User übererfolgreiche zustellung informieren
        Message message = new Message(string, "", "message");

        this.heroToHeroConsumer.sendMessage(message, heroMessageUrl);
    }

    @Override
    public void sendAssignment(String adventurer, String message) throws ErrorCodeException {

    }


}
