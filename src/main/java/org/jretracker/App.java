package org.jretracker;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Main application class.
 */
public class App {

    private int port;
    private final static CliHandler cliHandler = new CliHandler();
    private final static Logger logger = Logger.getLogger(App.class.getName());

    public App(int port) {
        this.port = port;
    }

    public void run() {
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));

        bootstrap.setOption("keepAlive", false);
        bootstrap.setPipelineFactory(new RetrackerServerPipelineFactory());
        bootstrap.bind(new InetSocketAddress(port));
        logger.info("Server listening on port: " + port);

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
