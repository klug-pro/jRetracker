package org.jretracker;

/**
 * Main application class.
 */
public class App {

    private int port;
    private final static CliHandler cliHandler = new CliHandler();

    public App(int port) {
        this.port = port;
    }

    public void run() {

    }

    public static void main(String[] args) throws Exception {
        // Load config.
        cliHandler.parse(args);
        // Show help.
        if (cliHandler.isHelp()) {
            System.out.println("Usage: App [--port=Port number]");
        }
        new App(cliHandler.getPort()).run();
    }
}
