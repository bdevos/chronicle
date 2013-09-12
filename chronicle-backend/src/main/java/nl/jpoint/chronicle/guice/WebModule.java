package nl.jpoint.chronicle.guice;

import com.google.inject.servlet.ServletModule;
import com.mongodb.Mongo;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import nl.jpoint.chronicle.controller.PageController;
import nl.jpoint.chronicle.dao.PageDAO;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;

/**
 * Web Module
 */
public class WebModule extends ServletModule {
    protected void configureServlets() {

        bind(ObjectMapper.class);
        bind(ConfigurationModule.class);
        bind(PageController.class);
        bind(Mongo.class);
        bind(PageDAO.class);

        serve("/*").with(GuiceContainer.class, new HashMap<String, String>());
    }
}