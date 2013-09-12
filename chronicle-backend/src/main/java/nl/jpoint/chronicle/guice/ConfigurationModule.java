package nl.jpoint.chronicle.guice;

import com.google.inject.AbstractModule;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Configuration module.
 */
public class ConfigurationModule extends AbstractModule {
    @Override
    protected void configure() {
        try {
            bind(Configuration.class).toInstance(new PropertiesConfiguration("chronicle.properties"));
        } catch (org.apache.commons.configuration.ConfigurationException e) {
            throw new IllegalArgumentException("chronicle.properties could not be found on the classpath.");
        }
    }
}
