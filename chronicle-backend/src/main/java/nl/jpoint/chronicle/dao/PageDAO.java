package nl.jpoint.chronicle.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import nl.jpoint.chronicle.domain.Page;

import java.util.List;

public class PageDAO {

    private BasicDAO<Page, String> dao;

    public PageDAO(Mongo mongo, String db) {
        Morphia morphia = new Morphia();
        morphia.map(Page.class);

        dao = new BasicDAO<Page, String>(Page.class, mongo, morphia, db);
    }

    public Page findByUri(String uri) {
        return dao.findOne(dao.createQuery().field("uri").equal(uri));
    }

    public void save(Page page) {
        dao.save(page);
    }

    public List<Page> query() {
        return dao.find().asList();
    }
}
