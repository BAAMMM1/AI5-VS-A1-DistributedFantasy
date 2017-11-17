package app.shell;

import app.shell.command.*;
import app.shell.command.Exception.CommandNotExistsException;
import app.shell.command.Exception.ParameterIsMissingException;
import app.shell.command.Exception.ParameterTooManyParameterException;
import app.shell.command.Register;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Christian G. on 17.11.2017
 */
public class Main {

    public static void main(String[] args){

        Interpreter interpreter = new Interpreter();
        interpreter.registerCommands(Arrays.asList(new Help(), new Register(), new Exit()));

        Scanner reader = new Scanner(System.in);

        // Initializte

        while (true){
            System.out.print(">> DistributedFantasy >>: ");

            String in = reader.nextLine();

            try {
                Command command = interpreter.interpret(in);

                command.execute();

            } catch (CommandNotExistsException e) {
                System.out.println(e.getMessage());

            } catch (ParameterTooManyParameterException e) {
                System.out.println(e.getMessage());

            } catch (ParameterIsMissingException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
