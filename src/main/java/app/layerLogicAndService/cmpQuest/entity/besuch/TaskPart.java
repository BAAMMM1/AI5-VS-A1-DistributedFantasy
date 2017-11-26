package app.layerLogicAndService.cmpQuest.entity.besuch;

import java.util.List;

/**
 * @author Chris on 26.11.2017
 */
public class TaskPart {

    private String uri;
    private List<Step> stepList;

    public TaskPart(String uri, List<Step> stepList) {
        this.uri = uri;
        this.stepList = stepList;
    }

    public String getUri() {
        return uri;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    // TODO - getTokens
}
