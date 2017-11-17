package app.shell;

import app.shell.command.*;
import app.shell.command.exception.CommandNotExistsException;
import app.shell.command.exception.ParameterIsMissingException;
import app.shell.command.exception.ParameterTooManyException;
import app.shell.command.Register;
import app.shell.command.state.Context;
import app.shell.command.state.State;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Christian G. on 17.11.2017
 */
public class Main {

    public static void main(String[] args){

        Context context = new Context(State.NOT_LOGIN);

        CommandHandler handler = new CommandHandler(context);


        Scanner reader = new Scanner(System.in);

        // Initializte

        while (true){

            System.out.print(context.getPromptState());

            String in = reader.nextLine();

            handler.handleCommand(in);



        }

    }
}
