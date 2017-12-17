package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.context.Context;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Chris on 24.11.2017
 */
public class Shell {

    private Scanner scanner;

    private InputInterpreter inputInterpreter;
    private InputHandler handler;

    /**
     * Standard Shell-Constructor ohne Programmabhängigkeiten
     */
    public Shell() {
        this.scanner = new Scanner(System.in);
        inputInterpreter = new InputInterpreter();
        handler = new InputHandler(inputInterpreter);;

    }


    public void start(){



        while (true) {

            String in = null;

            BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));

            try {
                in=buffer.readLine();

            } catch (IOException e) {
                e.printStackTrace();

            }

            System.out.print(Context.getInstance().getPromptState());

            //String in = scanner.nextLine();

            handler.handleCommand(in);

        }

    }




}
