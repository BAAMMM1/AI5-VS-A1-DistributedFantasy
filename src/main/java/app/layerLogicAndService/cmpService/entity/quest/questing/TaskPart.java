package app.layerLogicAndService.cmpService.entity.quest.questing;

import java.util.List;

/**
 * @author Chris on 26.11.2017
 */
public class TaskPart {

    private String deliverUri;
    private List<Step> stepList;

    public TaskPart(String deliverUri, List<Step> stepList) {
        this.deliverUri = deliverUri;
        this.stepList = stepList;
    }

    public String getDeliverUri() {
        return deliverUri;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    @Override
    public String toString() {
        return "TaskPart{" +
                "\ndeliverUri='" + deliverUri + '\'' +
                ", \nstepList=" + stepList +
                '}';
    }

    // TODO - getTokens
}
