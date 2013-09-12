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

public class PageHandler extends AbstractHandler {
    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    private PageDAO pageDAO;
//
//    public PageHandler(Mongo mongo, String db) {
//        pageDAO = new PageDAO(mongo);
//    }
//
//    @Override
//    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String pageUri = PageHandler.parsePageUri(target);
//
//        if ("DELETE".equals(request.getMethod())) {
//            deletePage(request, response);
//        } else if ("POST".equals(request.getMethod())) {
//            postPage(target, request, response);
//        } else {
//            if ("".equals(pageUri)) {
//                getPages(response);
//            } else {
//                getPage(target, response);
//            }
//        }
//    }
//
//    private void postPage(String target, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            Page page = mapper.readValue(request.getReader(), Page.class);
//            page.getMeta().setUpdated((new Date()).getTime());
//
//            pageDAO.save(page);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        getPage(target, response);
//    }
//
//    private void deletePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            Page page = mapper.readValue(request.getReader(), Page.class);
//
//            pageDAO.delete(page);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        getPage("/page/main", response);
//    }
//
//    private void getPage(String target, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json;charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//
//        String uri = parsePageUri(target);
//        Page page = pageDAO.findByUri(uri);
//
//        if (page == null) {
//            page = new Page();
//            page.setUri(uri);
//            page.setParent(PageHandler.parseParentUri(target));
//            page.getMeta().setCreated((new Date().getTime()));
//
//            if ("main".equals(uri)) {
//                createContentForDefaultPage(page);
//
//            } else {
//                page.setTitle("New page: " + uri);
//            }
//        }
//
//        mapper.writeValue(response.getOutputStream(), page);
//    }
//
//    private void getPages(HttpServletResponse response) throws IOException {
//        List<Page> pages = pageDAO.query();
//
//        mapper.writeValue(response.getOutputStream(), pages);
//    }
//
//    private void createContentForDefaultPage(Page page) {
//        page.setTitle("Welcome to Chronicle");
//        page.setContent("Here should be more explanation about this project, maybe just load the README.md once the project has Markdown parsing!" +
//                "A First Level Header\n" +
//                "====================\n" +
//                "\n" +
//                "A Second Level Header\n" +
//                "---------------------\n" +
//                "\n" +
//                "Now is the time for all good men to come to\n" +
//                "the aid of their country. This is just a\n" +
//                "regular paragraph.\n" +
//                "\n" +
//                "The quick brown fox jumped over the lazy\n" +
//                "dog's back.\n" +
//                "\n" +
//                "### Header 3\n" +
//                "\n" +
//                "> This is a blockquote.\n" +
//                "> \n" +
//                "> This is the second paragraph in the blockquote.\n" +
//                ">\n" +
//                "> ## This is an H2 in a blockquote");
//    }


}
