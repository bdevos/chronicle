package nl.jpoint.chronicle;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import nl.jpoint.chronicle.handler.PageHandler;
import nl.jpoint.chronicle.handler.UnknownHandler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;

public class Startup extends AbstractHandler {

    private static final String DB_NAME = "chronicle";

    private PageHandler pageHandler;
    private UnknownHandler unknownHandler;

    public Startup() {
        Mongo mongo = null;
        try {
            mongo = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        pageHandler = new PageHandler(mongo, DB_NAME);
        unknownHandler = new UnknownHandler();
    }

    public void handle(String target, Request baseRequest,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (pageHandler.isPageQuery(target) || pageHandler.parsePageUri(target) != null) {
            pageHandler.handle(target, baseRequest, request, response);

        } else {
            unknownHandler.handle(target, baseRequest, request, response);
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new Startup());

        server.start();
        server.join();
    }
}

