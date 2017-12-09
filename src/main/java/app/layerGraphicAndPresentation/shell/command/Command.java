package app.layerGraphicAndPresentation.shell.command;


import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Christian G. on 18.11.2017
 */
public abstract class Command {

    private String commandName;
    private int numberOfParameter;
    private List<String> parameter;
    private InputInterpreter inputInterpreter;

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     * @param inputInterpreter
     */
    public Command(InputInterpreter inputInterpreter) {
        this.commandName = this.getClass().getSimpleName().toLowerCase();
        this.numberOfParameter = this.parameterSize();
        this.inputInterpreter = inputInterpreter;
        this.inputInterpreter.registerCommand(this);
    }

    /**
     * Diese Methode setzt die Parameter eines Commandos. Dabei muss überprüft werden, ob zu viele, zu wenige oder
     * falsche Parameter übergeben worden.
     *
     * @param param Parameter für das Commando
     * @throws ParameterIncorrectException falls Parameter incorrekt
     */
    private void setParam(List<String> param) throws ParameterIncorrectException {

        this.checkParam(param);
        this.parameter = param;
    }

    /**
     * Falls ein Befehl eine besondere Überprüfung der Parameter benötigt, kann diese Methode in der konkreten
     * Unterklasse überschrieben werden.
     *
     * @param param
     * @throws ParameterIncorrectException
     */
    public void checkParam(List<String> param) throws ParameterIncorrectException {

        if (param.size() != this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }

    }

    /**
     * Führt einen Command aus
     * Falls das Ausführen dieses Commands den State ändert, wird der neue State Wert im Context hinterlegt.
     *
     * @throws UnAcceptedStateException
     */
    public void execute(List<String> param) throws UnAcceptedStateException, ParameterIncorrectException, UnexpectedResponseCodeException, IOException, InterruptedException, NumberFormatException  {

        // TODO - doppel checkParam
        this.checkParam(param);
        this.setParam(param);

        this.checkState();
        State state = this.instruction();

        if (state != null) {
            Context.getInstance().setState(state);
        }
    }

    /**
     * Diese Methode überprüft ob das Commando zum aktuellen State der Application ausgeführt werden darf. Falls ein
     * Command nur zu einem bestimmten State(s) ausgeführt werden darf, muss diese Methode in der konkreten Klasse
     * überschrieben werden.
     *
     * @throws UnAcceptedStateException falls das Commando nicht zum aktuellen Status ausgeführt werden kann.
     */
    public void checkState() throws UnAcceptedStateException {

    }

    /**
     * Diese Methode führt das Commando aus und gibt den anschließenden State der Application zurück. Falls das Command
     * den State der Application nicht verändert, muss null zurückgegeben werden werden.
     *
     * @return aktuellen State der Application
     */
    abstract State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException;


    /**
     * Diese Methode gibt an wie das Executable auf der console geschrieben werden muss, damit es vom InputInterpreter
     * als Commando erkannt wird.
     *
     * @return Schreibweise des Commands
     */
    public String getCommandName() {
        return this.commandName;
    }

    public int getNumberOfParameter() {
        return numberOfParameter;
    }

    public List<String> getParameter() {
        return parameter;
    }

    public InputInterpreter getInputInterpreter() {
        return inputInterpreter;
    }


    /**
     * Jedes Command muss seine Parameteranzahl selbst definieren
     * @return
     */
    abstract int parameterSize();

    abstract String description();

}
