package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer;

/**
 * @author Chris on 01.12.2017
 */
public class PathTaverna {

    public static final String ADVENTURERS = "/taverna/adventurers";
    public static final String ADVENTURER_BY_NAME = ADVENTURERS + "/{name}";
    public static final String GROUPS = "/taverna/groups";
    public static final String GROUP_BY_ID = GROUPS + "/{id}";
    public static final String GROUP_MEMBERS_BY_GROUP_ID = GROUP_BY_ID + "/members";

}
