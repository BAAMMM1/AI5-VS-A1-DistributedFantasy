package app;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;

/**
 * @author Chris on 01.12.2017
 */
public class API {

    public static final String WELL_KNOWN_SERVER = Blackboard.URL;

    public static final String BLACKBOARD = WELL_KNOWN_SERVER + "/blackboard";
    public static final String BLACKBOARD_QUESTS = BLACKBOARD + "/quests";
    public static final String BLACKBOARD_TASKS = BLACKBOARD + "/tasks";

    public static final String MAP = WELL_KNOWN_SERVER + "/map";
    public static final String LOGIN = WELL_KNOWN_SERVER + "/login";
    public static final String WHOAMI = WELL_KNOWN_SERVER + "/whoami";
    public static final String USERS = WELL_KNOWN_SERVER + "/users";

    public static final String TAVERNA = WELL_KNOWN_SERVER + "/taverna";
    public static final String TAVERNA_ADVENTURERS = TAVERNA + "/adventurers";
    public static final String TAVERNA_GROUPS = TAVERNA + "/groups";



}
