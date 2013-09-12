package nl.jpoint.chronicle.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.inject.Inject;
import com.mongodb.Mongo;
import nl.jpoint.chronicle.domain.Page;
import org.apache.commons.configuration.Configuration;

import java.util.List;

public class PageDAO {

    @Inject
    private Configuration configuration;

    private BasicDAO<Page, String> dao;

    @Inject
    public PageDAO(Mongo mongo) {
        Morphia morphia = new Morphia();
        morphia.map(Page.class);

        dao = new BasicDAO<Page, String>(Page.class, mongo, morphia, "chronicle");
    }

    public Page findByUri(String uri) {
        return dao.findOne(dao.createQuery().field("uri").equal(uri));
    }

    public void save(Page page) {
        dao.save(page);
    }

    public void delete(Page page) {
        dao.delete(page);
    }

    public List<Page> query() {
        return dao.find().asList();
    }
}