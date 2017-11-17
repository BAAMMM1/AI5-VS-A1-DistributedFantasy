package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.exception.ParameterIsMissingException;
import app.layerGraphicAndPresentation.shell.exception.ParameterSyntaxIncorrektException;
import app.layerGraphicAndPresentation.shell.exception.ParameterTooManyException;
import app.layerGraphicAndPresentation.shell.state.State;

import java.util.List;

/**
 * Diese Klasse definiert einen command line Befehl
 * @author Christian G. on 17.11.2017
 */
public interface Command {

    /**
     * Diese Methode gibt an wie das Command auf der console geschrieben werden muss, damit es vom CommandInterpreter als
     * Commando erkannt wird.
     *
     * @return Schreibweise des Commands
     */
    String getCommandName();

    /**
     * Diese Methode setzt die Parameter eines Commandos. Dabei muss überprüft werden, ob zu viele, zu wenige oder
     * falsche Parameter übergeben worden.
     *
     * @param param Parameter für das Commando
     * @throws ParameterTooManyException   falls zu viele Parameter übergeben worden
     * @throws ParameterIsMissingException falls zu wenige Parameter übergeben worden
     */
    void setParameter(List<String> param) throws ParameterTooManyException, ParameterIsMissingException, ParameterSyntaxIncorrektException;

    /**
     * Diese Methode überprüft ob das Commando zum aktuellen State der Application ausgeführt werden darf.
     *
     * @param state aktueller State der Application
     * @throws UnAcceptedStateException falls das Commando nicht zum aktuellen Status ausgeführt werden kann.
     *                                  <p>
     *                                  Diese Methode bleib leer, wenn das Commando zu jeder Zeit ausgeführt werden
     *                                  darf. Bsp. exit
     */
    void checkState(State state) throws UnAcceptedStateException;


    /**
     * Diese Methode führt das Commando aus und gibt den anschließenden State der Application zurück. Falls das Command
     * den State der Application nicht verändert, muss der State mit dem die Methode aufgerufen wurde zurückgegeben
     * werden.
     *
     * @return aktuellen State der Application
     */
    State execute(State state);

}
