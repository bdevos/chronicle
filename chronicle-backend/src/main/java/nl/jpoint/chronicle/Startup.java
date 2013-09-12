package nl.jpoint.chronicle;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.UnknownHostException;

public class Startup {
    /**
     * run under root context
     */
    private static final String CONTEXT_PATH = "/";
    /**
     * location where resources should be provided not related to the webapp
     */
    private static final String RESOURCE_BASE = "src/main/webapp";

    public Startup() {
        Mongo mongo = null;
        try {
            mongo = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        // setup a server with the specified port
        Server server = new Server(8080);
        WebAppContext webapp = new WebAppContext();

        // configure the webapp
        webapp.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        webapp.setContextPath(CONTEXT_PATH);
        webapp.setResourceBase(RESOURCE_BASE);
        webapp.setClassLoader(Thread.currentThread().getContextClassLoader());

        // attach the webapp to the server
        server.setHandler(webapp);

        // start the server and wait
        server.start();
        server.join();
    }


}

