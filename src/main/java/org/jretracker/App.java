package org.jretracker;

import org.ini4j.Wini;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Main application class.
 */
public class App {

    private final int port;

    private final static Logger logger = Logger.getLogger(App.class.getName());

    private App(int port) {
        this.port = port;
    }

    void run() {
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));

        bootstrap.setOption("keepAlive", false);
        bootstrap.setPipelineFactory(new RetrackerServerPipelineFactory());
        bootstrap.bind(new InetSocketAddress(port));
        logger.info("Server listening on port: " + port);

    }

    public static void main(String[] args) throws Exception {
        new App(new Wini(new File("settings.ini")).get("main", "port", int.class)).run();
    }
}
