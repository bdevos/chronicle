package nl.jpoint.chronicle.handler;

import com.mongodb.Mongo;
import nl.jpoint.chronicle.dao.PageDAO;
import nl.jpoint.chronicle.domain.Page;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageHandler extends AbstractHandler {

    private ObjectMapper mapper = new ObjectMapper();

    private PageDAO pageDAO;

    private Pattern pagePattern = Pattern.compile("/page/([\\w\\-]+)");

    public PageHandler(Mongo mongo, String db) {
        pageDAO = new PageDAO(mongo, db);
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageUri = parsePageUri(target);

        if ("POST".equals(request.getMethod())) {
            postPage(pageUri, request, response);
        } else {
            if (isPageQuery(target)) {
                getPages(response);
            } else {
                getPage(pageUri, response);
            }
        }
    }

    public boolean isPageQuery(String target) {
        return "/page".equals(target);
    }

    public String parsePageUri(String target) {
        Matcher pageMatcher = pagePattern.matcher(target);

        if (!pageMatcher.matches()) {
            return null;
        }
        return pageMatcher.group(1);
    }

    private void postPage(String pageUri, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Page page = mapper.readValue(request.getReader(), Page.class);
            page.getMeta().setUpdated((new Date()).getTime());

            pageDAO.save(page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        getPage(pageUri, response);
    }

    private void getPage(String pageUri, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        Page page = pageDAO.findByUri(pageUri);

        if (page == null) {
            page = new Page();
            page.setUri(pageUri);
            page.getMeta().setCreated((new Date().getTime()));

            if ("main".equals(pageUri)) {
                createContentForDefaultPage(page);

            } else {
                page.setTitle("New page: " + pageUri);
            }
        }

        mapper.writeValue(response.getOutputStream(), page);
    }

    private void getPages(HttpServletResponse response) throws IOException {
        List<Page> pages = pageDAO.query();

        mapper.writeValue(response.getOutputStream(), pages);
    }

    private void createContentForDefaultPage(Page page) {
        page.setTitle("Welcome to Chronicle");
        page.setContent("Here should be more explanation about this project, maybe just load the README.md once the project has Markdown parsing!");
    }
}
