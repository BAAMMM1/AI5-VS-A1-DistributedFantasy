package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Help extends Command {

    InputInterpreter inputInterpreter;

    private static final int PARAMETER_SIZE = 0;


    public Help(InputInterpreter inputInterpreter) {
        this.inputInterpreter = inputInterpreter;
    }


    @Override
    State instruction() {

        System.out.println("");
        System.out.println("# command ----------- # parameter ------------------- # description --------------------------------------");

        List<String> stringList = new ArrayList<String>();

        for (Command command : this.inputInterpreter.getRegisterCommands()) {
            stringList.add(command.description());
        }


        stringList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (String description : stringList) {
            System.out.println(description);
        }

        System.out.println("");

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -help\t\t\t\t\t\t\tdisplays all possible commands";
    }


}
