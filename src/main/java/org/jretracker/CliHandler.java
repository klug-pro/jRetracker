package org.jretracker;

import org.apache.commons.cli.*;

/**
 * Command line parameters handler.
 *
 *
 */
public class CliHandler {

    private final static String PORT = "port";
    private final static String HELP = "help";
    private final static int DEFAULT_PORT = 8080;

    private final Options options;
    private CommandLine line;

    public CliHandler() {
        options = new Options();

        Option option = new Option("p", "Server port.");
        option.setLongOpt(PORT);
        option.setDescription("Server port number.");
        option.setType(Integer.class);
        option.setArgName("NUMBER");
        option.setArgs(1);
        options.addOption(option);
        option = new Option("h", "Print help information.");
        option.setLongOpt(HELP);
        option.setOptionalArg(true);
        options.addOption(option);
    }

    public void parse(String[] args) throws Exception {
        CommandLineParser parser = new PosixParser();
        line = parser.parse(options, args);
    }

    public int getPort() {
        if (line.hasOption("port")) {
            return Integer.parseInt(line.getOptionValue(PORT));
        } else {
            return DEFAULT_PORT;
        }
    }

    public boolean isHelp() {
        return line.hasOption("help");
    }
}
